package Avaj.Aircrafts;

//Imports
import Avaj.Coordinates;
import Avaj.Tower.WeatherTower;
import java.util.HashMap;

//This class inherits from the Aircraft class
//And implement the interface Flyable
public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;
    //I use a map to easily retrieve weather communication of Aircraft.
    private HashMap<String, String> weatherComm = new HashMap<>() {{
        weatherComm = new HashMap<>();
        weatherComm.put("RAIN", "It's raining day !");
        weatherComm.put("FOG", "I can't see anything...");
        weatherComm.put("SUN", "What good weather to fly !");
        weatherComm.put("SNOW", "My engine is freezing !");
    }};

    //Constructor
    //This method should be private according to the UML Diagram, but it is not possible.
    //Helicopter must be instancied by the AircraftFactory class
    public Helicopter(String name, Coordinates coordinates){
        //Construct the parent class
        super(name, coordinates);
    }

    //Update the coordinates due to weather
    public void updateConditions() {
        String weather = weatherTower.getWeather(this.coordinates);

        //Longitude increase by 5 when RAIN
        if (weather == "RAIN"){
            this.coordinates = new Coordinates(this.coordinates.getLongitude() + 5, this.coordinates.getLatitude(), this.coordinates.getHeight());
        }
        //Longitude increase by 1 when FOG
        else if (weather == "FOG"){
            this.coordinates = new Coordinates(this.coordinates.getLongitude() + 5, this.coordinates.getLatitude(), this.coordinates.getHeight());
        }
        //Height increase by 2 & Longitude increase by 10 when SUN
        else if (weather == "SUN"){
            //If the Helicopter is at a superior height than 98 meter, he can't reach higher.
            if (this.coordinates.getHeight() + 2 >= 100)
                this.coordinates = new Coordinates(this.coordinates.getLongitude() + 10, this.coordinates.getLatitude(), 100);
            else
                this.coordinates = new Coordinates(this.coordinates.getLongitude() + 10, this.coordinates.getLatitude(), this.coordinates.getHeight() + 2);
        }
        //Height decrease by 12 when SNOW
        else if (weather == "SNOW"){
            //If the height is inferior or equal to 12, the Helicopter land.
            if (this.coordinates.getHeight() <= 12)
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), 0);
            else
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 12);
        }
        else
            System.out.println("INVALID");
    }

    //Regiser the Flyable to the WeatherTower
    //Unregister from the current tower and register to the new one
    public void registerTower(WeatherTower weatherTower){
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
    }
}