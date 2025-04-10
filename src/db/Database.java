package db;

import java.util.ArrayList;

public class Database {
    private static int identifier = 1;
    private static ArrayList<Device> devices = new ArrayList<>();

    private Database() {}

    public static void add(Device device) {
        device.id = identifier;
        devices.add(device.clone());
        identifier++;
    }

    public static Device get(int id) {
        for (Device device : devices) {
            if (device.id == id) {
                return device.clone();
            }
        }
        return null;
    }

    public static void remove(int id) {
        Device entity = get(id);
        devices.remove(entity);
    }

    public static void update(Device device) {
        get(device.id);

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
