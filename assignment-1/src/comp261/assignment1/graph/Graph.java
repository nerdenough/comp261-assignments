package comp261.assignment1.graph;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;
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
			segments = FileHelper.getSegments(directory, nodes, roads);
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
		g2.setColor(new Color(200, 200, 200));
		for (Segment segment : segments) {
			segment.render(g2);
		}
		
		Color colNode = new Color(100, 50, 50);
		Color colNodeSelected = new Color(255, 50, 50);
		for (Map.Entry<Integer, Node> entry : nodes.entrySet()) {
			Node node = entry.getValue();
			if (node.inView()) {
				if (node.isSelected()) {
					g2.setColor(colNodeSelected);
				} else {
					g2.setColor(colNode);
				}
				
				node.render(g2);
				
				if (node.isSelected()) {
					g2.setColor(Color.WHITE);
					g2.fillRect(Program.WIDTH - 240, 0, 240, 240);
					g2.setColor(Color.RED);
					g2.drawRect(Program.WIDTH - 240, 0, 239, 240);
					
					int i = 0;
					String[] names = new String[node.getSegments().size()];
					g2.setColor(Color.RED);
					g2.drawString("Node: " + node.getId(), Program.WIDTH - 230, 20);
					for (Segment segment : node.getSegments()) {
						if (!Arrays.asList(names).contains(segment.getRoad().getLabel())) {
							String name = segment.getRoad().getLabel();
							String city = segment.getRoad().getCity();
							names[i] = name;
							g2.drawString(name + ", " + city, Program.WIDTH - 230, 40 + 20 * i);
							i++;
						}
					}
				}
			}
		}
	}
	
	public void mousePressed(int x, int y) {
		for (Map.Entry<Integer, Node> entry : nodes.entrySet()) {
			Node node = entry.getValue();
			node.setSelected(false);
			
			float nodeX = node.getLocX();
			float nodeY = node.getLocY();
			
			if (x >= nodeX - 5 && x <= nodeX + 5) {
				if (y >= nodeY - 5 && y <= nodeY + 5) {
					node.setSelected(true);
				}
			}
		}
	}
	
	public void searchFor(String query) {
		for (Segment segment : segments) {
			segment.setHighlighted(false);
			
			if (segment.getRoad().getLabel().toLowerCase().equals(query.toLowerCase())) {
				System.out.println("Match! Road is highlighted");
				segment.setHighlighted(true);
			}
		}
	}
}
