package Avaj.Simulator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Logs {

    public static void appendToLogFile(String data) {
        //Create the simulation.txt file
        try {
            File file = new File("simulation.txt");

            if (!file.exists())
                file.createNewFile();

            //The true parameter mean append data instead of erasing them to write new ones.
            FileWriter fw = new FileWriter(file, true);
            fw.write(data);
            fw.close();
        }
        catch (IOException e) {
            return;
        }
    }
}
