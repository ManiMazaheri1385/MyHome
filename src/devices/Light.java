package devices;

import db.Device;
import db.Validator;

public class Light extends Device implements Validator {

    public int brightness;
    public String name;

    public Light(String name, Protocol protocol) {
        super(name, protocol);
        brightness = 50;
    }

    @Override
    public void setValue(int brightness) {
        if (brightness < 0 || brightness > 100) {
            throw new IllegalArgumentException("brightness must be between 0 and 100");
        }
        this.brightness = brightness;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

}
