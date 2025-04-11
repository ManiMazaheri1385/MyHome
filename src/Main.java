import db.Database;
import db.Device;
import devices.services.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static String[] commandArgs;

    public static void main(String[] args) {
        int cycles = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < cycles; i++) {
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
                default -> System.out.println("Unknown command: " + command);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addDevice() {
        switch (commandArgs[1]) {
            case "light" -> LightService.addLight(commandArgs[2], commandArgs[3]);
            case "thermostat" -> ThermostatService.addThermostat(commandArgs[2], commandArgs[3]);
            default -> {
                System.out.println("Unknown command: " + commandArgs[1]);
                return;
            }
        }

        System.out.println("device added successfully");
    }

    public static void setDevice() {
        Device device = Database.get(commandArgs[1]);

        switch (commandArgs[2]) {
            case "status" -> {
                device.status = Device.Status.valueOf(commandArgs[3]);
                Database.update(device);
            }
            case "brightness" -> LightService.setBrightness(device, commandArgs[3]);
            case "temperature" -> ThermostatService.setTemperature(device, commandArgs[3]);
            default -> {
                System.out.println("Unknown command: " + commandArgs[2]);
                return;
            }
        }

        System.out.println("device updated successfully");
    }

    public static void removeDevice() {
        Database.remove(commandArgs[1]);
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

}
