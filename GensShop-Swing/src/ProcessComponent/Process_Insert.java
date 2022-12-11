package ProcessComponent;

import java.awt.BorderLayout;
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
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
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
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;

import MyApp.Runnable;
import model.Good;
import model.Products;
import DAO.Good_DAO;
import DAO.ProductDAO;

public class Process_Insert extends JPanel implements ActionListener {

	Runnable Main;

	JComboBox<String> txt_gid;
	JTextField txt_qty;
	JTextField txt_col;
	JTextField txt_brand;
	JTextArea txt_des;
	JDateChooser dateChooser;
	boolean set = true;
	JButton submit;
	JLabel datasid;
	int width = 200;
	int height = 60;
	int xl = 50;
	int yl = 97;
	int xt = 130;
	int yt = 100;


	class TxT_gid extends JComboBox<String> {
		public TxT_gid() {
			setBounds(xt, 100, 220, 35);
			setFont(new Font(null, Font.PLAIN, 14));
			setForeground(Color.DARK_GRAY);
			setFocusable(false);
			addItem("Chose Goods Id");
			try {
				Vector<Good> good = Good_DAO.ChoiseGood();
				for (Iterator it = good.iterator(); it.hasNext();) {
					Good list = (Good) it.next();
					String id = String.valueOf(list.g_id);
					addItem(id);
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
							datasid.setText("");
						} else {
							String item = (String) obj;
							int i = Integer.valueOf(item);

							try {

								Vector<Good> good = Good_DAO.searchGood(i);

								for (Iterator it = good.iterator(); it.hasNext();) {
									Good list = (Good) it.next();
									datasid.setText(" "+String.valueOf(list.s_id));
									if(set) {
									JOptionPane.showMessageDialog(null, "id: " + getSelectedItem().toString()
											+ " name: " + list.getName() + " price: " + list.getPrice());
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
	
	class TxT_sid extends JLabel{
		public TxT_sid() {
			Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
			setBounds(xt, 150, 220, 35);
			setFont(new Font(null, Font.PLAIN, 14));
			setForeground(Color.DARK_GRAY);
			setBorder(border);
		}

	}

	class TxT_qty extends JTextField {
		public TxT_qty() {
			setBounds(xt, 200, 220, 35);
			setFont(new Font(null, Font.PLAIN, 14));
			setForeground(Color.DARK_GRAY);
			setCaretColor(Color.DARK_GRAY);
		}

	}

	class Calendar extends JDateChooser {
		public Calendar() {
			setBounds(xt, 250, 220, 35);
			setFont(new Font(null, Font.PLAIN, 14));
		}

	}

	class TxT_col extends JTextField {
		public TxT_col() {
			setBounds(xt, 300, 100, 35);
			setFont(new Font(null, Font.PLAIN, 14));
			setForeground(Color.DARK_GRAY);
			setCaretColor(Color.DARK_GRAY);
		}

	}

	class TxT_brnad extends JTextField {
		public TxT_brnad() {
			setBounds(xt + 120, 300, 100, 35);
			setFont(new Font(null, Font.PLAIN, 14));
			setForeground(Color.DARK_GRAY);
			setCaretColor(Color.DARK_GRAY);
		}

	}
	
	class TxT_des extends JTextArea {
		public TxT_des() {
			Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
	        setLineWrap(true);
	        setWrapStyleWord(true);
			setFont(new Font(null, Font.PLAIN, 14));
			setForeground(Color.DARK_GRAY);
			setCaretColor(Color.DARK_GRAY);
			setBorder(border);
		}

	}

	class BtnSubmit extends JButton {
		public BtnSubmit() {
			setBounds(xt, 450, 220, 35);
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

	public Process_Insert(Runnable Mainframe) {
		Main = Mainframe;
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 100));
		setBackground(new Color(0xeceff6));

		JPanel subpanel = new RoundedPanel(30);
		subpanel.setLayout(null);
		subpanel.setPreferredSize(new Dimension(450, 540));

		JPanel test = new RoundedPanel(30);
		test.setLayout(null);

		JLabel header = new JLabel("PROCESS INSERT");
		header.setForeground(Color.DARK_GRAY);
		header.setBounds(100, 5, 250, 100);
		header.setHorizontalAlignment(JLabel.CENTER);
		subpanel.add(header);
		
		JLabel lbgid = new JLabel("Good ID");
		width = 100;
		height = 50;
		lbgid.setBounds(xl, yl, width, height);
		subpanel.add(lbgid);

		txt_gid = new TxT_gid();
		subpanel.add(txt_gid);

		ImageIcon imggid = new ImageIcon("./imge/products mini.png");
		lbgid.setIcon(imggid);
		
		yl += 45;

		ImageIcon imgitem1 = new ImageIcon("./supplier/agile.png");
		header.setIcon(imgitem1);
		header.setIconTextGap(10);

		JLabel lbsid = new JLabel("Supplier ID");
		width = 100;
		height = 50;
		lbsid.setBounds(xl-10, yl, width, height);
		subpanel.add(lbsid);

		ImageIcon imgesid = new ImageIcon("./imge/inventory.png");
		lbsid.setIcon(imgesid);

		datasid = new TxT_sid();
		subpanel.add(datasid);

		yl += 50;

		JLabel lbqty = new JLabel("Good Quantity");
		width = 100;
		height = 50;
		lbqty.setBounds(xl - 30, yl, width, height);
		subpanel.add(lbqty);

		ImageIcon imgqty = new ImageIcon("./imge/package.png");
		lbqty.setIcon(imgqty);

		txt_qty = new TxT_qty();
		subpanel.add(txt_qty);

		yl += 50;

		JLabel lbdate = new JLabel("Date");
		width = 100;
		height = 50;
		lbdate.setBounds(xl + 10, yl, width, height);
		subpanel.add(lbdate);

		ImageIcon imgdate = new ImageIcon("./supplier/schedule.png");
		lbdate.setIcon(imgdate);

		dateChooser = new Calendar();
		subpanel.add(dateChooser);

		yl += 50;

		JLabel lb = new JLabel("Collection/Brand");
		width = 100;
		height = 50;
		lb.setBounds(xl - 20, yl, width, height);
		subpanel.add(lb);
		
		txt_col = new TxT_col();
		subpanel.add(txt_col);
		
		txt_brand = new TxT_brnad();
		subpanel.add(txt_brand);
		
		yl += 50;
		
		JLabel lbdes = new JLabel("Description");
		width = 100;
		height = 50;
		lbdes.setBounds(xl - 20, yl, width, height);
		subpanel.add(lbdes);
		
		txt_des = new TxT_des();
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(xt, 350, 220, 80);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.getViewport ().setView ( txt_des );
		subpanel.add(scroll);

		submit = new BtnSubmit();
		submit.addActionListener(this);
		subpanel.add(submit);

		add(subpanel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("SUBMIT")) {

			try {

				int g_id = Integer.valueOf(txt_gid.getSelectedItem().toString());
				int qty = Integer.valueOf(txt_qty.getText());
				Date Date = dateChooser.getDate();
				String date = DateFormat.getDateInstance().format(Date);
				String des = txt_des.getText();
				String col = txt_col.getText();
				String brand = txt_brand.getText();
				String title = "";
				int sid = 0;
				double price = 0;
				String image = "";
				
				try {

					Vector<Good> good = Good_DAO.searchGood(g_id);

					for (Iterator it = good.iterator(); it.hasNext();) {
						Good list = (Good) it.next();
						title = list.name;
						sid = list.s_id;
						price = list.price;
						image = list.image;
					}

				} catch (NumberFormatException ex) {
					System.err.println("Error! Invalid data.");
				} catch (Exception ex) {
					System.err.println("Error! " + ex.getMessage());
				}
				
				ProductDAO dao = new ProductDAO();
				Good_DAO gdao = new Good_DAO();
				Products product = new Products(g_id, title, des, image, price, 0, col, brand, qty, "", sid, "", date);
				if(dao.InsertProducts(product)) {
					JOptionPane.showMessageDialog(null, "INSERT SUCESSFULL", "INSERT PROCESS", JOptionPane.PLAIN_MESSAGE);
					txt_qty.setText("");
					dateChooser.setDateFormatString("");
					txt_des.setText("");
					txt_col.setText("");
					txt_brand.setText("");
					txt_gid.getSelectedIndex();
					if(gdao.UpdateChoise(g_id)) {
						for(int i=txt_gid.getItemCount()-1;i>=0;i--){
							set = false;
							txt_gid.removeItemAt(txt_gid.getSelectedIndex());
							txt_gid.setSelectedIndex(0);
							break;
						}
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
