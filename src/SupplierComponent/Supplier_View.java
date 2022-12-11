package SupplierComponent;

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
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.mysql.cj.protocol.a.result.ResultsetRowsCursor;

import MyApp.Runnable;
import model.Customer;
import model.Suplier;
import DAO.Customer_DAO;
import DAO.Suplier_DAO;

public class Supplier_View extends JPanel {

	Runnable Main;
	private boolean DEBUG = true;
	AbstractTableModel model; 
	JTable table;
	JScrollPane scrollPane;
	JButton refesh;

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
			setText("SUPPLIER VIEW");
			setForeground(new Color(0x02315d));
			ImageIcon icon = new ImageIcon("./supplier/power-plant.png");
			setIcon(icon);
			setBorder(null);
			setFocusable(false);
		}
	}

	public Supplier_View(Runnable Mainframe) {
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
						
						Suplier_DAO dao = new Suplier_DAO();
						
						String id = String.valueOf(model.getValueAt(ev.getFirstRow(), 0));
						String name = String.valueOf(model.getValueAt(ev.getFirstRow(), 1));
						String address = String.valueOf(model.getValueAt(ev.getFirstRow(), 2));
						String phone = String.valueOf(model.getValueAt(ev.getFirstRow(), 3));
						String email = String.valueOf(model.getValueAt(ev.getFirstRow(), 4));
						
						Suplier sup = new Suplier(Integer.valueOf(id), name, address, phone, email);
						if(dao.editSuplier(sup)) {
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
		scrollPane = new JScrollPane(table);
		
		add(scrollPane);
	}
	
	class MyTableModel extends AbstractTableModel{
		
		private String[] columnNames = { "Id", "Name", "Adress", "Phone" , "E-Mail" };
		private Object[][] data;
		
		
		public MyTableModel() throws ClassNotFoundException, SQLException {
			Vector<Suplier> supplier = Suplier_DAO.viewSuplier();
			Object[][] lsit = new Object[supplier.size()][supplier.size()];
			int row = 0;
			try {

				for (Suplier index : supplier) {
					Object obj[] = new Object[columnNames.length];
					obj[0] = index.id;
					obj[1] = index.name;
					obj[2] = index.address;
					obj[3] = index.tel;
					obj[4] = index.email;
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
			if(col>0) {
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


}
