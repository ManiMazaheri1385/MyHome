package db;

public abstract class Device implements Cloneable{
    public int id;
    public String name;
    public Protocol protocol;
    public Status status;
    public int value;

    public enum Protocol {
        WiFi, Bluetooth;
    }
    public enum Status {
        on, off;
    }

    @Override
    public Device clone() {
        try {
            Device cloned = (Device) super.clone();
            return cloned;
        } catch (CloneNotSupportedException e) {
            System.out.println("Cloning failed!");
            return null;
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Device device = (Device) object;
        return id == device.id;
    }

}