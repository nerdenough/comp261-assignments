package comp261.assignment1.graph;

import comp261.assignment1.helper.LocationHelper;

public class Point {
	private float x, y;
	
	public Point(double lat, double lon) {
		x = LocationHelper.getLocX(lat);
		y = LocationHelper.getLocY(lon);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
}
