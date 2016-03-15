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
		if (keys[0]) graph.setGraphY(graph.getGraphY() + 4);
		if (keys[1]) graph.setGraphY(graph.getGraphY() - 4);
		if (keys[2]) graph.setGraphX(graph.getGraphX() + 4);
		if (keys[3]) graph.setGraphX(graph.getGraphX() - 4);
		if (keys[4]) Graph.zoom += 10;
		if (keys[5]) Graph.zoom -= 10;
		
		graph.update();
	}
	
	/*
	 * (non-Javadoc)
	 * @see comp261.assignment1.state.State#render(java.awt.Graphics2D)
	 */
	public void render(Graphics2D g2) {
		graph.render(g2);
	}
}
