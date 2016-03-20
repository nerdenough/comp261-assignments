package comp261.assignment1.graph;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import comp261.assignment1.Program;
import comp261.assignment1.helper.FileHelper;

public class Graph {
	private HashMap<Integer, Node> nodes;
	private HashMap<Integer, Road> roads;
	private Set<Segment> segments;
	
	public static final double CENTRE_LAT = -36.868816;
	public static final double CENTRE_LON = 174.744800;
	public static final double SCALE_LAT = 111.0;
	public static final double SCALE_LON = 88.649;
	
	public static double zoom = 100D;
	
	private double graphX, graphY;
	
	public Graph(String directory) throws Exception {
		try {
			nodes = FileHelper.getNodes(directory);
			roads = FileHelper.getRoads(directory);
			segments = FileHelper.getSegments(directory, nodes);
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	public void update() {
		for (Map.Entry<Integer, Node> entry : nodes.entrySet()) {
			Node node = entry.getValue();
			node.setOffsetX(Program.WIDTH / 2 - graphX / 2);
			node.setOffsetY(Program.HEIGHT / 2 - graphY / 2);
		}
		
		for (Segment segment : segments) {
			segment.setOffsetX(Program.WIDTH / 2 - graphX / 2);
			segment.setOffsetY(Program.HEIGHT / 2 - graphY / 2);
		}
	}
	
	public void render(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		for (Map.Entry<Integer, Node> entry : nodes.entrySet()) {
			Node node = entry.getValue();
			boolean viewX = node.getLocX() > 0 && node.getLocX() <= Program.WIDTH;
			boolean viewY = node.getLocY() > 0 && node.getLocY() <= Program.HEIGHT;
			
			if (viewX && viewY) {				
				node.render(g2);
			}
		}
		
		for (Segment segment : segments) {
			segment.render(g2);
		}
	}
	
	public double getGraphX() {
		return graphX;
	}
	
	public double getGraphY() {
		return graphY;
	}

	public void setGraphX(double graphX) {
		this.graphX = graphX;
	}
	
	public void setGraphY(double graphY) {
		this.graphY = graphY;
	}
}
