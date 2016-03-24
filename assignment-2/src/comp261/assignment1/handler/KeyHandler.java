package comp261.assignment1.handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import comp261.assignment1.state.StateManager;

public class KeyHandler extends KeyAdapter {
	private StateManager sm;
	
	public KeyHandler(StateManager sm) {
		this.sm = sm;
	}
	
	@Override
	public void keyPressed(KeyEvent k) {
		sm.keyPressed(k.getKeyCode());
	}
	
	@Override
	public void keyReleased(KeyEvent k) {
		sm.keyReleased(k.getKeyCode());
	}
}
