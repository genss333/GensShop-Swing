package Menu;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

public class Deletecustomer extends JMenuItem {
	public Deletecustomer() {
		ImageIcon imgitem1 = new ImageIcon("./imge/delete-friend.png");
		setBackground(Color.white);
		setPreferredSize(new Dimension(170, 50));
		setIconTextGap(15);
		setForeground(Color.DARK_GRAY);
		setIcon(imgitem1);
	}
}