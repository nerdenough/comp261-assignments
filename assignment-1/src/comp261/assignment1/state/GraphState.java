package comp261.assignment1.state;

import java.awt.Graphics2D;

public class GraphState extends State {
	private float offsetX, offsetY;
	
	public GraphState() {
		super();
	}
	
	/*
	 * (non-Javadoc)
	 * @see comp261.assignment1.state.State#update()
	 */
	public void update() {
		if (keys[0]) offsetY++;
		if (keys[1]) offsetY--;	
		if (keys[2]) offsetX++;	
		if (keys[3]) offsetX--;	
	}
	
	/*
	 * (non-Javadoc)
	 * @see comp261.assignment1.state.State#render(java.awt.Graphics2D)
	 */
	public void render(Graphics2D g2) {
		// TODO: Render graph
	}
}
