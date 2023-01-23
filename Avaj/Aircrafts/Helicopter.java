package Avaj.Aircrafts;

//Imports
import Avaj.Coordinates;
import Avaj.Tower.WeatherTower;
import Avaj.Simulator.Logs;

import java.util.HashMap;

//This class inherits from the Aircraft class
//And implement the interface Flyable
public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    //I use a map to easily retrieve weather communication of Aircraft.
    private HashMap<String, String> weatherComm = new HashMap<>();

    //Constructor
    //This method should be private according to the UML Diagram, but it is not possible.
    //Helicopter must be instancied by the AircraftFactory class
    public Helicopter(String name, Coordinates coordinates){
        //Construct the parent class
        super(name, coordinates);
        //Fill my weather communication hashhMap
        weatherComm.put("RAIN", "My propeller protects us from the rain !");
        weatherComm.put("FOG", "I can't see anything...");
        weatherComm.put("SUN", "A good flight is like a good coffee. WARM !");
        weatherComm.put("SNOW", "Am I really flying in all this snow ?");
        //Assign the type in the parent class Aircraft for log communications.
        this.type = "Helicopter";
    }

    //Update the coordinates due to weather
    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);
        if (weather.equals("RAIN")) {
            this.coordinates = new Coordinates(this.coordinates.getLongitude() + 5, this.coordinates.getLatitude(), this.coordinates.getHeight());
            Logs.appendToLogFile(this.getFormattedLogName() + ": " + weatherComm.get(weather) + "\n");
        }
        else if (weather.equals("FOG")) {
            this.coordinates = new Coordinates(this.coordinates.getLongitude() + 5, this.coordinates.getLatitude(), this.coordinates.getHeight());
            Logs.appendToLogFile(this.getFormattedLogName() + ": " + weatherComm.get(weather) + "\n");
        }
        else if (weather.equals("SUN")) {
            if (this.coordinates.getHeight() + 2 >= 100)
                this.coordinates = new Coordinates(this.coordinates.getLongitude() + 10, this.coordinates.getLatitude(), 100);
            else
                this.coordinates = new Coordinates(this.coordinates.getLongitude() + 10, this.coordinates.getLatitude(), this.coordinates.getHeight() + 2);
            Logs.appendToLogFile(this.getFormattedLogName() + ": " + weatherComm.get(weather) + "\n");
        }
        else if (weather.equals("SNOW")) {
            //If the height is inferior or equal to 12, the Helicopter land.
            if (this.coordinates.getHeight() <= 12) {
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), 0);
                Logs.appendToLogFile(this.getFormattedLogName() + " landing at " + this.coordinates.getFormattedCoordinates() + ".\n");
                this.unregisterTower(weatherTower);
                return;
            }
            else
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 12);
            Logs.appendToLogFile(this.getFormattedLogName() + ": " + weatherComm.get(weather) + "\n");
        }
    }

    //Regiser the Flyable to the WeatherTower
    public void registerTower(WeatherTower weatherTower){
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
    }

    //Unregiser the Flyable to the WeatherTower
    public void unregisterTower(WeatherTower weatherTower){
        this.weatherTower.unregister(this);
    }
}