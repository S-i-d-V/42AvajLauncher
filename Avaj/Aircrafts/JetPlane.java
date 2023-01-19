package Avaj.Aircrafts;

//Imports
import Avaj.Coordinates;
import Avaj.Tower.WeatherTower;
import Avaj.Simulator.Logs;

import java.util.HashMap;

//This class inherits from the Aircraft class
//And implement the interface Flyable
public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    //I use a map to easily retrieve weather communication of Aircraft.
    private HashMap<String, String> weatherComm = new HashMap<>();

    //Constructor
    //This method should be private according to the UML Diagram, but it is not possible.
    //JetPlane must be instancied by the AircraftFactory class
    public JetPlane(String name, Coordinates coordinates){
        //Construct the parent class
        super(name, coordinates);
        //Fill my weather communication hashhMap
        weatherComm.put("RAIN", "It's raining dayyyyyy !");
        weatherComm.put("FOG", "I can't even see the nose of the plane...");
        weatherComm.put("SUN", "Remind me to take my sunglasses next time ahah.");
        weatherComm.put("SNOW", "My engine is freezing !");
        //Assign the type in the parent class Aircraft for log communications.
        this.type = "JetPlane";
    }

    //Update the coordinates due to weather
    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);

        //Latitude increase by 5 when RAIN
        if (weather.equals("RAIN")) {
            this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 5, this.coordinates.getHeight());
            Logs.appendToLogFile(this.getType() + "#" + this.getName() + "(" + this.getId() + "): " + weatherComm.get(weather) + "\n");
        }
        //Latitude increase by 1 when FOG
        else if (weather.equals("FOG")) {
            this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 1, this.coordinates.getHeight());
            Logs.appendToLogFile(this.getType() + "#" + this.getName() + "(" + this.getId() + "): " + weatherComm.get(weather) + "\n");
        }
        //Height increase by 2 & Longitude increase by 10 when SUN
        else if (weather.equals("SUN")) {
            if (this.coordinates.getHeight() + 2 >= 100)
                this.coordinates = new Coordinates(this.coordinates.getLongitude() + 10, this.coordinates.getLatitude(), 100);
            else
                this.coordinates = new Coordinates(this.coordinates.getLongitude() + 10, this.coordinates.getLatitude(), this.coordinates.getHeight() + 2);
            Logs.appendToLogFile(this.getType() + "#" + this.getName() + "(" + this.getId() + "): " + weatherComm.get(weather) + "\n");
        }
        //Height decrease by 7 when SNOW
        else if (weather.equals("SNOW")) {
            //If the height is inferior or equal to 7, the JetPlane land.
            if (this.coordinates.getHeight() <= 7) {
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), 0);
                this.unregisterTower(weatherTower);
                //Logs.appendToLogFile(this.getType() + "#" + this.getName() + "(" + this.getId() + "): " + weatherComm.get(weather) + "\n");
                return;
            }
            else
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 7);
            Logs.appendToLogFile(this.getType() + "#" + this.getName() + "(" + this.getId() + "): " + weatherComm.get(weather) + "\n");
        }
        else
            System.out.println("INVALID");
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