package Avaj.Simulator;

//Imports
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Avaj.Aircrafts.Flyable;
import Avaj.Tower.WeatherTower;
import Avaj.Aircrafts.AircraftFactory;

//Parse the scenario
public class Simulation {
    /***********************************************/
    /*                Class members                */
    /***********************************************/

    private static ArrayList<Flyable> flyables = new ArrayList<Flyable>();
    private static int nbOfLoop;

    /***********************************************/
    /*              Parsing methods                */
    /***********************************************/

    //Check if the file exist and store the lines in a List.
    private static List<String> readScenario(String path) throws ScenarioException {
        //Check if the file exist and read
        Path pathToScenario = Path.of(path);
        List<String> lines;
        //If the file exist, i store every lines in a String Array
        try {
            lines = Files.readAllLines(pathToScenario);
        }
        //If not throw an exception
        catch (Exception e) {
            throw new ScenarioException("Failed to read file: " + path);
        }
        return (lines);
    }

    //Parse the number of times the simulation must change the weather for every Aircraft.
    private static int retrieveNbOfLoop(String line) throws ScenarioException {
        nbOfLoop = 0;
        try {
            nbOfLoop = Integer.parseInt(line);
            //Throw and error if the number is negative or 0
            if (nbOfLoop <= 0)
                throw new ScenarioException("The first line should be a positive integer");
        }
        catch (NumberFormatException e) {
            //Throw an error if the first line is not a number
            throw new ScenarioException("The first line should be an integer");
        }

        return (nbOfLoop);
    }

    private static Flyable parseAircraftLine(String line) throws ScenarioException {
        String[] lineTokens = line.split(" ");
        //Throw an error if the line dont respect the format TYPE NAME LONGITUDE LATITUDE HEIGHT
        if (lineTokens.length != 5)
            throw new ScenarioException("Bad syntax: Respect this format: TYPE NAME LONGITUDE LATITUDE HEIGHT");

        //Retrieve the type of the Aircraft
        String type = lineTokens[0];
        //Throw error if the TYPE is not a Baloon, Helicopter or JetPlane.
        if (!(lineTokens[0].equals("Baloon") || lineTokens[0].equals("Helicopter") || lineTokens[0].equals("JetPlane")))
            throw new ScenarioException("The Aircraft's type should be Baloon, Helicopter or JetPlane");
        //Retrieve the name of the Aircraft
        String name = lineTokens[1];
        //Retrieve the coordinates of the Aircraft
        int longitude, latitude, height = 0;
        try {
            longitude = Integer. parseInt(lineTokens[2]);
            latitude = Integer. parseInt(lineTokens[3]);
            height = Integer. parseInt(lineTokens[4]);
            //Throw error if the coordinates are negatives
            if (longitude < 0 || latitude < 0 || height < 0)
                throw new ScenarioException("LONGITUDE LATITUDE HEIGHT should be positive integers");
        }
        catch (NumberFormatException e) {
            //Throw error if the coordinates are not integers
            throw new ScenarioException("LONGITUDE LATITUDE HEIGHT should be integers");
        }
        //Create a new Flyable and return it
        return (AircraftFactory.newAircraft(type, name, longitude, latitude, height));
    }

    //Parse the scenario and throw exceptions in case of errors.
    private static void parseScenario(String path) throws ScenarioException {
        List<String> lines = readScenario(path);
        Iterator<String> it = lines.iterator();

        //Retrieve number of loop the simulation has to do
        nbOfLoop = retrieveNbOfLoop(it.next());

        //Parse every lines and fill the Flyable Array
        while (it.hasNext()) {
            String line = it.next();

            Flyable newAircraft = parseAircraftLine(line);
            flyables.add(newAircraft);
        }
    }

    /***********************************************/
    /*            Simulation methods               */
    /***********************************************/



    //Run the simulation
    public static void main(String[] args) throws ScenarioException {
        //Check args
        if (args.length != 1)
			return;

        //Parse scenario and fill they Flyable Array
        parseScenario(args[0]);

        //Simulation
        WeatherTower weatherTower = new WeatherTower();

        for (int i = 0; i < nbOfLoop; i++){
            weatherTower.changeWeather();
        }
    }
}
