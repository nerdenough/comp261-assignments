package comp261.assignment1.state;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/**
 * StateManager is designed to control which state should be updated and
 * rendered.
 * 
 * @author Brendan Goodenough
 */

public class StateManager {
	public List<State> states;
	private int state = 0;

	/**
	 * Constructs a new StateManager by creating a new list of possible states.
	 */
	public StateManager() {
		states = new ArrayList<>();
		states.add(new DefaultState());
		states.add(new GraphState());
	}

	/**
	 * Updates the current state.
	 */
	public void update() {
		states.get(state).update();
	}

	/**
	 * Renders the current state.
	 * 
	 * @param g2 - Graphics2D object for rendering
	 */
	public void render(Graphics2D g2) {
		states.get(state).render(g2);
	}

	/**
	 * Returns the current state.
	 * 
	 * @return current state
	 */
	public State getState() {
		return states.get(state);
	}

	/**
	 * Sets the state to be updated and rendered.
	 * 
	 * @param state - new state
	 */
	public void setState(int state) {
		this.state = state;
	}

	public void keyPressed(int key) {
		states.get(state).keyPressed(key);
	}

	public void keyReleased(int key) {
		states.get(state).keyReleased(key);
	}
	
	public void mousePressed(int x, int y) {
		states.get(state).mousePressed(x, y);
	}

	public void mouseDragged(int x, int y) {
		states.get(state).mouseDragged(x, y);
	}

	public void buttonPressed(String cmd) {
		states.get(state).buttonPressed(cmd);
	}
}
