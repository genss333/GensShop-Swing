  package MyApp;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

import Components.Navbar;
import Components.Logo_panel;
import CustomerComponent.Customer_Delete;
import CustomerComponent.Customer_Edit;
import CustomerComponent.Customer_Insert;
import CustomerComponent.Customer_Search;
import CustomerComponent.Customer_View;
import DashBoardComponent.DashBoard;
import GoodsComponent.Good_Delete;
import GoodsComponent.Good_Edit;
import GoodsComponent.Good_Insert;
import GoodsComponent.Good_Search;
import GoodsComponent.Good_View;
import Menu.Conorder;
import Menu.DashBoard_Item;
import Menu.Delete_Good;
import Menu.Deletecustomer;
import Menu.Deletesupplier;
import Menu.Edit_Good;
import Menu.Editcustomer;
import Menu.Editsupplier;
import Menu.GoodMenu;
import Menu.Insert_Good;
import Menu.Insetcustomer;
import Menu.Insetsupplier;
import Menu.Menucustomer;
import Menu.Menusuppiler;
import Menu.Search_Good;
import Menu.Searchcustomer;
import Menu.Searchsupplier;
import Menu.View_Good;
import Menu.Viewcustomer;
import Menu.Viewsupplier;
import OrderComponent.Confirm_Order;
import ProcessComponent.ProcessMenst;
import ProcessComponent.Process_Delete;
import ProcessComponent.Process_Insert;
import SupplierComponent.Supplier_Delete;
import SupplierComponent.Supplier_Edit;
import SupplierComponent.Supplier_Insert;
import SupplierComponent.Supplier_Search;
import SupplierComponent.Supplier_View;
import Menu.Process;
import Menu.ProcessInsert;
import Menu.ProcessDelete;
import Menu.Processment;

public class Admin_Menu extends JFrame implements ActionListener {

	class VerticalMenuBar extends JMenuBar {
		private final LayoutManager grid = new GridLayout(0, 1);

		public VerticalMenuBar() {
			setLayout(grid);
		}
	}


	class Mainmenu extends JMenu {
		public Mainmenu() {
			ImageIcon icon = new ImageIcon("./imge/house.png");
			setIcon(icon);
			setIconTextGap(25);
			setBorder(null);
			setFocusable(true);
			setForeground(Color.DARK_GRAY);
			setText("MENU ADMIN MANEGER");
		}
	}
	

	Runnable run;
	Navbar nav;
	JMenuBar menuBar_1;

	Customer_Insert cus;
	Customer_Edit cusedit;
	Customer_Delete cusdel;
	Customer_Search cussearch;
	Customer_View cusview;

	Good_Delete gooddel;
	Good_Insert goodadd;
	Good_Edit goodedit;
	Good_Search goodsearch;
	Good_View goodview;

	Supplier_Insert supinsert;
	Supplier_Edit supedit;
	Supplier_Delete supdel;
	Supplier_Search supfind;
	Supplier_View supview;

	DashBoard das;

	ProcessMenst proces;
	Process_Insert proceessadd;
	Process_Delete prodelete;

	Confirm_Order con;
	public JPanel mainpanel;

	final static String DASH_BOARD = "ADMIN DASH BOARD";
	final static String CON_ORDER = "ADMIN CONFIRM ORDER";
	final static String PROCESSMENT = "PROCESSMENTS";
	final static String PROCESS_DELETE = "DELETE PROCES";
	final static String PROCESS_INSERT = "INSERT PROCESS";

	final static String INSERT_CUSTOMER = "INSERT CUSTOMER";
	final static String EDIT_CUSTOMER = "EDIT CUSTOMER";
	final static String DELETE_CUSTOMER = "DELTE CUSTOMER";
	final static String SEARCH_CUSTOMER = "SEARCH CUSTOMER";
	final static String VIEW_CUSTOMER = "VIEW CUSTOMER";

	final static String INSERT_GOOD = "INSERT GOOD";
	final static String EDIT_GOOD = "EDIT GOOD";
	final static String DELETE_GOOD = "DELETE GOOD";
	final static String SEARCH_GOOD = "SEARCH GOOD";
	final static String VIEW_GOOD = "VIEW GOOD";

