package CustomerComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import MyApp.Runnable;
import model.Customer;
import DAO.Customer_DAO;

public class Customer_Delete extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Runnable Main;

	JComboBox<String> txt_email;
	JButton submit;
	boolean set = true;

	int width = 200;
	int height = 60;
	int xl = 50;
	int yl = 100;
	int xt = 50;
	int yt = 150;
	
	class TxT_Email extends JComboBox<String> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public TxT_Email() {
			setBounds(xt, yt, 350, 35);
			setFont(new Font(null, Font.PLAIN, 14));
			setForeground(Color.DARK_GRAY);
			addItem("Chose Customer");
			setFocusable(false);
			try {
				List<Customer> customer = Customer_DAO.viewCustomer();

				for (Iterator<Customer> it = customer.iterator(); it.hasNext();) {
					final Customer list = (Customer) it.next();
					String id = String.valueOf(list.getEmail());
					addItem(id);

				}
				
				addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {

						if (e.getStateChange() == ItemEvent.SELECTED) {
							Object obj = getSelectedItem();

							if (getSelectedItem().toString().equals("Chose Customer")) {
								
							}else {
								
								String item = (String)obj;
								
								try {

									List<Customer> customer = Customer_DAO.searchCustomer(item);

									for (Iterator it = customer.iterator(); it.hasNext();) {
										final Customer list = (Customer) it.next();
										String id = String.valueOf(list.getEmail());
										if(set) {
											JOptionPane.showMessageDialog(null,
													"name: " + list.getFname()+" "+list.getLname() + " ,eamil: " + list.getEmail() + " ,password: " + list.getPassword(), "SEARCH CUTOMER",
													JOptionPane.PLAIN_MESSAGE);
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
			} catch (NumberFormatException ex) {
				System.err.println("Error! Invalid data.");
			} catch (Exception ex) {
				System.err.println("Error! " + ex.getMessage());
			}
		}
	}

	class BtnSubmit extends JButton {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

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
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
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

	public Customer_Delete(Runnable Mainframe) {
		Main = Mainframe;
		setLayout(new FlowLayout(FlowLayout.CENTER,0,100));
		setBackground(new Color(0xeceff6));

		JPanel subpanel = new RoundedPanel(30);
		subpanel.setLayout(null);
		subpanel.setPreferredSize(new Dimension(450, 350));

		JLabel header = new JLabel("DELETE CUSTOMER");
		header.setForeground(Color.DARK_GRAY);
		header.setBounds(100, 5, 250, 100);
		header.setHorizontalAlignment(JLabel.CENTER);
		subpanel.add(header);

		ImageIcon imgitem1 = new ImageIcon("./imge/delete-friend.png");
		header.setIcon(imgitem1);
		header.setIconTextGap(10);

		JLabel lbemail = new JLabel("Email");
		width = 100;
		height = 50;
		lbemail.setBounds(xl, yl, width, height);
		subpanel.add(lbemail);

		ImageIcon imgemail = new ImageIcon("./imge/email.png");
		lbemail.setIcon(imgemail);

		txt_email = new TxT_Email();
		subpanel.add(txt_email);

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

				String email = String.valueOf(txt_email.getSelectedItem());
				Customer_DAO dao = new Customer_DAO();
				if(dao.deleteCustomer(email)) {
					dao.deleteBook(email);
					JOptionPane.showMessageDialog(null, "DELETE SUCESSFULL", "DELETE CUSTOMER", JOptionPane.PLAIN_MESSAGE);
					for(int i=txt_email.getItemCount()-1;i>=0;i--){
						set = false;
						txt_email.removeItemAt(txt_email.getSelectedIndex());
						txt_email.setSelectedIndex(0);
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
