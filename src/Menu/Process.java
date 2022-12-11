package Menu;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class Process extends JMenu {
	
	public Process() {
		ImageIcon imgitem1 = new ImageIcon("./imge/process.png");
		setPreferredSize(new Dimension(250, 50));
		setIconTextGap(20);
		UIManager.put("PopupMenu.border", new LineBorder(Color.white));
		setOpaque(true);
		setForeground(Color.DARK_GRAY);
		setBackground(Color.white);
		setText("ADMIN MENU PROCESS");
		setFocusable(true);
		setIcon(imgitem1);
	}
}
