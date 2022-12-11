package CustomerComponent;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.util.List;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;


import MyApp.Runnable;
import model.Customer;
import DAO.Customer_DAO;

public class Customer_View extends JPanel implements ActionListener{

	Runnable Main;
	private boolean DEBUG = true;
	AbstractTableModel model; 
	JTable table;

	int width = 200;
	int height = 60;
	int xl = 50;
	int yl = 100;
	int xt = 350;
	int yt = 20;

	class header extends JLabel {
		public header() {
			setBounds(xt, yt, 200, 35);
			setBackground(Color.white);
			setText("CUSTOMER VIEW");
			setForeground(new Color(0x02315d));
			ImageIcon icon = new ImageIcon("./imge/search-person.png");
			setIcon(icon);
			setBorder(null);
			setFocusable(false);
		}
	}

	public Customer_View(Runnable Mainframe) {
		Main = Mainframe;
		setLayout(new FlowLayout(FlowLayout.CENTER,0,100));
		setBackground(new Color(0xeceff6));

		
		try {
			
			model = new MyTableModel();
			table = new JTable(model);
			table.setPreferredScrollableViewportSize(new Dimension(800, 400));
			table.setFillsViewportHeight(true);
			table.setBackground(Color.white);
			table.getColumnModel().getColumn(0).setPreferredWidth(60);
			table.getColumnModel().getColumn(1).setPreferredWidth(60);
			table.getColumnModel().getColumn(2).setPreferredWidth(60);
			table.getColumnModel().getColumn(3).setPreferredWidth(60);
			table.setRowHeight(30);
			table.setFont(new Font(null, Font.PLAIN, 16));
			

			table.getModel().addTableModelListener(new TableModelListener() {
				@Override
				public void tableChanged(final TableModelEvent ev) {
					if (ev.getType() == TableModelEvent.UPDATE) {
						
						Customer_DAO dao = new Customer_DAO();
						
						String fname = String.valueOf(model.getValueAt(ev.getFirstRow(), 0));
						String lname = String.valueOf(model.getValueAt(ev.getFirstRow(), 1));
						String email = String.valueOf(model.getValueAt(ev.getFirstRow(), 2));
						String pass = String.valueOf(model.getValueAt(ev.getFirstRow(), 3));
						
						Customer customer = new Customer(fname, lname, email, pass);
						if(dao.editCustomer(customer)) {
							System.out.println("Success Fully");
						}
				
					}
				}
			});
			
		} catch (NumberFormatException ex) {
			System.err.println("Error! Invalid data.");
		} catch (Exception ex) {
			System.err.println("Error! " + ex.getMessage());
		}
		JScrollPane scrollPane = new JScrollPane(table);
		
		add(scrollPane);
		
	}
	
	class MyTableModel extends AbstractTableModel{
		
		private String[] columnNames = { "Firstname", "Lastname", "E-mail", "Password" };
		private Object[][] data;
		
		
		public MyTableModel() throws ClassNotFoundException, SQLException {
			List<Customer> customer = Customer_DAO.viewCustomer();
			Object[][] lsit = new Object[customer.size()][customer.size()];
			int row = 0;
			try {

				for (Customer index : customer) {
					Object obj[] = new Object[columnNames.length];
					obj[0] = index.fname;
					obj[1] = index.lname;
					obj[2] = index.email;
					obj[3] = index.password;
					lsit[row] = obj;
					row++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			this.columnNames = columnNames;
			this.data = lsit;
			
			
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.length;
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		public boolean isCellEditable(int row, int col) {
				return true;
		}
		
		
		public void setValueAt(Object value, int row, int col) {
			System.out.println("setValueAt()... row:" + row + "x" + "col:" + col);
			if (DEBUG) {
				System.out.println("Setting value at " + row + "," + col + " to " + value + " (an instance of "
						+ value.getClass() + ")");
			}

			data[row][col] = value;
			fireTableCellUpdated(row, col);

			if (DEBUG) {
				System.out.println("New value of data:");
				printDebugData();
			}
		}

		private void printDebugData() {
			System.out.println("printDebugData()...");
			int numRows = getRowCount();
			int numCols = getColumnCount();

			for (int i = 0; i < numRows; i++) {
				System.out.print("    row " + i + ":");
				for (int j = 0; j < numCols; j++) {
					System.out.print("  " + data[i][j]);
				}
				System.out.println();
			}
			System.out.println("--------------------------");
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent evt) {

	}


}
