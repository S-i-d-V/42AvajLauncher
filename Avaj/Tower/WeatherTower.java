package Avaj.Tower;

//Imports
import Avaj.Coordinates;
import Avaj.WeatherProvider;

//This class inherits from the Tower class
public class WeatherTower extends Tower {
    //Return the weather generated for a precise location
	public String getWeather(Coordinates coordinates) {
		return (WeatherProvider.getProvider().getCurrentWeather(coordinates));
	}

    // ?
    //This method should be private according to the UML Diagram, but it is not possible.
	public void changeWeather() {
		System.out.println("Change weather");
	}
}