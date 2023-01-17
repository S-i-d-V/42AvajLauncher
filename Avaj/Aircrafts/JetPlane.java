package Avaj.Aircrafts;

//Imports
import Avaj.Coordinates;
import Avaj.Tower.WeatherTower;
import java.util.HashMap;

//This class inherits from the Aircraft class
//And implement the interface Flyable
public class JetPlane extends Aircraft implements Flyable {
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
    //JetPlane must be instancied by the AircraftFactory class
    public JetPlane(String name, Coordinates coordinates){
        //Construct the parent class
        super(name, coordinates);
    }

    //Update the coordinates due to weather
    public void updateConditions() {
        System.out.println("Function (JetPlane) updateCondition()");
        String weather = weatherTower.getWeather(this.coordinates);

        //Latitude increase by 5 when RAIN
        if (weather == "RAIN"){
            this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 5, this.coordinates.getHeight());
        
            //DEBUG
			System.out.println("TYPE#NAME(UID):" + weatherComm.get(weather));
        }
        //Latitude increase by 1 when FOG
        else if (weather == "FOG"){
            this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 1, this.coordinates.getHeight());
            
            //DEBUG
			System.out.println("TYPE#NAME(UID):" + weatherComm.get(weather));
        }
        //Height increase by 2 & Longitude increase by 10 when SUN
        else if (weather == "SUN"){
            if (this.coordinates.getHeight() + 2 >= 100)
                this.coordinates = new Coordinates(this.coordinates.getLongitude() + 10, this.coordinates.getLatitude(), 100);
            else
                this.coordinates = new Coordinates(this.coordinates.getLongitude() + 10, this.coordinates.getLatitude(), this.coordinates.getHeight() + 2);
                
            //DEBUG
			System.out.println("TYPE#NAME(UID):" + weatherComm.get(weather));
        }
        //Height decrease by 7 when SNOW
        else if (weather == "SNOW"){
            //If the height is inferior or equal to 7, the JetPlane land.
            if (this.coordinates.getHeight() <= 7) {
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), 0);
                //Need to unregister from weatherTower
                //Log current coordinates
            }
            else
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 7);
            
            //DEBUG
			System.out.println("TYPE#NAME(UID):" + weatherComm.get(weather));
        }
        else
            System.out.println("INVALID");
    }

    //Regiser the Flyable to the WeatherTower
    //Unregister from the current tower and register to the new one
    public void registerTower(WeatherTower weatherTower){
        this.weatherTower.unregister(this);
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
    }
}