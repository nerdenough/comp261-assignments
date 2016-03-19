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
	
	public void keyPressed(int key) {
		states.get(state).keyPressed(key);
	}
	
	public void keyReleased(int key) {
		states.get(state).keyReleased(key);
	}
	
	public void mouseDragged(int x, int y) {
		// TODO
	}
}
