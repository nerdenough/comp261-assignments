package comp261.assignment1.state;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 * State is an abstract class that is extended by all states, allowing them to
 * share methods that can be called by the StateManager.
 * 
 * @author Brendan Goodenough
 */

public abstract class State {
	public boolean[] keys;
	
	/**
	 * Constructs a new State object and initialises any generic state variables
	 * and objects.
	 */
	public State() {
		keys = new boolean[10];
	}

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
	
	/**
	 * Handles a key press event.
	 * @param key - key code
	 */
	public void keyPressed(int key) {
		if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) keys[0] = true;
		if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) keys[1] = true;
		if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) keys[2] = true;
		if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) keys[3] = true;
	}
	
	/**
	 * Handles a key release event.
	 * @param key - key code
	 */
	public void keyReleased(int key) {
		if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) keys[0] = false;
		if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) keys[1] = false;
		if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) keys[2] = false;
		if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) keys[3] = false;
	}
}
