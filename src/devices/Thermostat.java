package devices;

import db.Device;
import db.Validator;

public class Thermostat extends Device implements Validator {

    public int temperature;
    public String name;

    public Thermostat(String name, Protocol protocol) {
        super(name, protocol);
        temperature = 20;
    }

    @Override
    public void setValue(int temperature) {
        if (temperature < 10 || temperature > 30) {
            throw new IllegalArgumentException("temperature must be between 10 and 30");
        }
        this.temperature = temperature;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

}
