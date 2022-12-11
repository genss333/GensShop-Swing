package Components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Logo_panel extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public Logo_panel(){
		setPreferredSize(new Dimension(250,250));
		setLayout(new FlowLayout(FlowLayout.CENTER,30,105));
		setBackground(Color.white);
		ImageIcon img = new ImageIcon("./imge/shop.png");
		JLabel icon = new JLabel();
		icon.setText("GensShop");
		icon.setIconTextGap(20);
		icon.setIcon(img);
		add(icon);
	}

}
