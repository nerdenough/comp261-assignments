package comp261.assignment1.state;

import java.awt.Graphics2D;

/**
 * State is an abstract class that is extended by all states, allowing them to
 * share methods that can be called by the StateManager.
 * 
 * @author Brendan Goodenough
 */

public abstract class State {
	/**
	 * Updates the state for the current frame.
	 */
	public abstract void update();

	/**
	 * Renders the state to the screen.
	 * 
	 * @param g2 - Graphics2D object for rendering
	 */
	public abstract void render(Graphics2D g2);
}
