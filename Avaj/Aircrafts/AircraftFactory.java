package Avaj.Aircrafts;

//Imports
import Avaj.Coordinates;

//No constructor in the UML Diagram means this class is abstract
//Create a new Aircraft (Baloon, Helicopter, JetPlane)
public abstract class AircraftFactory {
    //Create new Aircrafts (Baloons, Helicopter, JetPlane)
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height){
        //Coordinates of the new Aircraft
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        if (type.equals("Baloon"))
            return (new Baloon(name, coordinates));
        else if (type.equals("Helicopter"))
            return (new Helicopter(name, coordinates));
        else if (type.equals("JetPlane"))
            return (new JetPlane(name, coordinates));
        //In case this method reutrn null, i have to throw an exception
        else
            return (null);
    }
}