package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import model.Customer;

public class Customer_DAO {
	
	public boolean addCustomer(Customer auth){
		System.out.println("Insert User");
		boolean set = false;
		try {
			String sql = "INSERT INTO USER (FIRSTNAME,LASTNAME,EMAIL,PASSWORD,STATUS)"
					+ "VALUES ('"+auth.fname+"', '"+auth.lname+"', '"+auth.email+"', '"+auth.password+"','0')";
			String sql2 = "INSERT INTO book (email, image, phone, street, city, state, zipcode) "
					+ "VALUES ('"+auth.email+"', '-', '-', '-', '-', '-', 0)";
			System.out.println(sql);
			System.out.println(sql2);
			ConnectionFactory connDB = new ConnectionFactory();
			Connection con = connDB.getConnection();
			Statement stmnt = null;
			Statement stmnt2 = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt2 = con.createStatement();
				stmnt.execute(sql);
				stmnt2.execute(sql2);
				set= true;
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
	public boolean editCustomer(Customer auth){
		boolean set = false;
		try {
			String sql = "UPDATE user SET firstname = '"+auth.fname+"', lastname = '"+auth.lname+"', "
					+ "email = '"+auth.email+"',password = '"+auth.password+"'"
					+ " WHERE email = '"+auth.email+"'";
			System.out.println(sql);
			ConnectionFactory connDB = new ConnectionFactory();
			Connection con = connDB.getConnection();
			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				stmnt.execute(sql);
				set= true;
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
	
	public boolean deleteCustomer(String  email) {
		System.out.println("Delete Customer");
		boolean set = false;
		try {
			String sql = "DELETE FROM user WHERE email='"+email+"'";
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
	
	public boolean deleteBook(String  email) {
		System.out.println("Delete Book");
		boolean set = false;
		try {
			String sql = "DELETE FROM book WHERE email='"+email+"'";
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
	
	public static List<Customer> searchCustomer(String  email)throws SQLException, ClassNotFoundException {
		System.out.println("Search Customer");
		List<Customer > customer = new ArrayList<Customer>();
		String sql = "SELECT * FROM user WHERE email='"+email+"'";
		System.out.println(sql);
		try {
			ConnectionFactory connDB = new ConnectionFactory();
			Connection con = connDB.getConnection();
			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(sql);
				while (rs.next()) {
					email = rs.getString("email");
					String password = rs.getString("password");
					String fname = rs.getString("firstname");
					String lname = rs.getString("lastname");
					Customer cus = new Customer(fname,lname,email,password);
					System.out.println(cus.toString());
					customer.add(cus);
				}
			}
			
		}catch (NumberFormatException ex) {
			System.err.println("Error! Invalid plate");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return customer;
	}
	
	public static List<Customer> viewCustomer()throws SQLException, ClassNotFoundException {
		System.out.println("List User");
		List<Customer > customer = new ArrayList<Customer>();
			String sql = "SELECT * FROM user";
			System.out.println(sql);
			try {
				ConnectionFactory connDB = new ConnectionFactory();
				Connection con = connDB.getConnection();
				Statement stmnt = null;
				if (con != null) {
					stmnt = con.createStatement();
					ResultSet rs = stmnt.executeQuery(sql);
					while (rs.next()) {
						String email = rs.getString("email");
						String password = rs.getString("password");
						String fname = rs.getString("firstname");
						String lname = rs.getString("lastname");
						Customer cus = new Customer(fname,lname,email,password);
						System.out.println(cus.toString());
						customer.add(cus);
					}
				}
				
			}catch (NumberFormatException ex) {
				System.err.println("Error! Invalid plate");
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		
		return customer;
	}
	
	
	

}
