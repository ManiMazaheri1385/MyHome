package devices;

import db.Device;

public class Thermostat extends Device {

    public int temperature;

    public Thermostat(String name, Protocol protocol) {
        super(name, protocol);
        temperature = 20;
    }

    @Override
    public String information() {
        return name + " " + status + " " + temperature + "C " + protocol;
    }

    @Override
    public void setValue(int temperature) {
        if (temperature < 10 || temperature > 30) {
            throw new IllegalArgumentException("invalid value");
        }
        this.temperature = temperature;
    }

}
