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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

import MyApp.Runnable;
import model.Customer;
import model.Suplier;
import DAO.Customer_DAO;
import DAO.Suplier_DAO;

public class Customer_Edit extends JPanel implements ActionListener {

	Runnable Main;

	TxT_Email txt_email;
	JTextField txt_fname;
	JTextField txt_lname;
	JPasswordField txt_pass;
	JButton submit;

	int width = 200;
	int height = 60;
	int xl = 50;
	int yl = 100;
	int xt = 130;
	int yt = 100;
	
	class TxT_Email extends JComboBox<String> {
		public TxT_Email() {
			setBounds(xt, yt, 220, 35);
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


	class txt_fname extends JTextField {
		public txt_fname() {
			setBounds(xt, 150, 220, 35);
			setFont(new Font(null, Font.PLAIN, 14));
			setForeground(Color.DARK_GRAY);
			setCaretColor(Color.DARK_GRAY);
		}

	}

	class TxT_adress extends JTextField {
		public TxT_adress() {
			setBounds(xt, 200, 220, 35);
			setFont(new Font(null, Font.PLAIN, 14));
			setForeground(Color.DARK_GRAY);
			setCaretColor(Color.DARK_GRAY);
		}

	}

	class txt_pass extends JPasswordField {
		public txt_pass() {
			setBounds(xt, 250, 220, 35);
			setFont(new Font(null, Font.PLAIN, 14));
			setForeground(Color.DARK_GRAY);
			setCaretColor(Color.DARK_GRAY);
		}

	}

	class BtnSubmit extends JButton {
		public BtnSubmit() {
			setBounds(xt, 300, 220, 35);
			setBackground(new Color(0x02315d));
			setText("SUBMIT");
			setForeground(Color.white);
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

	public Customer_Edit(Runnable Mainframe) {
		Main = Mainframe;
		setLayout(new FlowLayout(FlowLayout.CENTER,0,100));
		setBackground(new Color(0xeceff6));

		JPanel subpanel = new RoundedPanel(30);
		subpanel.setLayout(null);
		subpanel.setPreferredSize(new Dimension(450, 400));

		JLabel header = new JLabel("EDIT CUSTOMER");
		header.setForeground(Color.DARK_GRAY);
		header.setBounds(100, 5, 250, 100);
		header.setHorizontalAlignment(JLabel.CENTER);
		subpanel.add(header);

		ImageIcon imgitem1 = new ImageIcon("./imge/woman.png");
		header.setIcon(imgitem1);
		header.setIconTextGap(10);

		JLabel lbemail = new JLabel("E-Mail");
		width = 100;
		height = 50;
		lbemail.setBounds(xl, yl, width, height);
		subpanel.add(lbemail);

		ImageIcon imgemail = new ImageIcon("./imge/email.png");
		lbemail.setIcon(imgemail);

		txt_email = new TxT_Email();
		subpanel.add(txt_email);
		txt_email.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Object obj = txt_email.getSelectedItem();

					if (txt_email.getSelectedItem().toString().equals("Chose Customer Email")) {
						
						txt_lname.setText("");
						txt_fname.setText("");
						txt_pass.setText("");

					} else {
						String item = (String) obj;

						try {

							List<Customer> customer = Customer_DAO.searchCustomer(item);

							for (Iterator it = customer.iterator(); it.hasNext();) {
								final Customer list = (Customer) it.next();
								txt_fname.setText(list.fname);
								txt_lname.setText(list.lname);
								txt_pass.setText(list.password);
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

		yl += 45;

		JLabel lbfname = new JLabel("Firstname");
		width = 100;
		height = 50;
		lbfname.setBounds(xl, yl, width, height);
		subpanel.add(lbfname);

		ImageIcon imgname = new ImageIcon("./imge/id.png");
		lbfname.setIcon(imgname);

		txt_fname = new txt_fname();
		subpanel.add(txt_fname);

		yl += 50;

		JLabel lbadd = new JLabel("Lastname");
		width = 100;
		height = 50;
		lbadd.setBounds(xl - 10, yl, width, height);
		subpanel.add(lbadd);

		ImageIcon imghome = new ImageIcon("./imge/home-address.png");
		lbadd.setIcon(imghome);

		txt_lname = new TxT_adress();
		subpanel.add(txt_lname);

		yl += 50;

		JLabel lbetel = new JLabel("Password");
		width = 100;
		height = 50;
		lbetel.setBounds(xl, yl, width, height);
		subpanel.add(lbetel);

		ImageIcon imgtel = new ImageIcon("./imge/password.png");
		lbetel.setIcon(imgtel);

		txt_pass = new txt_pass();
		subpanel.add(txt_pass);

		submit = new BtnSubmit();
		submit.addActionListener(this);
		subpanel.add(submit);

		add(subpanel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if (command.equals("SUBMIT")) {
			String name = txt_fname.getText();
			String address = txt_lname.getText();
			String pass = txt_pass.getText();
			String email = String.valueOf(txt_email.getSelectedItem());
			Customer cus = new Customer(name, address, email, pass);
			Customer_DAO dao = new Customer_DAO();
			dao.editCustomer(cus);
			JOptionPane.showMessageDialog(null, "UPDATE SUCESSFULL", "UPDATE CUSTOMER", JOptionPane.PLAIN_MESSAGE);
			txt_fname.setText("");
			txt_lname.setText("");
			txt_pass.setText("");
			txt_email.setSelectedIndex(0);
		}

	}

}
