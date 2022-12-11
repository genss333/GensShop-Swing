package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import model.Suplier;

public class Suplier_DAO {
	
	public void addSuplier(Suplier sup) {
		System.out.println("Add Suplier");
		
		try {
			String sql = "INSERT INTO supplier (name, address, phone, email) "
					+ "VALUES ('"+sup.name +"','"+sup.address +"','"+sup.tel+"','"+sup.email+"')";
			System.out.println(sql);
			ConnectionFactory connDB = new ConnectionFactory();
			Connection con = connDB.getConnection();
			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.execute(sql);
				stmnt.close();
				con.close();
				System.out.println("Suplier added successfully.");
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	public boolean editSuplier(Suplier sup) {
		System.out.println("Edit Suplier");
		boolean set = false;
		try {
			String sql = "UPDATE supplier SET name = '"+sup.name+"', address = '"+sup.address+"', phone = '"+sup.tel+"', email = '"+sup.email+"' WHERE id ="+sup.id+"";
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
				System.out.println("Suplier added successfully.");
			}
			
		}catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return set;
	}
	public boolean deleteSuplier(int id) {
		System.out.println("Delete Suplier");
		boolean set = false;
		try {
			String sql = "DELETE FROM supplier WHERE id='"+id+"'";
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
	public static Vector<Suplier> searchSuplier(int id)throws SQLException, ClassNotFoundException {
		System.out.println("Search Suplier");
		Vector<Suplier > suplier = new Vector();
		try {
			String searchSql = "SELECT * FROM supplier WHERE id=" + id;
			System.out.println("selectSql:" + searchSql);

			ConnectionFactory connDB = new ConnectionFactory();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(searchSql);
				if (rs.next()) {
					id = rs.getInt("id");
					String name = rs.getString("name");
					String address = rs.getString("address");
					String tel = rs.getString("phone");
					String email = rs.getString("email");
					Suplier sup = new Suplier(id,name,address,tel,email);
					System.out.println(sup.toString());
					suplier.add(sup);
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
		return suplier;
	}
	public static Vector<Suplier> viewSuplier()throws SQLException, ClassNotFoundException {
		System.out.println("View Suplier");
		Vector<Suplier > suplier = new Vector();
		try {
			String searchSql = "SELECT * FROM supplier ";
			System.out.println("selectSql:" + searchSql);

			ConnectionFactory connDB = new ConnectionFactory();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(searchSql);
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String address = rs.getString("address");
					String tel = rs.getString("phone");
					String email = rs.getString("email");
					Suplier sup = new Suplier(id,name,address,tel,email);
					System.out.println(sup.toString());
					suplier.add(sup);
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
		return suplier;
	}
	

}
