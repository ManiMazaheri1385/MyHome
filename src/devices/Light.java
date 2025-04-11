package devices;

import db.Device;
import db.Validator;

public class Light extends Device {

    public int brightness;

    public Light(String name, Protocol protocol) {
        super(name, protocol);
        brightness = 50;
    }

    @Override
    public String information() {
        return "light: " + name + " " + status + " " + brightness + "% " + protocol;
    }

    @Override
    public void setValue(int brightness) {
        if (brightness < 0 || brightness > 100) {
            throw new IllegalArgumentException("invalid value");
        }
        this.brightness = brightness;
    }

}
