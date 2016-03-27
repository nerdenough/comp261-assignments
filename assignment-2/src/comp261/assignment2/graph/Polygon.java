package comp261.assignment2.graph;

import java.awt.Graphics2D;
import java.util.List;

public class Polygon {
	private int type;
	private List<Point> points;
	
	public Polygon(int type, List<Point> points) {
		this.type = type;
		this.points = points;
	}
	
	public void render(Graphics2D g2) {
		int[] xPoints = new int[points.size()];
		int[] yPoints = new int[points.size()];
		
		for (int i = 0; i < points.size(); i++) {
			Point point = points.get(i);
			xPoints[i] = (int) (point.getX() * (Graph.zoom / 100) + Graph.graphX);
			yPoints[i] = (int) (point.getY() * (Graph.zoom / 100) + Graph.graphY);
		}
		
		g2.fillPolygon(xPoints, yPoints, xPoints.length);
	}
	
	public int getType() {
		return type;
	}
}
