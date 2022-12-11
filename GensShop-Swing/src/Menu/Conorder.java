package Menu;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Conorder extends JButton {
	
	public Conorder() {
		ImageIcon imgitem1 = new ImageIcon("./imge/order.png");
		setPreferredSize(new Dimension(250, 50));
		setIconTextGap(20);
		setForeground(Color.DARK_GRAY);
		setBackground(Color.white);
		setFocusable(false);
		setBorder(null);
		setIcon(imgitem1);
	}
}
