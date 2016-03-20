package comp261.assignment1.state;

import java.awt.Graphics2D;

import comp261.assignment1.graph.Graph;
import comp261.assignment1.graph.Graph.GraphType;

public class GraphState extends State {
	private Graph graph;
	
	public GraphState() {
		super();
		graph = new Graph(GraphType.SMALL);
	}
	
	/*
	 * (non-Javadoc)
	 * @see comp261.assignment1.state.State#update()
	 */
	public void update() {
		double deltaX = 0;
		double deltaY = 0;
		
		if (keys[0]) deltaY -= 4;
		if (keys[1]) deltaY += 4;
		if (keys[2]) deltaX -= 4;
		if (keys[3]) deltaX += 4;
		if (keys[4]) Graph.zoom += 1;
		if (keys[5]) Graph.zoom -= 1;
		
		if (buttons[0]) deltaY -= 4 * Graph.zoom;
		if (buttons[1]) deltaY += 4 * Graph.zoom;
		if (buttons[2]) deltaX -= 4 * Graph.zoom;
		if (buttons[3]) deltaX += 4 * Graph.zoom;
		if (buttons[4]) Graph.zoom += 10;
		if (buttons[5]) Graph.zoom -= 10;
		
		deltaX += prevMouseX - mouseX;
		deltaY += prevMouseY - mouseY;
		
		prevMouseX = mouseX;
		prevMouseY = mouseY;
		
		graph.setGraphX(graph.getGraphX() + deltaX);
		graph.setGraphY(graph.getGraphY() + deltaY);
		
		graph.update();
		
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = false;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see comp261.assignment1.state.State#render(java.awt.Graphics2D)
	 */
	public void render(Graphics2D g2) {
		graph.render(g2);
	}
}
