SOURCES = Avaj/Aircrafts/Aircraft.java \
		Avaj/Aircrafts/AircraftFactory.java \
		Avaj/Aircrafts/Baloon.java \
		Avaj/Aircrafts/Flyable.java \
		Avaj/Aircrafts/Helicopter.java \
		Avaj/Aircrafts/JetPlane.java \
		Avaj/Tower/Tower.java \
		Avaj/Tower/WeatherTower.java \
		Avaj/Simulator/ScenarioException.java \
		Avaj/Simulator/Simulation.java \
		Avaj/Simulator/Logs.java \
		Avaj/Coordinates.java \
		Avaj/WeatherProvider.java


all: $(SOURCES)
	javac $(SOURCES)
	java Avaj.Simulator.Simulation scenario.txt

clean:
	find . -name "*.class" -type f -delete

fclean: clean
	rm -rf simulation.txt

re: fclean all