package devices.services;

import db.Database;
import db.Device;
import devices.Thermostat;

public class ThermostatService {

    private ThermostatService() {}

    public static void addThermostat(String name, String protocolString) {
        Device.Protocol protocol = Device.Protocol.valueOf(protocolString);
        Thermostat thermostat = new Thermostat(name, protocol);
        Database.add(thermostat);
    }

    public static void setTemperature(Device device, String temperatureString) {
        Thermostat thermostat = (Thermostat) device;
        int temperature = Integer.parseInt(temperatureString);
        thermostat.setValue(temperature);
        Database.update(thermostat);
    }

}
