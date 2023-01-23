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

	//Change the weather for every Flyable in the Flyables's list contained in the tower class.
	private void changeWeather() {
		this.conditionsChanged();
	}

	//In the UML Diagram, the changeWeather() method is private, should be public
	//But in doubt, i will use this public method to call the private one.
	public void applyWeatherChange() {
		this.changeWeather();
	}
}