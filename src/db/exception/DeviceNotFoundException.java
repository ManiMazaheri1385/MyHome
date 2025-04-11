package db.exception;

public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException() {
        super("device not found");
    }
}
