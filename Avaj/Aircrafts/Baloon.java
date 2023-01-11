package Avaj.Aircrafts;

//Imports
import Avaj.Coordinates;
import Avaj.Tower.WeatherTower;

//This class inherits from the Aircraft class
//And implement the interface Flyable
public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    //Constructor
    //This method should be private according to the UML Diagram, but it is not possible.
    //Baloon must be instancied by the AircraftFactory class
    public Baloon(String name, Coordinates coordinates){
        //Construct the parent class
        super(name, coordinates);
    }

    //?
    public void updateConditions() {
        System.out.println("Update the weather conditions");
    }

    //Regiser the Flyable to the WeatherTower
    //Unregister from the current tower and register to the new one
    public void registerTower(WeatherTower weatherTower){
        this.weatherTower.unregister(this);
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
    }
}