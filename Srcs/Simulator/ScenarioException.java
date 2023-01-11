package Srcs.Simulator;

public class ScenarioException extends Exception {
    public ScenarioException() {
        super("Error :");
    }

    public ScenarioException(String message) {
        super("Error :" + message);
    }
}
