package OrderComponent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import MyApp.Runnable;
import model.Order;
import model.Products;
import model.Recipt;
import DAO.ProductDAO;
import DAO.Recipt_DAO;

public class Confirm_Order extends JPanel implements ActionListener {

	Runnable Main;
	private boolean DEBUG = true;
	AbstractTableModel model; 
	JTable table;

	int width = 200;
	int height = 60; 
	int xl = 50;
	int yl = 100;
	int xt = 400;
	int yt = 20;


	public Confirm_Order(Runnable Mainframe) {

		Main = Mainframe;
		setLayout(new FlowLayout(FlowLayout.CENTER,0,100));
		setBackground(new Color(0xeceff6));
		
		try {
			
			model = new MyTableModel();
			table = new JTable(model);
			table.setPreferredScrollableViewportSize(new Dimension(900, 400));
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
						String id = String.valueOf(model.getValueAt(ev.getFirstRow(), 0));
						String status = String.valueOf(model.getValueAt(ev.getFirstRow(), 9));
						
						Recipt_DAO dao = new Recipt_DAO();
						if(dao.UpdateOrder(status, Integer.valueOf(id))) {
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
		
		private String[] columnNames = { 
				"Id", 
				"Product Id", 
				"Quantity", 
				"Total" , 
				"User" ,
				"Card Type",
				"Name Card",
				"Card Number",
				"Date",
				"Status",
				};
		private Object[][] data;
		
		
		public MyTableModel() throws ClassNotFoundException, SQLException {
			List<Order> order = Recipt_DAO.ShowOrder();
			Object[][] lsit = new Object[order.size()][order.size()];
			int row = 0;
			try {

				for (Order index : order) {
					Object obj[] = new Object[columnNames.length];
					obj[0] = index.getId();
					obj[1] = index.getPid();
					obj[2] = index.getQty();
					obj[3] = index.getTotal();
					obj[4] = index.getUser();
					obj[5] = index.getCctype();
					obj[6] = index.getCcname();
					obj[7] = index.getCcnum();
					obj[8] = index.getDate();
					obj[9] = index.getStatus();
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
				if(col == 9) {
					return true;
				}else {
					return false;
				}
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
