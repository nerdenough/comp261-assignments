package comp261.assignment1.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import comp261.assignment1.Program;

@SuppressWarnings("serial")
public class Window extends JFrame implements ActionListener {
	private Program program;
	
	public Window(Program program) {
		super("The Auckland Road System");
		this.program = program;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		init();
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void init() {
		JPanel container = new JPanel(new BorderLayout());
		JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JButton btnLoad = new JButton("Load");
		JButton btnUp = new JButton("Up");
		JButton btnDown = new JButton("Down");
		JButton btnLeft = new JButton("Left");
		JButton btnRight = new JButton("Right");
		JButton btnZoomIn = new JButton("Zoom In");
		JButton btnZoomOut = new JButton("Zoom Out");
		
		btnLoad.setActionCommand("load");
		btnUp.setActionCommand("up");
		btnDown.setActionCommand("down");
		btnLeft.setActionCommand("left");
		btnRight.setActionCommand("right");
		btnZoomIn.setActionCommand("zoomIn");
		btnZoomOut.setActionCommand("zoomOut");
		
		btnLoad.addActionListener(this);
		btnUp.addActionListener(this);
		btnDown.addActionListener(this);
		btnLeft.addActionListener(this);
		btnRight.addActionListener(this);
		btnZoomIn.addActionListener(this);
		btnZoomOut.addActionListener(this);
		
		top.add(btnLoad);
		top.add(btnUp);
		top.add(btnDown);
		top.add(btnLeft);
		top.add(btnRight);
		top.add(btnZoomIn);
		top.add(btnZoomOut);
		
		container.add(top, BorderLayout.NORTH);
		container.add(program, BorderLayout.CENTER);
		add(container);
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (cmd == "load") {
			// TODO: Show JFileChooser
		} else {
			program.buttonPressed(cmd);
		}
	}
}
