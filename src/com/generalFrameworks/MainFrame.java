package com.generalFrameworks;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 8315570791777913879L;

	public static void main(String[] args) {

		MainPanel panel = new MainPanel();

		MainFrame main = new MainFrame();
		main.getContentPane().add(panel);
		main.pack();
		main.setDefaultCloseOperation(EXIT_ON_CLOSE);
		main.setResizable(true);
		main.setVisible(true);
		panel.start();

	}
}
