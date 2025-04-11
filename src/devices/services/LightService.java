package devices.services;

import db.Device;
import db.Database;
import devices.Light;

public class LightService {

    private LightService() {}

    public static void addLight(String name, String protocolString) {
        try {
            Device.Protocol protocol = Device.Protocol.valueOf(protocolString);
            Light light = new Light(name, protocol);
            Database.addDevice(light);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("invalid protocol");
        }
    }

    public static void setBrightness(Device device, String brightnessString) {
        Light light = (Light) device;
        int brightness = Integer.parseInt(brightnessString);
        light.setValue(brightness);
        Database.update(light);
    }

}
