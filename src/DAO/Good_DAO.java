package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.function.Supplier;

import model.Good;
import model.Supplier_Good;

public class Good_DAO {
	
	public boolean addGood(Good good) {
		System.out.println("Add Good");
		boolean set = false;
		try {
			String sql = "INSERT INTO suppler_good (sup_id, title, image, price, status) "
					+ "VALUES ('"+good.s_id+"', '"+good.name+"', '"+good.image+"', '"+good.price+"',0)";
			System.out.println(sql);
			ConnectionFactory connDB = new ConnectionFactory();
			Connection con = connDB.getConnection();
			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.execute(sql);
				stmnt.close();
				set = true;
				con.close();
				System.out.println(" successfully.");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return set;
	}
	public boolean editGood(Good good) {
		System.out.println("Update Good");
		boolean set = false;
		try {
			String sql = "UPDATE suppler_good SET title = '"+good.name+"', image = '"+good.image+"', price = '"+good.price+"' "
					+ "WHERE suppler_good.g_id = '"+good.g_id+"'";
			System.out.println(sql);
			ConnectionFactory connDB = new ConnectionFactory();
			Connection con = connDB.getConnection();
			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.execute(sql);
				stmnt.close();
				con.close();
				set = true;
				System.out.println(" successfully.");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return set;
	}
	
	public boolean deleteGood(int id) {
		System.out.println("Delete Good");
		boolean set = false;
		try {
			String sql = "DELETE FROM suppler_good WHERE g_id='"+id+"'";
			System.out.println(sql);
			ConnectionFactory connDB = new ConnectionFactory();
			Connection con = connDB.getConnection();
			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.execute(sql);
				stmnt.close();
				con.close();
				set = true;
				System.out.println(" successfully.");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return set;
	}
	public static Vector<Good> searchGood(int g_id)throws SQLException, ClassNotFoundException {
		System.out.println("Search Goods");
		Vector<Good > good = new Vector();
		try {
			String searchSql = "SELECT * FROM suppler_good WHERE g_id='"+g_id+"'";
			System.out.println("selectSql:" + searchSql);

			ConnectionFactory connDB = new ConnectionFactory();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(searchSql);
				if (rs.next()) {
					int s_id = rs.getInt("sup_id");
					g_id = rs.getInt("g_id");
					String name = rs.getString("title");
					double price = rs.getDouble("price");
					String image = rs.getString("image");
					Good g = new Good(s_id,g_id,name,price,image);
					System.out.println(g.toString());
					good.add(g);
					System.out.println("succesfully");
				}
				stmnt.close();
				con.close();
				System.out.println("Searched successfully.");
			}
		} catch (NumberFormatException ex) {
			System.err.println("Error! Invalid plate");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return good;
	}
	public static Vector<Good> viewGood()throws SQLException, ClassNotFoundException {
		System.out.println("View Goods");
		Vector<Good > good = new Vector();
		try {
			String searchSql = "SELECT * FROM suppler_good";
			System.out.println("selectSql:" + searchSql);

			ConnectionFactory connDB = new ConnectionFactory();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(searchSql);
				while (rs.next()) {
					int s_id = rs.getInt("sup_id");
					int g_id = rs.getInt("g_id");
					String name = rs.getString("title");
					double price = rs.getDouble("price");
					String image = rs.getString("image");
					Good g = new Good(s_id,g_id,name,price,image);
					System.out.println(g.toString());
					good.add(g);
					System.out.println("succesfully");
				}
				stmnt.close();
				con.close();
				System.out.println("Searched successfully.");
			}
		} catch (NumberFormatException ex) {
			System.err.println("Error! Invalid plate");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return good;
	}
	
	public static Vector<Good> ChoiseGood()throws SQLException, ClassNotFoundException {
		System.out.println("View Good Choise");
		Vector<Good > good = new Vector();
		try {
			String searchSql = "SELECT * FROM suppler_good WHERE status = 0";
			System.out.println("selectSql:" + searchSql);

			ConnectionFactory connDB = new ConnectionFactory();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(searchSql);
				while (rs.next()) {
					int s_id = rs.getInt("sup_id");
					int g_id = rs.getInt("g_id");
					String name = rs.getString("title");
					double price = rs.getDouble("price");
					String image = rs.getString("image");
					Good g = new Good(s_id,g_id,name,price,image);
					System.out.println(g.toString());
					good.add(g);
					System.out.println("succesfully");
				}
				stmnt.close();
				con.close();
				System.out.println("Searched successfully.");
			}
		} catch (NumberFormatException ex) {
			System.err.println("Error! Invalid plate");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return good;
	}
	
	public boolean UpdateChoise(int id) {
		System.out.println("Update Good Choise");
		boolean set = false;
		try {
			String sql = "UPDATE suppler_good SET status = '1' WHERE suppler_good.g_id ="+id;
			System.out.println(sql);
			ConnectionFactory connDB = new ConnectionFactory();
			Connection con = connDB.getConnection();
			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.execute(sql);
				stmnt.close();
				con.close();
				set = true;
				System.out.println(" successfully.");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return set;
	}
	public boolean Updatestatus(int id) {
		System.out.println("Update Good Choise");
		boolean set = false;
		try {
			String sql = "UPDATE suppler_good SET status = '0' WHERE suppler_good.g_id ="+id;
			System.out.println(sql);
			ConnectionFactory connDB = new ConnectionFactory();
			Connection con = connDB.getConnection();
			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.execute(sql);
				stmnt.close();
				con.close();
				set = true;
				System.out.println(" successfully.");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return set;
	}
	
	public static Vector<Supplier_Good> viewSupplierGood()throws SQLException, ClassNotFoundException {
		System.out.println("View Supplier Good");
		Vector<Supplier_Good > good = new Vector();
		try {
			String searchSql = "SELECT * FROM supplier,suppler_good WHERE supplier.id = suppler_good.sup_id";
			System.out.println("selectSql:" + searchSql);

			ConnectionFactory connDB = new ConnectionFactory();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(searchSql);
				while (rs.next()) {
					int s_id = rs.getInt("sup_id");
					String s_name = rs.getString("name");
					int g_id = rs.getInt("g_id");
					String name = rs.getString("title");
					double price = rs.getDouble("price");
					String image = rs.getString("image");
					Supplier_Good g = new Supplier_Good(s_id,s_name,g_id,name,price,image);
					System.out.println(g.toString());
					good.add(g);
					System.out.println("succesfully");
				}
				stmnt.close();
				con.close();
				System.out.println("Searched successfully.");
			}
		} catch (NumberFormatException ex) {
			System.err.println("Error! Invalid plate");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return good;
	}

}
