package SupplierComponent;

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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

import MyApp.Runnable;
import SupplierComponent.Supplier_Edit.TxT_adress;
import model.Suplier;
import DAO.Suplier_DAO;

public class Supplier_Insert extends JPanel implements ActionListener {

	Runnable Main;

	JTextField txt_id;
	JTextField txt_email;
	JTextField txt_name;
	JTextArea txt_address;
	JTextField txt_phone;
	JButton submit;

	int width = 200;
	int height = 60;
	int xl = 50;
	int yl = 100;
	int xt = 130;
	int yt = 100;
	
	class TxT_id extends JTextField {
		public TxT_id() {
			setBounds(xt, yt, 220, 35);
			setFont(new Font(null, Font.PLAIN, 14));
			setForeground(Color.DARK_GRAY);
			setCaretColor(Color.DARK_GRAY);
		}
	}

	class TxT_email extends JTextField {
		public TxT_email() {
			setBounds(xt, yt, 220, 35);
			setFont(new Font(null, Font.PLAIN, 14));
			setForeground(Color.DARK_GRAY);
			setCaretColor(Color.DARK_GRAY);
		}
	}

	class TxT_name extends JTextField {
		public TxT_name() {
			setBounds(xt, 150, 220, 35);
			setFont(new Font(null, Font.PLAIN, 14));
			setForeground(Color.DARK_GRAY);
			setCaretColor(Color.DARK_GRAY);
		}

	}

	class TxT_adress extends JTextArea {
		public TxT_adress() {
			setLineWrap(true);
	        setWrapStyleWord(true);
			setFont(new Font(null, Font.PLAIN, 14));
			setForeground(Color.DARK_GRAY);
			setCaretColor(Color.DARK_GRAY);
		}

	}

	class TxT_phone extends JTextField {
		public TxT_phone() {
			setBounds(xt, 285, 220, 35);
			setFont(new Font(null, Font.PLAIN, 14));
			setForeground(Color.DARK_GRAY);
			setCaretColor(Color.DARK_GRAY);
		}

	}

	class BtnSubmit extends JButton {
		public BtnSubmit() {
			setBounds(xt, 350, 220, 35);
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
	


	public Supplier_Insert(Runnable Mainframe) {
		Main = Mainframe;
		setLayout(new FlowLayout(FlowLayout.CENTER,0,100));
		setBackground(new Color(0xeceff6));
		
		JPanel subpanel = new RoundedPanel(30);
		subpanel.setLayout(null);
		subpanel.setPreferredSize(new Dimension(450, 480));
		
		JPanel test = new RoundedPanel(30);
		test.setLayout(null);

		JLabel header = new JLabel("INSERT SUPPLIER");
		header.setForeground(Color.DARK_GRAY);
		header.setBounds(100, 5, 250, 100);
		header.setHorizontalAlignment(JLabel.CENTER);
		subpanel.add(header);
		
		ImageIcon imgitem1 = new ImageIcon("./supplier/packages.png");
		header.setIcon(imgitem1);
		header.setIconTextGap(10);

		JLabel lbemail = new JLabel("E-Mail");
		width = 100;
		height = 50;
		lbemail.setBounds(xl, yl, width, height);
		subpanel.add(lbemail);
		
		ImageIcon imgemail = new ImageIcon("./imge/email.png");
		lbemail.setIcon(imgemail);

		txt_email = new TxT_email();
		subpanel.add(txt_email);

		yl += 45;

		JLabel lbgname = new JLabel("Name");
		width = 100;
		height = 50;
		lbgname.setBounds(xl, yl, width, height);
		subpanel.add(lbgname);
		
		ImageIcon imgname = new ImageIcon("./supplier/company.png");
		lbgname.setIcon(imgname);

		txt_name = new TxT_name();
		subpanel.add(txt_name);

		yl += 50;

		JLabel lbadd = new JLabel("Address");
		width = 100;
		height = 50;
		lbadd.setBounds(xl - 10, yl, width, height);
		subpanel.add(lbadd);
		
		ImageIcon imghome = new ImageIcon("./imge/home-address.png");
		lbadd.setIcon(imghome);

		txt_address = new TxT_adress();
		txt_address = new TxT_adress();
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(xt, 200, 220, 65);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.getViewport ().setView ( txt_address );
		subpanel.add(scroll);
		
		yl += 80;
		
		JLabel lbetel = new JLabel("Phone");
		width = 100;
		height = 50;
		lbetel.setBounds(xl, yl, width, height);
		subpanel.add(lbetel);
		
		ImageIcon imgtel = new ImageIcon("./imge/smartphone.png");
		lbetel.setIcon(imgtel);
		
		txt_phone = new TxT_phone();
		subpanel.add(txt_phone);

		submit = new BtnSubmit();
		submit.addActionListener(this);
		subpanel.add(submit);

		add(subpanel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("SUBMIT")) {
			
			try {
				
				int id = 0;
				String name = txt_name.getText();
				String address = txt_address.getText();
				String tel = txt_phone.getText();
				String email = txt_email.getText();
				Suplier sup = new Suplier(id,name,address,tel,email);
				Suplier_DAO  dao = new Suplier_DAO();
				dao.addSuplier(sup);
				txt_name.setText("");
				txt_address.setText("");
				txt_email.setText("");
				txt_phone.setText("");
				JOptionPane.showMessageDialog(null,"INSERT SUCESSFULL","INSERT SUPPLIER",JOptionPane.PLAIN_MESSAGE);
				
			}catch (NumberFormatException ex) {
				System.err.println("Error! Invalid data.");
			}catch (Exception ex) {
				System.err.println("Error! " + ex.getMessage());
			}
		}

	}

}
