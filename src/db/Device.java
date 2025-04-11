package db;

public abstract class Device implements Cloneable, Validator {

    public String name;
    public Protocol protocol;
    public Status status;

    public enum Protocol {
        WiFi, Bluetooth;
    }
    public enum Status {
        on, off;
    }

    public Device(String name, Protocol protocol) {
        setName(name);
        this.protocol = protocol;
        this.status = Status.off;
    }

    public abstract String information();

    @Override
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
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
        return  name.equals(device.name);
    }

}