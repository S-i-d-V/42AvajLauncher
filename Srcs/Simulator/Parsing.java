package Srcs.Simulator;

//Imports
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Srcs.Aircrafts.Flyable;
import Srcs.Aircrafts.AircraftFactory;

//Parse the scenario
public class Parsing {

    //Parse the scenario and throw exceptions in case of errors.
    public ArrayList<Flyable> parseScenario(String path) throws ScenarioException {
        //Check if the path exist
        Path pathToScenario = Path.of(path);
        List<String> lines;
        try {
            lines = Files.readAllLines(pathToScenario);
        }
        catch (Exception e) {
            //If not throw an exception
            throw new ScenarioException("Failed to read file: " + path);
        }

        //Init an Array of Flyable and an iterator to navigate through the lines
        ArrayList<Flyable> flyables = new ArrayList<Flyable>();
        Iterator<String> it = lines.iterator();

        //Parse the number of times the simulation must change the weather for every Aircraft.
        int nbOfLoop = 0;
        try {
            nbOfLoop = Integer.parseInt(it.toString());
        }
        catch (NumberFormatException e) {
            //Throw an error if the first line is not a number
            throw new ScenarioException("The first line should be a positive integer");
        }
        //Throw and error if the number is negative or 0
        if (nbOfLoop <= 0)
            throw new ScenarioException("The first line should be a positive integer");

        //Parse every lines and fill the Flyable Array
            //Throw an error if the coordinates integers are NaN, negatives or 0.
        while (it.hasNext()) {
            //I split my line in tokens
            String[] lineTokens = it.toString().split(" ");
            //Throw an error if the line dont respect the format TYPE NAME LONGITUDE LATITUDE HEIGHT
            if (lineTokens.length != 5)
                throw new ScenarioException("Bad syntax: Aircrafts should respect this format: TYPE NAME LONGITUDE LATITUDE HEIGHT");

            //Retrieve the type of the Aircraft
            String type = lineTokens[0];
            //Throw error if the TYPE is not a Baloon, Helicopter or JetPlane.
            if (lineTokens[0] != "Baloon" && lineTokens[0] != "Helicopter" && lineTokens[0] != "JetPlane")
                throw new ScenarioException("The Aircraft's type should be Baloon, Helicopter or JetPlane");

            //Retrieve the name of the Aircraft
            String name = lineTokens[1];

            //Retrieve the coordinates of the Aircraft
            int longitude, latitude, height = 0;
            try {
                longitude = Integer. parseInt(lineTokens[2]);
                latitude = Integer. parseInt(lineTokens[3]);
                height = Integer. parseInt(lineTokens[4]);
            }
            catch (NumberFormatException e) {
                //Throw error if the coordinates are not postives integers
                throw new ScenarioException("LONGITUDE LATITUDE HEIGHT should be positive integers");
            }
            
            //Create a new flyable in the Flyable array
            Flyable newAircraft = AircraftFactory.newAircraft(type, name, longitude, latitude, height);
            flyables.add(newAircraft);

            //Incrementing the iterator
            it.next();
        }

        return (flyables);
    }
}
