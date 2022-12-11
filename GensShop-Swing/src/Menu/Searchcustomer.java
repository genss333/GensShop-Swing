package Menu;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

public class Searchcustomer extends JMenuItem {
	public Searchcustomer() {
		ImageIcon imgitem1 = new ImageIcon("./imge/searching.png");
		setBackground(Color.white);
		setPreferredSize(new Dimension(170, 50));
		setIconTextGap(12);
		setForeground(Color.DARK_GRAY);
		setIcon(imgitem1);
	}
}
