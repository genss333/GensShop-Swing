package Menu;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class Menucustomer extends JMenu {
	public Menucustomer() {
		ImageIcon imgitem1 = new ImageIcon("./imge/people.png");
		setPreferredSize(new Dimension(250, 50));
		setIconTextGap(20);
		UIManager.put("PopupMenu.border", new LineBorder(Color.white));
		setOpaque(true);
		setForeground(Color.DARK_GRAY);
		setBackground(Color.white);
		setText("ADMIN MENU CUSTOMER");
		setFocusable(true);
		setIcon(imgitem1);
	}
}
