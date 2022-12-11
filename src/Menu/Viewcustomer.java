package Menu;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

public class Viewcustomer extends JMenuItem {
	public Viewcustomer() {
		ImageIcon icon = new ImageIcon("./imge/search-person.png");
		setBackground(Color.white);
		setPreferredSize(new Dimension(170, 50));
		setIconTextGap(12);
		setForeground(Color.DARK_GRAY);
		setIcon(icon);
	}
}
