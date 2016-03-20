package comp261.assignment1.graph;

import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Set;

import comp261.assignment1.Program;
import comp261.assignment1.helper.LocationHelper;

public class Node {
	private int id;
	private double lat, lon, x, y;
	private boolean view;
	
	private Set<Segment> segments;
	
	public Node(int id, double lat, double lon) {
		this.id = id;
		this.lat = lat;
		this.lon = lon;
		
		x = LocationHelper.getLocX(lat);
		y = LocationHelper.getLocY(lon);
		
		segments = new HashSet<>();
	}
	
	public void update() {
		boolean viewX = getLocX() > 0 && getLocX() <= Program.WIDTH;
		boolean viewY = getLocY() > 0 && getLocY() <= Program.HEIGHT;
		view = viewX && viewY;
	}
	
	public void render(Graphics2D g2) {
		g2.fillRect((int) (x * (Graph.zoom / 100) + Graph.graphX - 2), (int) (y * (Graph.zoom / 100) + Graph.graphY - 2), 4, 4);
	}
	
	public Set<Segment> getSegments() {
		return segments;
	}
	
	public boolean inView() {
		return view;
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
		return x * (Graph.zoom / 100) + Graph.graphX;
	}
	
	public double getLocY() {
		return y * (Graph.zoom / 100) + Graph.graphY;
	}
}
