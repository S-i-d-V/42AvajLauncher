package Avaj.Tower;

//Imports
import java.util.ArrayList;

import Avaj.Aircrafts.Flyable;
import Avaj.Simulator.Logs;

//No constructor in the UML Diagram means this class is abstract + she's designed to be extended
//WeatherTower's parent class
public abstract class Tower {
    private ArrayList<Flyable> observers = new ArrayList<Flyable>();

    //Add the given flyable to the tower's Flyable list
	public void register(Flyable flyable) {
		if (!this.observers.contains(flyable)) {
			this.observers.add(flyable);
			Logs.appendToLogFile("Tower says: " + flyable.getType() + "#" + flyable.getName() + "(" + flyable.getId() + ")" + " registered to weather tower.\n");
		}
	}

    //Delete the given flyable from the tower's Flyable list
	public void unregister(Flyable flyable) {
		int indexToDel = observers.indexOf(flyable);
		if (indexToDel != -1){
			this.observers.remove(indexToDel);
			Logs.appendToLogFile("Tower says: " + flyable.getType() + "#" + flyable.getName() + "(" + flyable.getId() + ")" + " unregistered to weather tower.\n");
		}
	}

    // Update the weather for every Flyable in the list.
	protected void conditionsChanged() {
        for (int i = 0; i < observers.size(); i++) {
			observers.get(i).updateConditions();
		}
	}
}