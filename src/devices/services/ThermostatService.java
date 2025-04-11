package devices.services;

import db.Device;
import db.Database;
import devices.Thermostat;

public class ThermostatService {

    private ThermostatService() {}

    public static void addThermostat(String name, String protocolString) {
        try {
            Device.Protocol protocol = Device.Protocol.valueOf(protocolString);
            Thermostat thermostat = new Thermostat(name, protocol);
            Database.addDevice(thermostat);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("invalid protocol");
        }
    }

    public static void setTemperature(Device device, String temperatureString) {
        Thermostat thermostat = (Thermostat) device;
        int temperature = Integer.parseInt(temperatureString);
        thermostat.setValue(temperature);
        Database.update(thermostat);
    }

}
