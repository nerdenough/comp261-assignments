package comp261.assignment2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import comp261.assignment2.graph.Graph;
import comp261.assignment2.gui.Window;
import comp261.assignment2.handler.KeyHandler;
import comp261.assignment2.handler.MouseHandler;
import comp261.assignment2.handler.MouseMotionHandler;
import comp261.assignment2.state.GraphState;
import comp261.assignment2.state.StateManager;

/**
 * COMP261 Assignment 2
 * The Auckland Road System
 * 
 * Program holds the main entry point for this assignment. Extending Canvas and
 * implementing runnable, it also provides the ability to update and draw
 * graphics to the screen at a target of 60fps.
 * 
 * @author Brendan Goodenough
 */

@SuppressWarnings("serial")
public class Program extends Canvas implements Runnable {
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static boolean debug = true;

	private StateManager sm;
	private BufferedImage image;

	private Thread thread;
	private boolean running;
	private int fps = 60;

	/**
	 * Constructs a new Program object by setting the canvas dimensions and
	 * building the GUI.
	 */
	public Program() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		sm = new StateManager();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		
		new Window(this);

		addKeyListener(new KeyHandler(sm));
		addMouseListener(new MouseHandler(sm));
		addMouseMotionListener(new MouseMotionHandler(sm));
		
		setFocusable(true);
		requestFocus();
	}
	
	public void loadData(String directory) {
		sm.setState(1);
		GraphState state = (GraphState) sm.getState();
		state.init(directory);
	}

	/**
	 * Updates the canvas for the current frame by updating the state manager.
	 */
	public void update() {
		sm.update();
	}

	/**
	 * Creates a buffer strategy with triple buffering, sets anti-aliasing on
	 * the graphics. Graphics are drawn to the screen through the state
	 * manager's render method.
	 * 
	 * Debug information is drawn to the screen if debug mode is enabled.
	 */
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, WIDTH, HEIGHT);
		
		sm.render(g2);

		g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);

		if (debug) {
			// DEBUG: Show FPS Counter
			g.setColor(Color.BLACK);
			g.drawString(String.format("FPS: %S", fps), 10, 20);
			g.drawString(String.format("Zoom: %1$,.1f", Graph.zoom / 100), 10, 40);
		}

		g.dispose();
		bs.show();
	}

	/**
	 * Loops through updating and rendering the canvas while the program is
	 * running. Ideally these updates will be occurring at the target rate of
	 * 60fps.
	 * 
	 * I have used this gameloop in various personal projects and my SWEN222
	 * Group Project from 2015. It works, even if it isn't very optimised.
	 */
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0;
		double ns = 1000000000D / 60D;
		int frames = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				update();
				render();
				delta--;
				frames++;
			}

			// DEBUG: Set FPS Counter
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				fps = frames;
				frames = 0;
			}
		}
	}
	
	public void buttonPressed(String cmd) {
		sm.buttonPressed(cmd);
	}
	
	public void searchFor(String query) {
		sm.searchFor(query);
	}

	/**
	 * Initialises and starts the thread for the canvas.
	 */
	private synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	/*
	 * Main entry point for the program.
	 */
	public static void main(String[] args) {
		Program program = new Program();
		program.start();
	}
}
