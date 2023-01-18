package Avaj;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//Generate the weather for the WeatherTower
public class WeatherProvider {
    //Static instance of the class, the constructor is private so this class can be instancied only once from the inside.
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
        try {
            //To generate a random weather from the 3D points, i hash the coordinates and nodulo the integer.
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest((coordinates.getLongitude() + ":" + coordinates.getLatitude() + ":" + coordinates.getHeight()).getBytes());
            long hash = 0;

            for (int i = 0; i < 8; i++){
                hash <<= 8;
                hash |= (bytes[i] & 0xFF);
            }
            if (hash < 0)
                hash = hash * -1;

            return (this.weather[(int)(hash % 4) % 4]);
        }
        catch (NoSuchAlgorithmException e) {
            return ("INVALID WEATHER");
        }
    }
}