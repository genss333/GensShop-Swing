package GoodsComponent;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

import MyApp.Admin_Menu;
import MyApp.Runnable;
import model.Good;
import model.Suplier;
import DAO.Good_DAO;
import DAO.Suplier_DAO;

public class Good_Insert extends JPanel implements ActionListener {

	Runnable Main;

	JComboBox<String> txt_sid;
	JTextArea txt_name;
	JTextField txt_price;
	JTextArea txt_image;
	JButton submit;

	int width = 200;
	int height = 60;
	int xl = 50;
	int yl = 100;
	int xt = 150;
	int yt = 100;

	class TxT_sid extends JComboBox<String> {
		public TxT_sid() {
			setBounds(xt, yt, 220, 35);
			setFont(new Font(null, Font.PLAIN, 14));
			setForeground(Color.DARK_GRAY);
			setFocusable(false);
			addItem("Chose Supplier Id");
			try {
				Vector<Suplier> suplier = Suplier_DAO.viewSuplier();
				
				for(Iterator it=suplier.iterator();it.hasNext();)
				{
					Suplier list = (Suplier)it.next();
					addItem(""+list.getId());
				}
				
			}catch (NumberFormatException ex) {
				System.err.println("Error! Invalid data.");
			} catch (Exception ex) {
				System.err.println("Error! " + ex.getMessage());
			}
			
			addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
							Object obj = getSelectedItem();
							
							if(getSelectedItem().toString().equals("Chose Supplier Id")) {
								
							}else {
								try {
									
									String item = (String) obj;
									int i = Integer.valueOf(item);
									
									Vector<Suplier> suplier = Suplier_DAO.searchSuplier(i);
									
								for (Iterator it = suplier.iterator(); it.hasNext();) {
									final Suplier list = (Suplier) it.next();
									JOptionPane.showMessageDialog(null, "id: "+getSelectedItem().toString()
											+ " name: " + list.getName()+" e-amil: "+list.getEmail()+" address: "+list.getAddress());
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
	

	class TxT_name extends JTextArea {
		public TxT_name() {
	        setLineWrap(true);
	        setWrapStyleWord(true);
			setFont(new Font(null, Font.PLAIN, 14));
			setForeground(Color.DARK_GRAY);
			setCaretColor(Color.DARK_GRAY);
		}

	}

	class TxT_price extends JTextField {
		public TxT_price() {
			setBounds(xt, 200, 220, 35);
			setFont(new Font(null, Font.PLAIN, 14));
			setForeground(Color.DARK_GRAY);
			setCaretColor(Color.DARK_GRAY);
		}

	}
	
	class TxT_image extends JTextArea {
		public TxT_image() {
			setLineWrap(true);
	        setWrapStyleWord(true);
			setFont(new Font(null, Font.PLAIN, 14));
			setForeground(Color.DARK_GRAY);
			setCaretColor(Color.DARK_GRAY);
		}

	}

	class BtnSubmit extends JButton {
		public BtnSubmit() {
			setBounds(xt, 355, 220, 35);
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
	


	public Good_Insert(Runnable Mainframe) {
		Main = Mainframe;
		setLayout(new FlowLayout(FlowLayout.CENTER,0,100));
		setBackground(new Color(0xeceff6));
		
		JPanel subpanel = new RoundedPanel(30);
		subpanel.setLayout(null);
		subpanel.setPreferredSize(new Dimension(450, 480));
		
		JPanel test = new RoundedPanel(30);
		test.setLayout(null);

		JLabel header = new JLabel("INSERT GOOD");
		header.setForeground(Color.DARK_GRAY);
		header.setBounds(100, 5, 250, 100);
		header.setHorizontalAlignment(JLabel.CENTER);
		subpanel.add(header);
		
		ImageIcon imgitem1 = new ImageIcon("./imge/open-box.png");
		header.setIcon(imgitem1);
		header.setIconTextGap(10);

		JLabel lbsid = new JLabel("Supplier ID");
		width = 100;
		height = 50;
		lbsid.setBounds(xl, yl, width, height);
		subpanel.add(lbsid);
		
		ImageIcon imgesid = new ImageIcon("./imge/inventory.png");
		lbsid.setIcon(imgesid);

		txt_sid = new TxT_sid();
		subpanel.add(txt_sid);


		yl += 50;

		JLabel lbname = new JLabel("Good Name");
		width = 100;
		height = 50;
		lbname.setBounds(xl - 10, yl, width, height);
		subpanel.add(lbname);
		
		ImageIcon imgname = new ImageIcon("./imge/package.png");
		lbname.setIcon(imgname);

		txt_name = new TxT_name();
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(xt, 150, 220, 45);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.getViewport ().setView ( txt_name );
		subpanel.add(scroll);
		
		yl += 50;
	
		JLabel lbprice = new JLabel("Good Price");
		width = 100;
		height = 50;
		lbprice.setBounds(xl, yl, width, height);
		subpanel.add(lbprice);
		
		ImageIcon imgprice = new ImageIcon("./imge/dollar.png");
		lbprice.setIcon(imgprice);
		
		txt_price = new TxT_price();
		subpanel.add(txt_price);
		
		yl += 50;
		
		JLabel lbimg = new JLabel("Good Image");
		width = 100;
		height = 50;
		lbimg.setBounds(xl, yl, width, height);
		subpanel.add(lbimg);
		
		ImageIcon img = new ImageIcon("./imge/image-files.png");
		lbimg.setIcon(img);
		
		txt_image = new TxT_image();
		JScrollPane scroll2 = new JScrollPane();
		scroll2.setBounds(xt, 250, 220, 65);
		scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll2.getViewport ().setView ( txt_image );
		subpanel.add(scroll2);
		
		submit = new BtnSubmit();
		submit.addActionListener(this);
		subpanel.add(submit);

		add(subpanel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		System.out.println("command:" + command);
		Object source = e.getSource();
		System.out.println("source:" + source.toString());
		if(command.equals("SUBMIT")) {
			
			try {
			
				int s_id = Integer.valueOf(txt_sid.getSelectedItem().toString());
				String name = txt_name.getText();
				String img = txt_image.getText();
				double price = Double.valueOf(txt_price.getText());
				Good g = new Good(s_id,0,name,price,img);
				Good_DAO  dao = new Good_DAO();
				if(dao.addGood(g)) {
					JOptionPane.showMessageDialog(null,"INSERT SUCESSFULL","INSERT GOOD",JOptionPane.PLAIN_MESSAGE);
					txt_sid.setSelectedIndex(0);
					txt_name.setText("");
					txt_sid.setSelectedItem("");
					txt_price.setText("");
					txt_image.setText("");
				}
				
			}catch (NumberFormatException ex) {
				System.err.println("Error! Invalid data.");
			}catch (Exception ex) {
				System.err.println("Error! " + ex.getMessage());
			}
		}

	}

}
