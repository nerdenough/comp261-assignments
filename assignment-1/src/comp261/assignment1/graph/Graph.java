package comp261.assignment1.graph;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
	
	public static float zoom = 200f;
	public static float graphX, graphY;
	
	public Graph(String directory) throws Exception {
		try {
			nodes = FileHelper.getNodes(directory);
			roads = FileHelper.getRoads(directory);
			segments = FileHelper.getSegments(directory, nodes);
			graphX = Program.WIDTH / 2;
			graphY = Program.HEIGHT / 2;
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	public void update() {
		for (Map.Entry<Integer, Node> entry : nodes.entrySet()) {
			Node node = entry.getValue();
			node.update();
		}
	}
	
	public void render(Graphics2D g2) {
		g2.setColor(new Color(120, 120, 120));
		for (Segment segment : segments) {
			segment.render(g2);
		}
		
		g2.setColor(new Color(200, 50, 50));
		for (Map.Entry<Integer, Node> entry : nodes.entrySet()) {
			Node node = entry.getValue();
			if (node.inView()) node.render(g2);
		}
	}
}
