package comp261.assignment1.handler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import comp261.assignment1.state.StateManager;

public class MouseHandler extends MouseAdapter {
	private StateManager sm;
	
	public MouseHandler(StateManager sm) {
		this.sm = sm;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		sm.mousePressed(e.getX(), e.getY());
	}
}
