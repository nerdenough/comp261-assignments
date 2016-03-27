package comp261.assignment2.state;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import comp261.assignment2.Program;

public class DefaultState extends State {
	private BufferedImage gettingStarted;
	
	public DefaultState() {
		try {
			gettingStarted = ImageIO.read(Program.class.getResourceAsStream("/images/getting-started.png"));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Some resources are missing!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see comp261.assignment1.state.State#update()
	 */
	public void update() {
		// Nothing to do here
	}
	
	/*
	 * (non-Javadoc)
	 * @see comp261.assignment1.state.State#render(java.awt.Graphics2D)
	 */
	public void render(Graphics2D g2) {
		int x = Program.WIDTH / 2 - gettingStarted.getWidth() / 2;
		int y = Program.HEIGHT / 2 - gettingStarted.getHeight() / 2;
		g2.drawImage(gettingStarted, x, y, null);
	}
}
