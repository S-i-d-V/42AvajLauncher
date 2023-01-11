package Srcs.Tower;

import Srcs.Coordinates;
import Srcs.WeatherProvider;

//This class inherits from the Tower class
public class WeatherTower extends Tower {
    //Return the weather generated for a precise location
	public String getWeather(Coordinates coordinates) {
		return (WeatherProvider.getProvider().getCurrentWeather(coordinates));
	}

    // ?
	public void changeWeather() {
		System.out.println("Change weather");
	}
}