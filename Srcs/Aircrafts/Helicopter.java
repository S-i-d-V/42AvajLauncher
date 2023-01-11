package Srcs.Aircrafts;

//Imports
import Srcs.Coordinates;
import Srcs.Tower.WeatherTower;

//This class inherits from the Aircraft class
//And implement the interface Flyable
public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    //Constructor
    //This method should be private according to the UML Diagram, but it is not possible.
    //Helicopter must be instancied by the AircraftFactory class
    public Helicopter(String name, Coordinates coordinates){
        //Construct the parent class
        super(name, coordinates);
    }

    //Update conditions
    public void updateConditions() {
        System.out.println("Update the weather conditions");
    }

    //Regiser the Flyable to the WeatherTower
    //Unregister from the current tower and register to the new one
    public void registerTower(WeatherTower weatherTower){
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
    }
}