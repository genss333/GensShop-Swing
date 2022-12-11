package CustomerComponent;

import java.awt.CardLayout;
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
import java.util.List;
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

import CustomerComponent.Customer_Edit.BtnSubmit;
import CustomerComponent.Customer_Edit.TxT_Email;
import MyApp.Runnable;
import model.Customer;
import DAO.Customer_DAO;

public class Customer_Search extends JPanel implements ActionListener {

	Runnable Main;

	TxT_Email txt_email;
	JButton submit;

	int width = 200;
	int height = 60;
	int xl = 50;
	int yl = 100;
	int xt = 50;
	int yt = 150;

	class TxT_Email extends JComboBox<String> {
		public TxT_Email() {
			setBounds(xt, yt, 350, 35);
			setFont(new Font(null, Font.PLAIN, 14));
			setForeground(Color.DARK_GRAY);
			addItem("Chose Customer Email");
			setFocusable(false);
			try {
				List<Customer> customer = Customer_DAO.viewCustomer();

				for (Iterator it = customer.iterator(); it.hasNext();) {
					final Customer list = (Customer) it.next();
					String email = String.valueOf(list.getEmail());
					addItem(email);

				}
			} catch (NumberFormatException ex) {
				System.err.println("Error! Invalid data.");
			} catch (Exception ex) {
				System.err.println("Error! " + ex.getMessage());
			}
		}
	}
	class BtnSubmit extends JButton {
		public BtnSubmit() {
			setBounds(xt + 250, 200, 100, 35);
			setBackground(Color.white);
			setText("SEARCH");
			setForeground(new Color(0x02315d));
			ImageIcon icon = new ImageIcon("./imge/search.png");
			setIcon(icon);
			setBorder(null);
			setFocusable(false);
			setContentAreaFilled(false);
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

	public Customer_Search(Runnable Mainframe) {
		Main = Mainframe;
		setLayout(new FlowLayout(FlowLayout.CENTER,0,100));
		setBackground(new Color(0xeceff6));

		JPanel subpanel = new RoundedPanel(30);
		subpanel.setLayout(null);
		subpanel.setPreferredSize(new Dimension(450, 350));

		JLabel header = new JLabel("SEARCH CUSTOMER");
		header.setForeground(Color.DARK_GRAY);
		header.setBounds(100, 5, 250, 100);
		header.setHorizontalAlignment(JLabel.CENTER);
		subpanel.add(header);

		ImageIcon imgitem1 = new ImageIcon("./imge/searching.png");
		header.setIcon(imgitem1);
		header.setIconTextGap(10);

		JLabel lbphone = new JLabel("E-Mail");
		width = 100;
		height = 50;
		lbphone.setBounds(xl, yl, width, height);
		subpanel.add(lbphone);

		ImageIcon imgphone = new ImageIcon("./imge/email.png");
		lbphone.setIcon(imgphone);

		txt_email = new TxT_Email();
		subpanel.add(txt_email);
		txt_email.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Object obj = txt_email.getSelectedItem();

					if (txt_email.getSelectedItem().toString().equals("Chose Customer Email")) {

					} else {
						String item = (String) obj;

						try {

							List<Customer> customer = Customer_DAO.searchCustomer(item);

							for (Iterator it = customer.iterator(); it.hasNext();) {
								final Customer list = (Customer) it.next();
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

		JButton submit = new BtnSubmit();
		submit.addActionListener(this);
		subpanel.add(submit);

		add(subpanel);

	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		String command = evt.getActionCommand();
		System.out.println("command:" + command);
		Object source = evt.getSource();
		System.out.println("source:" + source.toString());
		if (command.equals("SEARCH")) {
			try {
				String email = String.valueOf(txt_email.getSelectedItem());

				List<Customer> customer = Customer_DAO.searchCustomer(email);

				for (Iterator it = customer.iterator(); it.hasNext();) {
					Customer list = (Customer) it.next();
					
						JOptionPane.showMessageDialog(null,
								"name: " + list.getFname()+" "+list.getLname() + " ,eamil: " + list.getEmail() + " ,password: " + list.getPassword(), "SEARCH CUTOMER",
								JOptionPane.PLAIN_MESSAGE);
				}
			} catch (NumberFormatException ex) {
				System.err.println("Error! Invalid data.");
			} catch (Exception ex) {
				System.err.println("Error! " + ex.getMessage());
			}

		}

	}

}
