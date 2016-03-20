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
	public boolean[] keys, buttons;
	public int mouseX, mouseY, prevMouseX, prevMouseY;
	
	/**
	 * Constructs a new State object and initialises any generic state variables
	 * and objects.
	 */
	public State() {
		keys = new boolean[10];
		buttons = new boolean[10];
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
		if (key == KeyEvent.VK_E) keys[4] = true;
		if (key == KeyEvent.VK_Q) keys[5] = true;
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
		if (key == KeyEvent.VK_E) keys[4] = false;
		if (key == KeyEvent.VK_Q) keys[5] = false;
	}
	
	public void mousePressed(int x, int y) {
		mouseX = x;
		mouseY = y;
	}
	
	/**
	 * Handles a mouse drag event
	 * @param x - x drag coord
	 * @param y - y drag coord
	 */
	public void mouseDragged(int x, int y) {
		mouseX = x;
		mouseY = y;
	}
	
	public void buttonPressed(String cmd) {
		buttons[0] = cmd == "up";
		buttons[1] = cmd == "down";
		buttons[2] = cmd == "left";
		buttons[3] = cmd == "right";
		buttons[4] = cmd == "zoomIn";
		buttons[5] = cmd == "zoomOut";
	}
}
