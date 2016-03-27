package comp261.assignment2.graph;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Set;

import comp261.assignment2.Program;
import comp261.assignment2.helper.LocationHelper;

public class Node {
	private int id;
	private double lat, lon, x, y;
	private boolean view, selected;
	
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
		Color col = g2.getColor();
		
		if (selected) {
			g2.setColor(Color.BLACK);
			for (Segment segment : segments) {
				segment.render(g2);
			}
		}
		
		g2.setColor(col);
		g2.fillRect((int) (getLocX() - 2), (int) (getLocY() - 2), 4, 4);
	}
	
	public Set<Segment> getSegments() {
		return segments;
	}
	
	public boolean inView() {
		return view;
	}
	
	public boolean isSelected() {
		return selected;
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
	
	public float getLocX() {
		return (float) (x * (Graph.zoom / 100) + Graph.graphX);
	}
	
	public float getLocY() {
		return (float) (y * (Graph.zoom / 100) + Graph.graphY);
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
