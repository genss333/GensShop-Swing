package Components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Navbar extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel icon;
	ImageIcon img;
	
	public Navbar() {
		this.setPreferredSize(new Dimension(0,50));
		this.setBackground(new Color(0x212121));
		img = new ImageIcon("menu.png");
		icon = new JLabel();
		icon.setIcon(img);
		
		this.setLayout(new BorderLayout());
		this.add(icon,BorderLayout.WEST); 
	}
	
	public void panit (Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		  
		g2D.setPaint(Color.orange);
		 g2D.drawOval(0, 0, 100, 100);
		 g2D.fillOval(0, 0, 100, 100);
		
	}
	
}
