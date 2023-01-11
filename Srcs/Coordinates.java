package Srcs;

//Class that store coordinates
public class Coordinates {
	private int longitude;
	private int latitude;
	private int height;

    //Constructor
	public Coordinates(int longitude, int latitude, int height) {
		this.longitude = longitude;
		this.latitude = latitude;
		if (height > 100)
			this.height = 100;
		else
			this.height = height;
	}
	
    //Longitude getter
	public int getLongitude() {
		return (this.longitude);
	}

    //Latitude getter
	public int getLatitude() {
		return (this.latitude);
	}

    //Height getter
	public int getHeight() {
		return (this.height);
	}
}