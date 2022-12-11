package Menu;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

public class Delete_Good extends JMenuItem{
	
	public Delete_Good() {
		ImageIcon imgitem1 = new ImageIcon("./imge/shopping-cart.png");
		setBackground(Color.white);
		setPreferredSize(new Dimension(170, 50));
		setIconTextGap(15);
		setForeground(Color.DARK_GRAY);
		setIcon(imgitem1);
	}

}
