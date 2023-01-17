package Avaj.Aircrafts;

//Imports
import Avaj.Coordinates;

//Baloon/Helicopter/JetPlane's Parent class 
//This class is abstract because she's designed to be extended
public abstract class Aircraft {
    protected       long        id;
    protected       String      name;
    protected       Coordinates coordinates;
    //Anytime an Aircraft is instancied, the next aircraft instancied will conserve the same idCounter
    private static  long        idCounter = 0;

    //Constructor
    protected Aircraft(String name, Coordinates coordinates){
        this.name = name;
        this.coordinates = coordinates;
        this.id = nextId();
    }

    //Increment the idCounter then return the next id
    private long nextId() {
        idCounter += 1;
        return (idCounter);
    }
}