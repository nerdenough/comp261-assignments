package comp261.assignment1.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import comp261.assignment1.Program;

@SuppressWarnings("serial")
public class Window extends JFrame implements ActionListener {
	private Program program;
	
	public Window(Program program) {
		super("The Auckland Road System");
		this.program = program;
		
		init();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void init() {
		JPanel container = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel(new BorderLayout());
		JPanel topLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel topRightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JButton btnLoad = new JButton("Load");
		JButton btnUp = new JButton("Up");
		JButton btnDown = new JButton("Down");
		JButton btnLeft = new JButton("Left");
		JButton btnRight = new JButton("Right");
		JButton btnZoomIn = new JButton("Zoom In");
		JButton btnZoomOut = new JButton("Zoom Out");
		JButton btnSearch = new JButton("Search");
		
		JTextField txtSearch = new JTextField(20);
		
		btnLoad.setActionCommand("load");
		btnUp.setActionCommand("up");
		btnDown.setActionCommand("down");
		btnLeft.setActionCommand("left");
		btnRight.setActionCommand("right");
		btnZoomIn.setActionCommand("zoomIn");
		btnZoomOut.setActionCommand("zoomOut");
		btnSearch.setActionCommand("search");
		
		btnLoad.addActionListener(this);
		btnUp.addActionListener(this);
		btnDown.addActionListener(this);
		btnLeft.addActionListener(this);
		btnRight.addActionListener(this);
		btnZoomIn.addActionListener(this);
		btnZoomOut.addActionListener(this);
		btnSearch.addActionListener(this);
		
		topLeftPanel.add(btnLoad);
		topLeftPanel.add(btnUp);
		topLeftPanel.add(btnDown);
		topLeftPanel.add(btnLeft);
		topLeftPanel.add(btnRight);
		topLeftPanel.add(btnZoomIn);
		topLeftPanel.add(btnZoomOut);
		
		topRightPanel.add(txtSearch);
		topRightPanel.add(btnSearch);
		
		topPanel.add(topLeftPanel, BorderLayout.WEST);
		topPanel.add(topRightPanel, BorderLayout.EAST);
		container.add(topPanel, BorderLayout.NORTH);
		container.add(program, BorderLayout.CENTER);
		add(container);
	}

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		if (cmd == "load") {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = chooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					program.loadData(chooser.getSelectedFile().getAbsolutePath());
				} catch (Exception ex) {
					String error = "No valid data found in this directory!";
					JOptionPane.showConfirmDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (cmd == "search") {
			// TODO: Implement search
		} else {
			program.buttonPressed(cmd);
		}
	}
}
