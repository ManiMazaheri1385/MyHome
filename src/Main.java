import db.Device;
import db.Database;
import java.util.Date;
import java.util.Scanner;
import devices.services.*;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static String[] commandArgs;

    public static void main(String[] args) {
        int cycles = Integer.parseInt(scanner.nextLine());

        for (int i = 0;i < cycles; i++) {
            String command = scanner.nextLine().trim();
            commandArgs = command.split(" ");
            processCommand(commandArgs[0]);
        }

    }

    public static void processCommand(String command) {
        try {
            switch (command) {
                case "add_device" -> addDevice();
                case "set_device" -> setDevice();
                case "remove_device" -> removeDevice();
                case "list_devices" -> listDevices();
                case "add_rule" -> addRule();
                case "check_rules" -> checkRules();
                case "list_rules" -> listRules();
                default -> System.out.println("Unknown command: " + command);
            }
        } catch (ClassCastException e) {
            System.out.println("invalid property");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addDevice() {
        switch (commandArgs[1]) {
            case "light" -> LightService.addLight(commandArgs[2], commandArgs[3]);
            case "thermostat" -> ThermostatService.addThermostat(commandArgs[2], commandArgs[3]);
            default -> {
                System.out.println("invalid input");
                return;
            }
        }

        System.out.println("device added successfully");
    }

    public static void setDevice() {
        Device device = Database.get(commandArgs[1]);

        switch (commandArgs[2]) {
            case "status" -> {
                try {
                    device.status = Device.Status.valueOf(commandArgs[3]);
                    Database.update(device);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("invalid status");
                }
            }
            case "brightness" -> LightService.setBrightness(device, commandArgs[3]);
            case "temperature" -> ThermostatService.setTemperature(device, commandArgs[3]);
            default -> {
                System.out.println("invalid input");
                return;
            }
        }

        System.out.println("device updated successfully");
    }

    public static void removeDevice() {
        Database.remove(commandArgs[1]);
        System.out.println("device removed successfully");
    }

    public static void listDevices() {
        ArrayList<Device> devices = Database.getAll();

        if (devices.isEmpty()) {
            System.out.println();
            return;
        }

        for (Device device : devices) {
            System.out.println(device.information());
        }
    }

    public static void addRule() {
        Device device = Database.get(commandArgs[1]);

        if (device.rule != null) {
            throw new IllegalArgumentException("duplicate rule");
        }
        try {
            Device.Status action = Device.Status.valueOf(commandArgs[3]);
            Date time = time(commandArgs[2]);
            device.rule = new Device.Rule(action, time);
            Database.update(device);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("invalid action");
        }

        System.out.println("rule added successfully");
    }

    public static void checkRules() {
        Date time = time(commandArgs[1]);
        ArrayList<Device> devices = Database.getAll();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

        for (Device device : devices) {
            String deviceRule = dateFormat.format(device.rule.time);
            if (deviceRule.equals(commandArgs[1])) {
                device.status = device.rule.action;
                Database.update(device);
            }
        }

        System.out.println("rules checked");
    }

    public static void listRules() {
        ArrayList<Device> devices = Database.getAll();
        if (devices.isEmpty()) {
            System.out.println();
        }

        for (Device device : devices) {
            if (device.rule == null) {
                continue;
            }
            System.out.println(device.name + " " + device.rule.information());
        }
    }

    public static Date time(String dateString) {
        try {
            return new SimpleDateFormat("HH:mm").parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException("invalid time");
        }
    }

}
