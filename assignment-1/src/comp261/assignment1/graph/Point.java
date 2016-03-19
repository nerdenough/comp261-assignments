package comp261.assignment1.graph;

import comp261.assignment1.helper.LocationHelper;

public class Point {
	private double lat, lon, x, y, offsetX, offsetY;
	
	public Point(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
		x = LocationHelper.getLocX(lat);
		y = LocationHelper.getLocY(lon);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getLocX() {
		return x + offsetX;
	}
	
	public double getLocY() {
		return y + offsetY;
	}
	
	public void setOffsetX(double offsetX) {
		this.offsetX = offsetX;
	}
	
	public void setOffsetY(double offsetY) {
		this.offsetY = offsetY;
	}
}