	final static String INSERT_SUPPLIER = "INSERT SUPPLIER";
	final static String EDIT_SUPPLIER = "EDIT SUPPLIER";
	final static String DELETE_SUPPLIER = "DELETE SUPPLIER";
	final static String SEARCH_SUPPLIER = "SEARCH SUPPLIER";
	final static String VIEW_SUPPLIER = "VIEW SUPPLIER";

	final static int width = 650;
	final static int height = 200;

	public Admin_Menu(Runnable Run) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
		setTitle("Admin Maneger RUY X3");
		this.run = Run;
		ImageIcon imgitem1 = new ImageIcon("./imge/house.png");
		setIconImage(imgitem1.getImage());

		mainpanel = new JPanel();
		mainpanel.setLayout(new CardLayout());
		nav = new Navbar();

		// ---menu panel----
		JPanel menupanel = new JPanel();
		menupanel.setLayout(new BorderLayout());

		Logo_panel logo = new Logo_panel();
		menupanel.add(logo, BorderLayout.NORTH);
		
		// ----------------menu---------------------------------
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.white);
		JTabbedPane tp = new JTabbedPane();
		tp.addTab("New tab", null, desktopPane, null);

		menuBar_1 = new VerticalMenuBar();
		menuBar_1.setBounds(0, 0, 250, 250);
		menuBar_1.setBorder(null);
		menuBar_1.setBackground(Color.white);
		desktopPane.add(menuBar_1);
		menupanel.add(desktopPane, BorderLayout.CENTER);

		// ----------------Dashboard--------------------------
		DashBoard_Item dashpage = new DashBoard_Item();
		dashpage.setText(DASH_BOARD);
		dashpage.addActionListener(this);
		menuBar_1.add(dashpage);
		das = new DashBoard(Run);
		mainpanel.add(das, DASH_BOARD);

		// Confirm order
		Conorder conorder = new Conorder();
		conorder.setText(CON_ORDER);
		conorder.addActionListener(this);
		menuBar_1.add(conorder);
		con = new Confirm_Order(Run);
		mainpanel.add(con, CON_ORDER);

		// menu
		JMenu minamenu = new Mainmenu();
		minamenu.addActionListener(this);
		menuBar_1.add(minamenu);

		// ---------------- processment menu------------------
		Process process = new Process();
		process.addActionListener(this);
		minamenu.add(process);
		// processment
		Processment proview = new Processment();
		proview.setText(PROCESSMENT);
		proview.addActionListener(this);
		proces = new ProcessMenst(Run);
		mainpanel.add(proces, PROCESSMENT);
		process.add(proview);
		// process insert
		ProcessInsert proinsert = new ProcessInsert();
		proinsert.setText(PROCESS_INSERT);
		proinsert.addActionListener(this);
		proceessadd = new Process_Insert(Run);
		mainpanel.add(proceessadd, PROCESS_INSERT);
		process.add(proinsert);
		
		ProcessDelete prodel = new ProcessDelete();
		prodel.setText(PROCESS_DELETE);
		prodel.addActionListener(this);
		prodelete = new Process_Delete(Run);
		mainpanel.add(prodelete, PROCESS_DELETE);
		process.add(prodel);

		// ---------supplier menu----------------------------
		Menusuppiler suppliermenu = new Menusuppiler();
		minamenu.add(suppliermenu);
		// insert
		Insetsupplier insertsup = new Insetsupplier();
		insertsup.setText(INSERT_SUPPLIER);
		insertsup.addActionListener(this);
		suppliermenu.add(insertsup);
		supinsert = new Supplier_Insert(Run);
		mainpanel.add(supinsert, INSERT_SUPPLIER);
		// edit
		Editsupplier editsup = new Editsupplier();
		editsup.setText(EDIT_SUPPLIER);
		editsup.addActionListener(this);
		suppliermenu.add(editsup);
		supedit = new Supplier_Edit(Run);
		mainpanel.add(supedit, EDIT_SUPPLIER);
		// delete
		Deletesupplier delsup = new Deletesupplier();
		delsup.setText(DELETE_SUPPLIER);
		delsup.addActionListener(this);
		suppliermenu.add(delsup);
		supdel = new Supplier_Delete(Run);
		mainpanel.add(supdel, DELETE_SUPPLIER);
		// search
		Searchsupplier findsup = new Searchsupplier();
		findsup.setText(SEARCH_SUPPLIER);
		findsup.addActionListener(this);
		suppliermenu.add(findsup);
		supfind = new Supplier_Search(Run);
		mainpanel.add(supfind, SEARCH_SUPPLIER);
		// view
		Viewsupplier viewsup = new Viewsupplier();
		viewsup.setText(VIEW_SUPPLIER);
		viewsup.addActionListener(this);
		suppliermenu.add(viewsup);
		supview = new Supplier_View(Run);
		mainpanel.add(supview, VIEW_SUPPLIER);

		// ---------- --good menu--------------------------
		GoodMenu goodmenu = new GoodMenu();
		minamenu.add(goodmenu);
		// insert good
		Insert_Good insertgood = new Insert_Good();
		insertgood.setText(INSERT_GOOD);
		insertgood.addActionListener(this);
		goodmenu.add(insertgood);
		goodadd = new Good_Insert(Run);
		mainpanel.add(goodadd, INSERT_GOOD);
		// edit good
		Edit_Good editgood = new Edit_Good();
		editgood.setText(EDIT_GOOD);
		editgood.addActionListener(this);
		goodmenu.add(editgood);
		goodedit = new Good_Edit(Run);
		mainpanel.add(goodedit, EDIT_GOOD);
		// delete good
		Delete_Good delgood = new Delete_Good();
		delgood.setText(DELETE_GOOD);
		delgood.addActionListener(this);
		goodmenu.add(delgood);
		gooddel = new Good_Delete(Run);
		mainpanel.add(gooddel, DELETE_GOOD);
		// search good
		Search_Good searchgood = new Search_Good();
		searchgood.setText(SEARCH_GOOD);
		searchgood.addActionListener(this);
		goodmenu.add(searchgood);
		goodsearch = new Good_Search(Run);
		mainpanel.add(goodsearch, SEARCH_GOOD);
		// view good
		View_Good viewgood = new View_Good();
		viewgood.setText(VIEW_GOOD);
		viewgood.addActionListener(this);
		goodmenu.add(viewgood);
		goodview = new Good_View(Run);
		mainpanel.add(goodview, VIEW_GOOD);

		// -----------customer menu--------------------------
		Menucustomer customermenu = new Menucustomer();
		minamenu.add(customermenu);
		// ------add------------
		Insetcustomer insertcustomer = new Insetcustomer();
		insertcustomer.setText(INSERT_CUSTOMER);
		insertcustomer.addActionListener(this);
		customermenu.add(insertcustomer);
		cus = new Customer_Insert(Run);
		mainpanel.add(cus, INSERT_CUSTOMER);
		// -------edit--------------
		Editcustomer editcustomer = new Editcustomer();
		editcustomer.setText(EDIT_CUSTOMER);
		editcustomer.addActionListener(this);
		customermenu.add(editcustomer);
		cusedit = new Customer_Edit(Run);
		mainpanel.add(cusedit, EDIT_CUSTOMER);
		// ----delete------------
		Deletecustomer delcustomer = new Deletecustomer();
		delcustomer.setText(DELETE_CUSTOMER);
		delcustomer.addActionListener(this);
		customermenu.add(delcustomer);
		cusdel = new Customer_Delete(Run);
		mainpanel.add(cusdel, DELETE_CUSTOMER);
		// -------search-----------
		Searchcustomer searchcustomer = new Searchcustomer();
		searchcustomer.setText(SEARCH_CUSTOMER);
		searchcustomer.addActionListener(this);
		customermenu.add(searchcustomer);
		cussearch = new Customer_Search(Run);
		mainpanel.add(cussearch, SEARCH_CUSTOMER);
		// -------- view--------------
		Viewcustomer viewcustomer = new Viewcustomer();
		viewcustomer.setText(VIEW_CUSTOMER);
		viewcustomer.addActionListener(this);
		customermenu.add(viewcustomer);
		cusview = new Customer_View(Run);
		mainpanel.add(cusview, VIEW_CUSTOMER);

		setLayout(new BorderLayout());
		add(menupanel, BorderLayout.WEST);

