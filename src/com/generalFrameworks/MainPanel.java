package com.generalFrameworks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JPanel;

public class MainPanel extends JPanel implements KeyListener, MouseListener {
	private static final long serialVersionUID = -6894784795625268479L;
	static boolean looping, running;
	static int fps;
	static long pastTime;
	static double fpsTimer;
	final long TARGET_FPS = 120;
	final long MIN_FPS = 30;
	final long MAX_TIME = 1000000000 / MIN_FPS;
	final long OPTIMAL_TIME = 1000000000 / TARGET_FPS * 2;
	final static long startTime = System.nanoTime();
	final static int SCREEN_WIDTH = 1920;
	final static int SCREEN_HEIGHT = 1080;

	// States if key is pressed or not.
	HashMap<Integer, Boolean> keys;
	// Contains all screens.
	ArrayList<Screen> screens;

	public MainPanel() {
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
		addMouseListener(this);
		setPreferredSize(new Dimension(SCREEN_WIDTH - 10, SCREEN_HEIGHT - 10));
		setOpaque(true);
		setBackground(Color.WHITE);
		looping = true;

		keys = new HashMap<Integer, Boolean>();
		screens = new ArrayList<Screen>();

		screens.add(new BaseScreenExample(0, keys));
	}

	/**
	 * Updates game logic in all screens.
	 * 
	 * @param dt
	 *            Delta time.
	 */
	public void update(double dt) {
		for (Screen s : screens) {
			s.updateScreen(dt, keys);
		}
	}

	/**
	 * Repaints all screens.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Screen s : screens) {
			s.paint(g);
		}
	}

	/**
	 * Returns false if a screen with the same ID exists. The new screen will
	 * not be added.
	 * 
	 * @param s
	 * @return
	 */
	public boolean addScreen(Screen s) {
		for (Screen screen : screens) {
			if (screen.getID() == s.getID()) {
				return false;
			}
		}
		screens.add(s);
		Collections.sort(screens);
		return true;
	}

	public boolean removeScreen(int id) {
		for (int i = 0; i < screens.size(); i++) {
			if (screens.get(i).getID() == id) {
				screens.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys.put(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys.put(e.getKeyCode(), false);
	}

	public void start() {
		running = true;
		pastTime = System.nanoTime();
		gameLoop();
	}

	public void resume() {
		running = true;
	}

	public static void stop() {
		running = false;
	}

	public void gameLoop() {
		while (looping) {
			long currentTime = System.nanoTime();
			double updateLength = currentTime - pastTime;
			pastTime = currentTime;
			try {
				if (updateLength < OPTIMAL_TIME) {
					Thread.sleep((long) ((OPTIMAL_TIME - updateLength) / 1000000));
				} else if (updateLength > MAX_TIME) {
					updateLength = MAX_TIME;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (running) {
				update(updateLength / 1000000000);
			}
			repaint();
			fps++;
			fpsTimer += updateLength;
			if (fpsTimer >= 1000000000.0) {
				System.out.println("FPS:" + fps);
				fps = 0;
				fpsTimer = 0;
			}

		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(SCREEN_WIDTH - 10, SCREEN_HEIGHT - 10);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
