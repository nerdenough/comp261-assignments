package comp261.assignment1.graph;

import java.awt.Graphics2D;
import java.util.List;

public class Segment {
	private int id;
	private Node n1, n2;
	private List<Point> points;
	
	public Segment(int id, Node n1, Node n2, List<Point> points) {
		this.id = id;
		this.n1 = n1;
		this.n2 = n2;
		this.points = points;
	}
	
	public void render(Graphics2D g2) {
		if (n1.inView() || n2.inView()) {
			int[] xPoints = new int[points.size()];
			int[] yPoints = new int[points.size()];
			
			for (int i = 0; i < points.size(); i++) {
				Point point = points.get(i);
				xPoints[i] = (int) (point.getX() * (Graph.zoom / 100) + Graph.graphX);
				yPoints[i] = (int) (point.getY() * (Graph.zoom / 100) + Graph.graphY);
			}
					
			g2.drawPolyline(xPoints, yPoints, xPoints.length);
		}
	}
}
