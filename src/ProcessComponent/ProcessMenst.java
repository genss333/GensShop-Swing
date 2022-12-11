package ProcessComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import DAO.ProductDAO;
import MyApp.Runnable;
import model.Products;
public class ProcessMenst extends JPanel implements ActionListener {

	Runnable Main;
	private boolean DEBUG = true;
	AbstractTableModel model; 
	JButton delete;
	JTable table;

	int width = 200;
	int height = 60;
	int xl = 50;
	int yl = 100;
	int xt = 400;
	int yt = 20;

	class BtnSubmit extends JButton {
		public BtnSubmit() {
			setBounds(xt + 250, 200, 100, 35);
			setBackground(Color.white);
			setText("DELETE PRODUCT");
			setHideActionText(true);
			ImageIcon icon = new ImageIcon("./imge/delete.png");
			setIcon(icon);
			setForeground(Color.red);
			setContentAreaFilled(false);
			setBorder(null);
			setFocusable(false);
		}
	}
	public ProcessMenst(Runnable Mainframe) {

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
						String des = String.valueOf(model.getValueAt(ev.getFirstRow(), 2));
						String sale = String.valueOf(model.getValueAt(ev.getFirstRow(), 5));
						String collection = String.valueOf(model.getValueAt(ev.getFirstRow(), 6));
						String brand = String.valueOf(model.getValueAt(ev.getFirstRow(), 7));
						String stk = String.valueOf(model.getValueAt(ev.getFirstRow(), 8));
						String status = String.valueOf(model.getValueAt(ev.getFirstRow(), 9));
						String inshop = String.valueOf(model.getValueAt(ev.getFirstRow(), 11));
						String date = String.valueOf(model.getValueAt(ev.getFirstRow(), 12));
						boolean dao = new ProductDAO().UpdateProducts(des,Double.valueOf(sale),collection,brand,Integer.valueOf(stk),status,inshop,date,Integer.valueOf(id));
						if(dao) {
							System.out.println("succex full");
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
				"Title", 
				"Desc", 
				"Image" , 
				"Price" ,
				"Sale",
				"Collection",
				"Brand",
				"In Stock",
				"Status",
				"Supplier",
				"In Shop",
				"Date"
				};
		private Object[][] data;
		
		
		public MyTableModel() throws ClassNotFoundException, SQLException {
			List<Products> product = ProductDAO.ProductsAll();
			Object[][] lsit = new Object[product.size()][product.size()];
			int row = 0;
			try {

				for (Products index : product) {
					Object obj[] = new Object[columnNames.length];
					obj[0] = index.id;
					obj[1] = index.title;
					obj[2] = index.des;
					obj[3] = index.image;
					obj[4] = index.price;
					obj[5] = index.sale;
					obj[6] = index.collection;
					obj[7] = index.brand;
					obj[8] = index.stk;
					obj[9] = index.status;
					obj[10] = index.supplier;
					obj[11] = index.inshop;
					obj[12] = index.date;
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


		public boolean isCellEditable(int row, int col) {
				if(col == 2) {
					return true;
				}else if(col >4 && col < 10 ) {
					return true;
				}else if(col > 10) {
					return true;
				}
				else {
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
