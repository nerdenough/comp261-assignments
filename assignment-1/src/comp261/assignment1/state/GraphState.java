package comp261.assignment1.state;

import java.awt.Graphics2D;

import javax.swing.JOptionPane;

import comp261.assignment1.graph.Graph;

public class GraphState extends State {
	private Graph graph;
	
	public GraphState() {
		super();
	}
	
	public void init(String directory) {
		try {
			graph = new Graph(directory);
		} catch (Exception e) {
			String error = "No valid data found in this directory!";
			JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see comp261.assignment1.state.State#update()
	 */
	public void update() {
		if (graph != null) {
			double deltaX = 0;
			double deltaY = 0;
			
			if (keys[0]) deltaY += 4;
			if (keys[1]) deltaY -= 4;
			if (keys[2]) deltaX += 4;
			if (keys[3]) deltaX -= 4;
			if (keys[4]) Graph.zoom += 1;
			if (keys[5]) Graph.zoom -= 1;
			
			if (buttons[0]) deltaY += 400 / (Graph.zoom / 100);
			if (buttons[1]) deltaY -= 400 / (Graph.zoom / 100);
			if (buttons[2]) deltaX += 400 / (Graph.zoom / 100);
			if (buttons[3]) deltaX -= 400 / (Graph.zoom / 100);
			if (buttons[4]) Graph.zoom += 10;
			if (buttons[5]) Graph.zoom -= 10;
			
			deltaX += prevMouseX - mouseX;
			deltaY += prevMouseY - mouseY;
			
			prevMouseX = mouseX;
			prevMouseY = mouseY;
			
			Graph.graphX += deltaX;
			Graph.graphY += deltaY;
			
			graph.update();
		
			for (int i = 0; i < buttons.length; i++) {
				buttons[i] = false;
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see comp261.assignment1.state.State#render(java.awt.Graphics2D)
	 */
	public void render(Graphics2D g2) {
		if (graph != null) {
			graph.render(g2);
		}
	}
	
	@Override
	public void mousePressed(int x, int y) {
		graph.mousePressed(x, y);
	}
}
