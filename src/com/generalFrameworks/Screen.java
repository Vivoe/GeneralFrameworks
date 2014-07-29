package com.generalFrameworks;

import java.awt.Graphics;
import java.util.HashMap;

/**
 * @author Justin Ng
 * 
 *         All game objects will be placed in a screen. Different screens
 *         include rooms and pause menus. The methods update, keyEvent, and
 *         paint will be called every frame. When creating new a new Screen,
 *         take the current ID and add one. Screens with higher priority will be
 *         drawn infront of others.
 */
public abstract class Screen implements Comparable<Screen> {
	private int priority;
	private final int ID;

	/**
	 * If the screen uses keyEvents, add all keys used to the HashMap by calling
	 * keys.put(KeyEvent.VK_'KEY', false);
	 * 
	 * @param id
	 *            Identifier of the Screen.
	 * @param keys
	 *            HashMap containing key presses.
	 */
	public Screen(int id, HashMap<Integer, Boolean> keys) {
		ID = id;
		priority = 0;
		registerKeys(keys);
	}

	public final void updateScreen(double dt, HashMap<Integer, Boolean> keys) {
		keyEvent(keys);
		update(dt);
	}

	protected abstract void update(double dt);

	/**
	 * All keys used in the class must be registered to prevent
	 * NullPointerExceptions.
	 */
	protected abstract void registerKeys(HashMap<Integer, Boolean> keys);

	/**
	 * Get each keyEvent by using an if statement. Get the desired key by
	 * calling keys.get(KeyEvent.VK_'KEY');
	 * 
	 * @param keys
	 */
	public abstract void keyEvent(HashMap<Integer, Boolean> keys);

	/**
	 * Each object is to have their own paint method, and will draw their own
	 * object.
	 */
	public abstract void paint(Graphics g);

	@Override
	public int compareTo(Screen o) {
		if (o.getPriority() > priority) {
			return 1;
		} else if (o.getPriority() == priority) {
			return 0;
		} else {
			return -1;
		}
	}

	public int getID() {
		return ID;
	}

	public int getPriority() {
		return 0;
	}

	public void setPriority(int n) {
		priority = n;
	}

}
