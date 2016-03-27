package comp261.assignment2.graph;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

public class Segment {
	private int id;
	private boolean highlighted;
	
	private Node n1, n2;
	private Road road;
	private List<Point> points;
	
	public Segment(int id, Node n1, Node n2, Road road, List<Point> points) {
		this.id = id;
		this.n1 = n1;
		this.n2 = n2;
		this.road = road;
		this.points = points;
	}
	
	public void render(Graphics2D g2) {
		if (n1.inView() || n2.inView()) {
			Color col = g2.getColor();
			if (highlighted) g2.setColor(Color.RED);
			
			int[] xPoints = new int[points.size()];
			int[] yPoints = new int[points.size()];
			
			for (int i = 0; i < points.size(); i++) {
				Point point = points.get(i);
				xPoints[i] = (int) (point.getX() * (Graph.zoom / 100) + Graph.graphX);
				yPoints[i] = (int) (point.getY() * (Graph.zoom / 100) + Graph.graphY);
			}
					
			g2.drawPolyline(xPoints, yPoints, xPoints.length);
			g2.setColor(col);
		}
	}
	
	public Road getRoad() {
		return road;
	}
	
	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}
}
