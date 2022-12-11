package DashBoardComponent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.mindfusion.charting.swing.LineChart;

import DAO.Dashboard_DAO;
import MyApp.Runnable;
import model.DashBar;

public class DashBoard extends JPanel implements ActionListener {

	Runnable Main;

	JButton submit;
	JPanel subpanel;
	BarGraphColor bargraph; 
	LineGraph linegraph; 
	BalanceGraph balance;

	int width = 200;
	int height = 60;
	int xl = 50; 
	int yl = 100;
	int xt = 400;
	int yt = 20;


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

	class MainPanel extends JTabbedPane{
		public MainPanel () {
			setBackground(Color.white);
			setFocusable(false);
			try {
				Dashboard_DAO dao = new Dashboard_DAO();
				Vector<DashBar> barchart = dao.BarChart();
				Object[] list = new Object[barchart.size()];
				int row = 0;
				int sum = 0;
				for(DashBar index : barchart) {
					list[row] = index.getTotal();
					sum += index.getTotal();
					row++;
				}
				
				int[] data = new int[list.length];
				for (int i = 0; i < list.length; i++) {
					data[i] = (Integer)list[i];
		            bargraph = new BarGraphColor(sum, data);
		        }
				System.out.println("sum"+sum);
				System.out.println(Arrays.toString(data));
			}catch (Exception e) {
				// TODO: handle exception
			}
			addTab("Product sales", bargraph);
			setMnemonicAt(0, KeyEvent.VK_1);
			
			try {
				Dashboard_DAO dao = new Dashboard_DAO();
				Vector<DashBar> linechart = dao.LineChart();
				Object[] datalist = new Object[linechart.size()];
				int row = 0;
				int total = 0;
				for(DashBar index : linechart) {
					datalist[row] = index.getQty();
					total += index.getQty();
					row++;
				}
				
				int[]data = new int[datalist.length];
				for (int i = 0; i < datalist.length; i++) {
					data[i] = (Integer)datalist[i];
					linegraph = new LineGraph(total, data);
		        }
				System.out.println("total"+total);
				System.out.println(Arrays.toString(data));
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			addTab("Product sales Quantity", linegraph);
			setMnemonicAt(1, KeyEvent.VK_2);
			
			try {
				Dashboard_DAO dao = new Dashboard_DAO();
				Vector<DashBar> balancegp = dao.ProductBalance();
				Object[] datalist = new Object[balancegp.size()];
				int row = 0;
				int total = 0;
				for(DashBar index : balancegp) {
					datalist[row] = index.getQty();
					total += index.getQty();
					row++;
				}
				
				int[]data = new int[datalist.length];
				for (int i = 0; i < datalist.length; i++) {
					data[i] = (Integer)datalist[i];
					balance = new BalanceGraph(total, data);
		        }
				System.out.println("total"+total);
				System.out.println(Arrays.toString(data));
			}catch (Exception e) {
				// TODO: handle exception
			}
			
			addTab("Product Balance", balance);
			setMnemonicAt(2, KeyEvent.VK_3);
		}
	}

	public DashBoard(Runnable Mainframe) {

		Main = Mainframe;
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 100));
		setBackground(new Color(0xeceff6));

		subpanel = new RoundedPanel(30);
		subpanel.setPreferredSize(new Dimension(850, 650));
		subpanel.setLayout(new BorderLayout());
		
		
		MainPanel pane = new MainPanel();
		subpanel.add(pane,BorderLayout.CENTER);
		add(subpanel);
		
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