//		main panel
		add(mainpanel, BorderLayout.CENTER);
		add(nav, BorderLayout.NORTH);

		JScrollPane scroller = new JScrollPane(mainpanel);
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setBorder(null);
		getContentPane().add(scroller);
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		String command = evt.getActionCommand();
		System.out.println("command:" + command);
		Object source = evt.getSource();
		System.out.println("source:" + source.toString());

		// dashboard
		if (command.equals(DASH_BOARD)) {
			das = new DashBoard(run);
			mainpanel.remove(das);
			mainpanel.add(das, DASH_BOARD);
			CardLayout cardLayout = (CardLayout) mainpanel.getLayout();
			cardLayout.show(mainpanel, DASH_BOARD);
		}

		// invoice processs
		if (command.equals(PROCESSMENT)) {
			proces = new ProcessMenst(run);
			mainpanel.remove(proces);
			mainpanel.add(proces, PROCESSMENT);
			CardLayout cardLayout = (CardLayout) mainpanel.getLayout();
			cardLayout.show(mainpanel, PROCESSMENT);
		} else if (command.equals(PROCESS_INSERT)) {
			proceessadd = new Process_Insert(run);
			mainpanel.remove(proceessadd);
			mainpanel.add(proceessadd, PROCESS_INSERT);
			CardLayout cardLayout = (CardLayout) mainpanel.getLayout();
			cardLayout.show(mainpanel, PROCESS_INSERT);
		}
		else if (command.equals(PROCESS_DELETE)) {
			prodelete = new Process_Delete(run);
			mainpanel.remove(proceessadd);
			mainpanel.add(prodelete, PROCESS_DELETE);
			CardLayout cardLayout = (CardLayout) mainpanel.getLayout();
			cardLayout.show(mainpanel, PROCESS_DELETE);
		}
		
		// customer
		if (command.equals(INSERT_CUSTOMER)) {
			
			cus = new Customer_Insert(run);
			mainpanel.remove(cus);
			mainpanel.add(cus, INSERT_CUSTOMER);
			CardLayout cardLayout = (CardLayout) mainpanel.getLayout();
			cardLayout.show(mainpanel, INSERT_CUSTOMER);
		} else if (command.equals(EDIT_CUSTOMER)) {
			
			cusedit = new Customer_Edit(run);
			mainpanel.remove(cusedit);
			mainpanel.add(cusedit, EDIT_CUSTOMER);
			CardLayout cardLayout = (CardLayout) mainpanel.getLayout();
			cardLayout.show(mainpanel, EDIT_CUSTOMER);
		} else if (command.equals(DELETE_CUSTOMER)) {
			
			cusdel = new Customer_Delete(run);
			mainpanel.remove(cusdel);
			mainpanel.add(cusdel, DELETE_CUSTOMER);
			CardLayout cardLayout = (CardLayout) mainpanel.getLayout();
			cardLayout.show(mainpanel, DELETE_CUSTOMER);
		} else if (command.equals(SEARCH_CUSTOMER)) {
			
			cussearch = new Customer_Search(run);
			mainpanel.remove(cussearch);
			mainpanel.add(cussearch, SEARCH_CUSTOMER);
			CardLayout cardLayout = (CardLayout) mainpanel.getLayout();
			cardLayout.show(mainpanel, SEARCH_CUSTOMER);
		} else if (command.equals(VIEW_CUSTOMER)) {
			
			cusview = new Customer_View(run);
			mainpanel.remove(cusview);
			mainpanel.add(cusview, VIEW_CUSTOMER );
			CardLayout cardLayout = (CardLayout) mainpanel.getLayout();
			cardLayout.show(mainpanel, VIEW_CUSTOMER);
		}

		// good
		if (command.equals(INSERT_GOOD)) {
			goodadd = new Good_Insert(run);
			mainpanel.remove(goodadd);
			mainpanel.add(goodadd, INSERT_GOOD);
			CardLayout cardLayout = (CardLayout) mainpanel.getLayout();
			cardLayout.show(mainpanel, INSERT_GOOD);
			
		} else if (command.equals(EDIT_GOOD)) {
			goodedit = new Good_Edit(run);
			mainpanel.remove(goodedit);
			mainpanel.add(goodedit, EDIT_GOOD);
			CardLayout cardLayout = (CardLayout) mainpanel.getLayout();
			cardLayout.show(mainpanel, EDIT_GOOD);
			
		} else if (command.equals(DELETE_GOOD)) {
			gooddel = new Good_Delete(run);
			mainpanel.remove(gooddel);
			mainpanel.add(gooddel, DELETE_GOOD);
			CardLayout cardLayout = (CardLayout) mainpanel.getLayout();
			cardLayout.show(mainpanel, DELETE_GOOD);
			
		} else if (command.equals(SEARCH_GOOD)) {
			goodsearch = new Good_Search(run);
			mainpanel.remove(goodsearch);
			mainpanel.add(goodsearch, SEARCH_GOOD);
			CardLayout cardLayout = (CardLayout) mainpanel.getLayout();
			cardLayout.show(mainpanel, SEARCH_GOOD);
			
		} else if (command.equals(VIEW_GOOD)) {
			goodview = new Good_View(run);
			mainpanel.remove(goodview);
			mainpanel.add(goodview, VIEW_GOOD);
			CardLayout cardLayout = (CardLayout) mainpanel.getLayout();
			cardLayout.show(mainpanel, VIEW_GOOD);
		}

		// supplier

		if (command.equals(INSERT_SUPPLIER)) {
			supinsert = new Supplier_Insert(run);
			mainpanel.remove(supinsert);
			mainpanel.add(supinsert, INSERT_SUPPLIER);
			CardLayout cardLayout = (CardLayout) mainpanel.getLayout();
			cardLayout.show(mainpanel, INSERT_SUPPLIER);
		} else if (command.equals(EDIT_SUPPLIER)) {
			supedit = new Supplier_Edit(run);
			mainpanel.remove(supedit);
			mainpanel.add(supedit, EDIT_SUPPLIER);
			CardLayout cardLayout = (CardLayout) mainpanel.getLayout();
			cardLayout.show(mainpanel, EDIT_SUPPLIER);
		} else if (command.equals(DELETE_SUPPLIER)) {
			supdel = new Supplier_Delete(run);
			mainpanel.remove(supdel);
			mainpanel.add(supdel, DELETE_SUPPLIER);
			CardLayout cardLayout = (CardLayout) mainpanel.getLayout();
			cardLayout.show(mainpanel, DELETE_SUPPLIER);
		} else if (command.equals(SEARCH_SUPPLIER)) {
			supfind = new Supplier_Search(run);
			mainpanel.remove(supfind);
			mainpanel.add(supfind, SEARCH_SUPPLIER);
			CardLayout cardLayout = (CardLayout) mainpanel.getLayout();
			cardLayout.show(mainpanel, SEARCH_SUPPLIER);
		} else if (command.equals(VIEW_SUPPLIER)) {
			supview = new Supplier_View(run);
			mainpanel.remove(supview);
			mainpanel.add(supview, VIEW_SUPPLIER);
			CardLayout cardLayout = (CardLayout) mainpanel.getLayout();
			cardLayout.show(mainpanel, VIEW_SUPPLIER);
		}

		// order

		if (command.equals(CON_ORDER)) {
			con = new Confirm_Order(run);
			mainpanel.remove(con);
			mainpanel.add(con,CON_ORDER);
			CardLayout cardLayout = (CardLayout) mainpanel.getLayout();
			cardLayout.show(mainpanel, CON_ORDER);
		}

	}

}
