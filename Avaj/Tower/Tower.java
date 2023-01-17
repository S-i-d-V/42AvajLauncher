package Avaj.Tower;

//Imports
import java.util.ArrayList;
import Avaj.Aircrafts.Flyable;

//No constructor in the UML Diagram means this class is abstract + she's designed to be extended
//WeatherTower's parent class
public abstract class Tower {
    private ArrayList<Flyable> observers = new ArrayList<Flyable>();

    //Add the given flyable to the tower's Flyable list
	public void register(Flyable flyable) {
		if (!this.observers.contains(flyable)) {
			this.observers.add(flyable);

			//DEBUG
			System.out.println("Tower says: " + "<name>" + " registred to weather tower.");
		}
	}

    //Delete the given flyable from the tower's Flyable list
	public void unregister(Flyable flyable) {
		int indexToDel = observers.indexOf(flyable);
		if (indexToDel != -1){
			this.observers.remove(indexToDel);

			//DEBUG
			System.out.println("Tower says: " + "<name>" + " unregistred to weather tower.");
		}
	}

    // ?
	protected void conditionsChanged() {
        System.out.println("Conditions changed");
	}
}