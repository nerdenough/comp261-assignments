package comp261.assignment1.graph;

import java.awt.Graphics2D;

import comp261.assignment1.helper.LocationHelper;

public class Node {
	private int id;
	private double lat, lon, x, y, offsetX, offsetY;
	
	public Node(int id, double lat, double lon) {
		this.id = id;
		this.lat = lat;
		this.lon = lon;
		
		x = LocationHelper.getLocX(lat);
		y = LocationHelper.getLocY(lon);
	}
	
	public void render(Graphics2D g2) {
		g2.drawRect((int) (x + offsetX - 1), (int) (y + offsetY - 1), 3, 3);
	}
	
	public int getId() {
		return id;
	}
	
	public double getLat() {
		return lat;
	}
	
	public double getLon() {
		return lon;
	}
	
	public double getLocX() {
		return (x + offsetX) * (Graph.zoom / 100);
	}
	
	public double getLocY() {
		return (y + offsetY) * (Graph.zoom / 100);
	}
	
	public void setOffsetX(double offsetX) {
		this.offsetX = offsetX;
	}
	
	public void setOffsetY(double offsetY) {
		this.offsetY = offsetY;
	}
}
