package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import model.Order;


public class Recipt_DAO {
	
	public static List<Order> ShowOrder()throws SQLException, ClassNotFoundException {
		System.out.println("Products Order");
		List<Order > order = new ArrayList<Order>();
		
		try {
			String sql = "SELECT * FROM cart ORDER BY id ASC";
			System.out.println("selectSql:" + sql);

			ConnectionFactory connDB = new ConnectionFactory();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(sql);
				while (rs.next()) {
					int id = rs.getInt("id");
					int pid = rs.getInt("product_id");
					int qty = rs.getInt("quantity");
					double total = rs.getDouble("total");
					String user = rs.getString("user");
					String card_type = rs.getString("card_type");
					String ccname = rs.getString("namecard");
					String ccnum = rs.getString("cardnumber");
					String date = rs.getString("date");
					String status = rs.getString("status");
					Order or = new Order(id, pid, qty, total, user, card_type, ccname, ccnum, date, status);
					System.out.println(or.toString());
					order.add(or);
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
		return order;
	}
	
	public boolean UpdateOrder(String status, int  id) {
		System.out.println("Update Order Status");
		boolean set = false;
		try {
			String sql = "UPDATE cart SET status = '"+status+"' WHERE id = '"+id+"'";
			System.out.println(sql);
			ConnectionFactory connDB = new ConnectionFactory();
			Connection con = connDB.getConnection();
			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.execute(sql);
				stmnt.close();
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

	

}
