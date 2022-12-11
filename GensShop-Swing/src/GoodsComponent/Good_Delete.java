package GoodsComponent;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

import MyApp.Runnable;
import model.Good;
import DAO.Good_DAO;

public class Good_Delete extends JPanel implements ActionListener {

	Runnable Main;

	JComboBox<String> txt_id;
	JButton submit;
	boolean set = true;

	int width = 200;
	int height = 60;
	int xl = 50;
	int yl = 100;
	int xt = 50;
	int yt = 150;

	class TxT_id extends JComboBox<String> {
		public TxT_id() {
			setBounds(xt, yt, 350, 35);
			setFont(new Font(null, Font.PLAIN, 14));
			setForeground(Color.DARK_GRAY);
			setFocusable(false);
			addItem("Chose Goods Id");
			try {
				Vector<Good> good = Good_DAO.viewGood();

				for (Iterator it = good.iterator(); it.hasNext();) {
					Good list = (Good) it.next();
					addItem("" + list.getG_id());
				}

			} catch (NumberFormatException ex) {
				System.err.println("Error! Invalid data.");
			} catch (Exception ex) {
				System.err.println("Error! " + ex.getMessage());
			}

			addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {

					if (e.getStateChange() == ItemEvent.SELECTED) {
						Object obj = getSelectedItem();

						if (getSelectedItem().toString().equals("Chose Goods Id")) {
							
						}else {
							
							String item = (String)obj;
							int i = Integer.valueOf(item);
							
							try {

								Vector<Good> good = Good_DAO.searchGood(i);

								for (Iterator it = good.iterator(); it.hasNext();) {
									Good list = (Good) it.next();
									if(set) {
										JOptionPane.showMessageDialog(null,
											"supplier id: " + list.getS_id()
													+ "good id: " + list.getG_id() + " ,good name: " + list.getName()
													+ " ,price: " + list.getPrice(),
											"SEARCH CUTOMER", JOptionPane.PLAIN_MESSAGE);
									}
									
								}
							} catch (NumberFormatException ex) {
								System.err.println("Error! Invalid data.");
							} catch (Exception ex) {
								System.err.println("Error! " + ex.getMessage());
							}
						}
					}
				}
			});
		}
	}

	class BtnSubmit extends JButton {
		public BtnSubmit() {
			setBounds(xt + 250, 200, 100, 35);
			setBackground(Color.white);
			setText("DELETE");
			setHideActionText(true);
			ImageIcon icon = new ImageIcon("./imge/delete.png");
			setIcon(icon);
			setForeground(Color.red);
			setContentAreaFilled(false);
			setBorder(null);
			setFocusable(false);
		}
	}

	class RoundedPanel extends JPanel {
		private Color backgroundColor;
		private int cornerRadius = 15;

		public RoundedPanel(LayoutManager layout, int radius) {
			super(layout);
			cornerRadius = radius;
		}

		public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
			super(layout);
			cornerRadius = radius;
			backgroundColor = bgColor;
		}

		public RoundedPanel(int radius) {
			super();
			cornerRadius = radius;
		}

		public RoundedPanel(int radius, Color bgColor) {
			super();
			cornerRadius = radius;
			backgroundColor = bgColor;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Dimension arcs = new Dimension(cornerRadius, cornerRadius);
			int width = getWidth();
			int height = getHeight();
			Graphics2D graphics = (Graphics2D) g;
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			graphics.setPaint(Color.white);
			graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint background
			graphics.setColor(getForeground());
		}
	}

	public Good_Delete(Runnable Mainframe) {
		Main = Mainframe;
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 100));
		setBackground(new Color(0xeceff6));

		JPanel subpanel = new RoundedPanel(30);
		subpanel.setLayout(null);
		subpanel.setPreferredSize(new Dimension(450, 350));

		JLabel header = new JLabel("DELETE GOOD");
		header.setForeground(Color.DARK_GRAY);
		header.setBounds(100, 5, 250, 100);
		header.setHorizontalAlignment(JLabel.CENTER);
		subpanel.add(header);

		ImageIcon imgitem1 = new ImageIcon("./imge/shopping-cart.png");
		header.setIcon(imgitem1);
		header.setIconTextGap(10);

		JLabel lbid = new JLabel("Good ID");
		width = 100;
		height = 50;
		lbid.setBounds(xl, yl, width, height);
		subpanel.add(lbid);

		ImageIcon imgphone = new ImageIcon("./imge/products mini.png");
		lbid.setIcon(imgphone);

		txt_id = new TxT_id();
		subpanel.add(txt_id);

		JButton submit = new BtnSubmit();
		submit.addActionListener(this);
		subpanel.add(submit);

		add(subpanel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String command = e.getActionCommand();
		if (command.equals("DELETE")) {

			try {
				int id = Integer.valueOf(txt_id.getSelectedItem().toString());
				Good_DAO dao = new Good_DAO();
				if(dao.deleteGood(id)) {
					JOptionPane.showMessageDialog(null, "DELETE SUCESSFULL", "DELETE GOOD", JOptionPane.PLAIN_MESSAGE);
					for(int i=txt_id.getItemCount()-1;i>=0;i--){
						set = false;
						txt_id.removeItemAt(txt_id.getSelectedIndex());
						txt_id.setSelectedIndex(0);
						break;
					}
				}
				
				set= true;
				
			} catch (NumberFormatException ex) {
				System.err.println("Error! Invalid data.");
			} catch (Exception ex) {
				System.err.println("Error! " + ex.getMessage());
			}
		}

	}

}
