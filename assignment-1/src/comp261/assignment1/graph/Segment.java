package comp261.assignment1.graph;

import java.awt.Graphics2D;

public class Segment {
	private int id;
	private Node n1, n2;
	
	public Segment(int id, Node n1, Node n2) {
		this.id = id;
		this.n1 = n1;
		this.n2 = n2;
	}
	
	public void render(Graphics2D g2) {
		g2.drawLine((int) n1.getLocX(), (int) n1.getLocY(), (int) n2.getLocX(), (int) n2.getLocY());
	}
}
