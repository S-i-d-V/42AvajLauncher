package Srcs;

//Generate the weather for the WeatherTower
public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private String[] weather;

    //Constructor
    //Fill the array of weather with weathers implemented in the simulator
    private WeatherProvider() {
        this.weather = new String[]{ "RAIN", "FOG", "SUN", "SNOW" };
    }

    //WeatherProvider getter
    public static WeatherProvider getProvider() {
        return (weatherProvider);
    }

    //Return the weather at a precise location
    //This method has to return a random weather for thoses coordinates
    //The coordinates have to be used to generate it
    public String getCurrentWeather(Coordinates coordinates){
        int random = 1;
        return (this.weather[random % 4]);
    }
}