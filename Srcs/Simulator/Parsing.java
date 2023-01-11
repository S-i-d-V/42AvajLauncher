package Srcs.Simulator;

//Imports
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import Srcs.Aircrafts.Flyable;

//Parse the scenario
public class Parsing {

    //Parse the scenario and throw exceptions in case of errors.
    public ArrayList<Flyable> parseScenario(String path) {
        //Check if the path exist
        Path pathToScenario = Path.of(path);
        List<String> lines;
        try {
            lines = Files.readAllLines(pathToScenario);
        }
        catch (IOException e){
            throw new ScenarioException("Failed to read file: " + e);
        }

        ArrayList<Flyable> flyables = new ArrayList<Flyable>();
        Iterator<String> it = lines.iterator();
        while (it.hasNext()){
            it.next();
        }


        //Parse the numbers of times the simulation must change the weather for every Aircraft.
            //Throw an error if the first line is not a number
            //Throw an error if the number is negative or 0
        //Parse every Aircraft in the simulation with their type, name and coordinates.
            //Throw an error if the line dont respect the format TYPE NAME LONGITUDE LATITUDE HEIGHT
            //Throw error if the TYPE is not a Baloon, Helicopter or JetPlane.
            //Throw an error if the coordinates integers are NaN, negatives or 0.

        return (flyables);
    }
}
