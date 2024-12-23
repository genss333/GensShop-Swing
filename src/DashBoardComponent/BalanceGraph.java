package DashBoardComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JPanel;

import DAO.Dashboard_DAO;
import model.DashBar;

public class BalanceGraph extends JPanel{

		final static int PAD_RIGHT = 50;
		final static int PAD_LEFT = 100;
		final static int PAD_TOP = 50;
		final static int PAD_BOTTOM = 60;
		final static int OFFSET_TOP = 30;
		final static int OFFSET_BOTTOM = 20;
		final static int OFFSET_X_AXIS = PAD_LEFT - 40;
		final static int OFFSET_Y_AXIS = PAD_BOTTOM - 10;
		final static int SCALE_LENGTH = 8;

		String graphLabel = "Product Balance";
		String xLabel = "Products";
		String yLabel = "Quantity";

		int width;
		int height;
		int graphWidth;
		int graphHeight;

		int fontHeight = 10;

		int dataBound;
		int[] data;
		int blockNo;
		int gapWidth;
		int barWidth;
		double valueRatio;

		public BalanceGraph(int dataBound, int[] data) {

			this(800, 600, dataBound, data);

		}

		public BalanceGraph(int w, int h, int dataBound, int[] data) {

			super();

			width = w;
			height = h;
			this.dataBound = dataBound;
			this.data = data;

			setPreferredSize(new Dimension(width, height));
			graphWidth = width - PAD_LEFT - PAD_RIGHT;
			graphHeight = height - PAD_TOP - PAD_BOTTOM;
			gapWidth = graphWidth / ((this.data.length * 2 + 1));
			barWidth = gapWidth;

		}

		int[] scaleData(int[] raw) {
			int[] scaled = new int[raw.length];
			int i = 0;
			double ratio = graphHeight * 1.0 / dataBound;
			for (int value : raw) {
				int temp = (int) Math.round(value * ratio);
				scaled[i++] = temp;
			}

			return scaled;
		}

		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;

			int[] scaledData = scaleData(data);
			int yBlockSize = graphHeight / (scaledData.length + 1);
			int dataBlockSize = dataBound / (data.length + 1);
			System.out.println("yBlockSize:" + yBlockSize + ", dataBlockSize:" + dataBlockSize);

			int x1, y1, x2, y2;

			// graph label
			x1 = PAD_LEFT + (graphWidth / 2);
			y1 = PAD_TOP - OFFSET_TOP;
			g2.drawString(graphLabel, x1, y1);

			// draw x label
			x1 = PAD_LEFT + (graphWidth / 2);
			y1 = height - OFFSET_BOTTOM;
			g2.drawString(xLabel, x1, y1);

			// draw x axis
			x1 = PAD_LEFT;
			y1 = height - PAD_BOTTOM;
			x2 = width - PAD_RIGHT;
			y2 = y1;
			g2.drawLine(x1, y1, x2, y2);

			// draw scale on x axis
			x1 = PAD_LEFT + gapWidth + (barWidth / 2);
			y1 = height - PAD_BOTTOM;
			y2 = y1 + SCALE_LENGTH;
			try {

				Dashboard_DAO dao = new Dashboard_DAO();
				Vector<DashBar> barchart = dao.ProductBalance();
				Object[] list = new Object[barchart.size()];
				int row = 0;
				for (DashBar index : barchart) {
					list[row] = index.getGood_id();
					row++;
				}

				int[] ar = new int[list.length];
				int p_id;
				for (int i = 0; i < scaledData.length; i++) {
					x2 = x1;
					g2.drawLine(x1, y1, x2, y2);
					int fontHeight = 10;
					ar[i] = (Integer)list[i];
					p_id = ar[i];
					g2.drawString("id- " + p_id, x1, y2 + fontHeight);
					// move right one block
					x1 += gapWidth + barWidth;
				}
				System.out.println(Arrays.toString(ar));
			} catch (Exception e) {
				// TODO: handle exception
			}

			// draw y label
			x1 = PAD_LEFT;
			y1 = OFFSET_TOP;
			g2.drawString(yLabel, x1, y1);

			// draw y axis
			x1 = PAD_LEFT;
			y1 = PAD_TOP;
			x2 = PAD_LEFT;
			y2 = height - PAD_BOTTOM;
			g2.drawLine(x1, y1, x2, y2);

			// draw scale on y axis
			x1 = PAD_LEFT - SCALE_LENGTH;
			x2 = PAD_LEFT;
			y1 = height - PAD_BOTTOM;
			for (int i = 0; i <= scaledData.length + 1; i++) {
				y2 = y1;
				g2.drawLine(x1, y1, x2, y2);
				g2.drawString(String.valueOf(dataBlockSize * i), OFFSET_X_AXIS, y1);
				// move up one block
				y1 -= yBlockSize;
				// y1 = (height - PAD_BOTTOM) - (i*yBlockSize);
			}

			// HSV color setting
			float hue = 20f;
			float saturation = .7f;
			float brightness = .5f;
			int inc = 360 / data.length;

			// draw graph
			x1 = PAD_LEFT + gapWidth;
			for (int i = 0; i < data.length; i++) {

				y1 = height - PAD_BOTTOM - scaledData[i];
				Color color = new Color(Color.HSBtoRGB((hue / 360), saturation, brightness));
				g2.setColor(color);
				g2.fillRect(x1, y1, barWidth, scaledData[i] - 1);
				g2.setColor(Color.BLACK);
				g2.drawRect(x1, y1, barWidth, scaledData[i] - 1);
				g2.drawString(String.valueOf(data[i]), x1, y1);

				x1 += barWidth + gapWidth;

				hue += inc;
			}

		}

	}

