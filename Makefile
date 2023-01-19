.SILENT:

#COLORS OUTPUTS
RED =`tput setaf 1`
GREEN =`tput setaf 2`
YELLOW =`tput setaf 3`
CLEAR =`tput sgr0`

#FILES
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
OBJECTS = $(SOURCES:.java=.class)

#RULES
.PHONY: all compile run clean fclean re

compile: $(SOURCES)
	echo "$(YELLOW)Compiling binary files from java files..$(CLEAR)"
	javac $(SOURCES)

run: $(OBJECTS)
	echo "$(GREEN)Executing Avaj-Launcher..$(CLEAR)"
	java Avaj.Simulator.Simulation scenario.txt

all: compile run

clean:
	echo "$(RED)Cleaning binary files..$(CLEAR)"
	rm -rf $(OBJECTS)

fclean: clean
	echo "$(RED)Cleaning simulation.txt..$(CLEAR)"
	rm -rf simulation.txt

re: fclean all
