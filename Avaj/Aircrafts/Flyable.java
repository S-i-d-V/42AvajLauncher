package Avaj.Aircrafts;

//Imports
import Avaj.Tower.WeatherTower;

//Interface shared by Aircrafts (Baloon, Helicopter, JetPlanes)
//Every methods will be defined by class that implement it.
public interface Flyable {
    public void updateConditions();

    public void registerTower(WeatherTower weatherTower);
}