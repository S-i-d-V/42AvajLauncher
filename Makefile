SOURCES = Srcs/Aircrafts/Aircraft.java \
		Srcs/Aircrafts/AircraftFactory.java \
		Srcs/Aircrafts/Baloon.java \
		Srcs/Aircrafts/Flyable.java \
		Srcs/Aircrafts/Helicopter.java \
		Srcs/Aircrafts/JetPlane.java \
		Srcs/Tower/Tower.java \
		Srcs/Tower/WeatherTower.java \
		Srcs/Simulator/ScenarioException.java \
		Srcs/Simulator/Simulation.java \
		Srcs/Coordinates.java \
		Srcs/WeatherProvider.java


all: $(SOURCES)
	javac $(SOURCES)

clean:
	find . -name "*.class" -type f -delete

fclean: clean

re: fclean all