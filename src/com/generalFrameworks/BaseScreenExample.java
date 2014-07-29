package com.generalFrameworks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Scanner;

public class BaseScreenExample extends Screen {

	
	public BaseScreenExample(int id, HashMap<Integer, Boolean> keys) {
		super(id, keys);
	}

	@Override
	protected void update(double dt) {

	}

	@Override
	protected void registerKeys(HashMap<Integer, Boolean> keys) {
	}

	@Override
	public void paint(Graphics g) {

	}

	@Override
	public void keyEvent(HashMap<Integer, Boolean> keys) {
		
	}

}