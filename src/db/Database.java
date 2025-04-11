package db;

import db.exception.DeviceNotFoundException;

import java.util.ArrayList;

public class Database {
    private static ArrayList<Device> devices = new ArrayList<>();

    private Database() {}

    public static void add(Device device) {
        try {
            get(device.name);
            throw new IllegalArgumentException("duplicate device name");
        } catch (DeviceNotFoundException e) {
            devices.add(device.clone());
        }
    }

    public static Device get(String name) throws DeviceNotFoundException {
        for (Device device : devices) {
            if (device.name.equals(name)) {
                return device.clone();
            }
        }
        throw new DeviceNotFoundException();
    }

    public static void remove(String name) {
        Device device = get(name);
        devices.remove(device);
    }

    public static void update(Device device) {
        get(device.name);

        int index = devices.indexOf(device);
        devices.set(index, device.clone());
    }

    public static ArrayList<Device> getAll() {
        ArrayList<Device> devicesCopy = new ArrayList<>();
        for (Device device : devices) {
            devicesCopy.add(device.clone());
        }
        return devicesCopy;
    }

}
