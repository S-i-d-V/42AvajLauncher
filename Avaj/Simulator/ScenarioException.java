package Avaj.Simulator;

public class ScenarioException extends Exception {
    public ScenarioException(String message) {
        super("Error: " + message);
    }
}
