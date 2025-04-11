package devices.services;

import db.Database;
import db.Device;
import devices.Light;

public class LightService {

    private LightService() {}

    public static void addLight(String name, String protocolString) {
        Device.Protocol protocol = Device.Protocol.valueOf(protocolString);
        Light light = new Light(name, protocol);
        Database.add(light);
    }

    public static void setBrightness(Device device, String brightnessString) {
        Light light = (Light) device;
        int brightness = Integer.parseInt(brightnessString);
        light.setValue(brightness);
        try {
            Database.update(light);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void removeLight(String name) {}
}
