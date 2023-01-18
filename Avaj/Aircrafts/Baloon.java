package Avaj.Aircrafts;

//Imports
import Avaj.Coordinates;
import Avaj.Tower.WeatherTower;
import Avaj.Simulator.Logs;

import java.util.HashMap;

//This class inherits from the Aircraft class
//And implement the interface Flyable
public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    //I use a map to easily retrieve weather communication of Aircraft.
    private HashMap<String, String> weatherComm = new HashMap<>();

    //Constructor
    //This method should be private according to the UML Diagram, but it is not possible.
    //Baloon must be instancied by the AircraftFactory class
    public Baloon(String name, Coordinates coordinates){
        //Construct the parent class
        super(name, coordinates);
        //Fill my weather communication hashhMap
        weatherComm.put("RAIN", "The meal will be soaked...");
        weatherComm.put("FOG", "I can't even see the runway.");
        weatherComm.put("SUN", "Amazing weather to fly with the love of your life !");
        weatherComm.put("SNOW", "I feel my fingertips freezing.");
        //Assign the type in the parent class Aircraft for log communications.
        this.type = "Baloon";
    }

    //Update the coordinates due to weather
    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);

        //Height decrease by 5 when RAIN
        if (weather.equals("RAIN")) {
            //If the height is inferior to 5, the Baloon land.
            if (this.coordinates.getHeight() < 5)
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), 0);
            else
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 5);
            
            //DEBUG
            Logs.appendToLogFile(this.getType() + "#" + this.getName() + "(" + this.getId() + "): " + weatherComm.get(weather) + "\n");
			System.out.println(this.getType() + "#" + this.getName() + "(" + this.getId() + "): " + weatherComm.get(weather));
        }
        //Height decrease by 3 when FOG
        else if (weather.equals("FOG")) {
            //If the height is inferior to 3, the Baloon land.
            if (this.coordinates.getHeight() < 3)
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), 0);
            else
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 3);
            
            //DEBUG
            Logs.appendToLogFile(this.getType() + "#" + this.getName() + "(" + this.getId() + "): " + weatherComm.get(weather) + "\n");
			System.out.println(this.getType() + "#" + this.getName() + "(" + this.getId() + "): " + weatherComm.get(weather));
        }
        //Height increase by 4 & Longitude increase by 2 when SUN
        else if (weather.equals("SUN")) {
            if (this.coordinates.getHeight() + 4 >= 100)
                this.coordinates = new Coordinates(this.coordinates.getLongitude() + 2, this.coordinates.getLatitude(), 100);
            else
                this.coordinates = new Coordinates(this.coordinates.getLongitude() + 2, this.coordinates.getLatitude(), this.coordinates.getHeight() + 4);
        
            //DEBUG
            Logs.appendToLogFile(this.getType() + "#" + this.getName() + "(" + this.getId() + "): " + weatherComm.get(weather) + "\n");
			System.out.println(this.getType() + "#" + this.getName() + "(" + this.getId() + "): " + weatherComm.get(weather));
        }
        //Height decrease by 15 when SNOW
        else if (weather.equals("SNOW")) {
            //If the height is inferior or equal to 15, the Baloon land.
            if (this.coordinates.getHeight() <= 15) {
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), 0);
                //Need to unregister from weatherTower
                this.unregisterTower(weatherTower);
            }
            else
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 15);
        
            //DEBUG
            Logs.appendToLogFile(this.getType() + "#" + this.getName() + "(" + this.getId() + "): " + weatherComm.get(weather) + "\n");
			System.out.println(this.getType() + "#" + this.getName() + "(" + this.getId() + "): " + weatherComm.get(weather));
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