SOURCES = Srcs/Aircrafts/Aircraft.java \
		Srcs/Aircrafts/AircraftFactory.java \
		Srcs/Aircrafts/Baloon.java \
		Srcs/Aircrafts/Flyable.java \
		Srcs/Aircrafts/Helicopter.java \
		Srcs/Aircrafts/JetPlane.java \
		Srcs/Tower/Tower.java \
		Srcs/Tower/WeatherTower.java \
		Srcs/Coordinates.java \
		Srcs/WeatherProvider.java \

all: $(SOURCES)
	find * -name "*.java" > sources.txt
	javac $(SOURCES)

clean:
	find . -name "*.class" -type f -delete

fclean: clean

re: fclean all