package MyApp;

import javax.swing.JFrame;

public class Runnable {
	Admin_Menu panel;

	public Runnable() {

		panel = new Admin_Menu(this);
		panel.setSize(1200, 800);
		panel.setLocation(500, 100);
		panel.setVisible(true);
		panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new Runnable(); 
		
	}
}
