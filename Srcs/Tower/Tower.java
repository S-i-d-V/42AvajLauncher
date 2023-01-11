package Srcs.Tower;

//Imports
import java.util.ArrayList;
import Srcs.Coordinates;
import Srcs.Aircrafts.Flyable;

//No constructor in the UML Diagram means this class is abstract
//WeatherTower's parent class
public abstract class Tower {
    private ArrayList<Flyable> observers = new ArrayList<Flyable>();

    //Add the given flyable to the tower's Flyable list
	public void register(Flyable flyable) {
		if (!this.observers.contains(flyable))
			this.observers.add(flyable);
	}

    //Delete the given flyable from the tower's Flyable list
	public void unregister(Flyable flyable) {
		this.observers.remove(observers.indexOf(flyable));
	}

    // ?
	protected void conditionsChanged() {
        System.out.println("Conditions changed");
	}
}