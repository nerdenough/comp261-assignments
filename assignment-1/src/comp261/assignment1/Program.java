package comp261.assignment1;

import java.awt.Canvas;

/**
 * COMP261 Assignment 1
 * The Auckland Road System
 * 
 * Program holds the main entry point for this assignment. Extending Canvas and
 * implementing runnable, it also provides the ability to update and draw
 * graphics to the screen at a target of 60fps.
 * 
 * @author Brendan Goodenough
 */

public class Program extends Canvas implements Runnable {
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;

	private Thread thread;
	private boolean running;
	private int fps;

	/**
	 * Updates the canvas for the current frame.
	 */
	public void update() {

	}

	/**
	 * Renders the current frame of the canvas to the screen.
	 */
	public void render() {

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

			// Update and render the current frame
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
