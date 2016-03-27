package comp261.assignment2.handler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import comp261.assignment2.state.StateManager;

public class MouseMotionHandler extends MouseMotionAdapter {
	private StateManager sm;
	
	public MouseMotionHandler(StateManager sm) {
		this.sm = sm;
	}
	
	public void mouseDragged(MouseEvent e) {
		sm.mouseDragged(e.getX(), e.getY());
	}
}
