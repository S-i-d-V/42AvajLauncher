package Srcs.Aircrafts;

//Imports
import Srcs.Coordinates;

//Baloon/Helicopter/JetPlane's Parent class 
public class Aircraft {
    protected       long        id;
    protected       String      name;
    protected       Coordinates coordinates;
    private static  long        idCounter = 0;

    //Constructor
    protected Aircraft(String name, Coordinates coordinates){
        this.name = name;
        this.coordinates = coordinates;
        this.id = nextId();
    }

    //Increment the idCounter and return the next id
    private long nextId() {
        idCounter += 1;
        return (idCounter);
    }
}