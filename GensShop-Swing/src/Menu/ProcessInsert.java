package Menu;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

public
class ProcessInsert extends JMenuItem {
	public ProcessInsert() {
		ImageIcon imgitem1 = new ImageIcon("./supplier/agile.png");
		setBackground(Color.white);
		setPreferredSize(new Dimension(170, 50));
		setIconTextGap(15);
		setForeground(Color.DARK_GRAY);
		setIcon(imgitem1);
	}
}
