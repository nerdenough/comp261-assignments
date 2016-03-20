package comp261.assignment1.gui;

import java.awt.Canvas;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame {
	public Window(Canvas canvas) {
		super("The Auckland Road System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		add(canvas);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
