package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Products;

public class ProductDAO {
	
	private static final String SELECT_PRODUCTS_ALL = "SELECT * FROM product";
	
	public boolean InsertProducts(Products p) {
		System.out.println("Add Product");
		boolean set = false;
		try {
			String sql = "INSERT INTO product (id, title, des, image, price, sale, collection, brand, instock, status, supplier, inshop, date) "
					+ "VALUES ('"+p.id+"', '"+p.title+"', '"+p.des+"', '"+p.image+"', '"+p.price+"', 0, '"+p.collection+"', '"+p.brand+"', "
							+ "'"+p.stk+"', '', '"+p.supplier+"', 'no', '"+p.date+"')";
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
	
	public boolean UpdateProducts(String des, double sale, String collection, String brand, int stk, String status, String inshop, String shopdate, int id) {
		System.out.println("Update Product");
		boolean set = false;
		try {
			String sql = "UPDATE product SET des= '"+des+"', sale = '"+sale+"', collection = '"+collection+"', brand = '"+brand+"', instock = '"+stk+"', status = '"+status+"',"
					+ "inshop = '"+inshop+"', date = '"+shopdate+"'"
					+ "WHERE id = '"+id+"'";
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
	
	public static List<Products> ProductsAll()throws SQLException, ClassNotFoundException {
		System.out.println("Products all");
		List<Products > products = new ArrayList<Products>();
		try {
			System.out.println("selectSql:" + SELECT_PRODUCTS_ALL);

			ConnectionFactory connDB = new ConnectionFactory();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(SELECT_PRODUCTS_ALL);
				while (rs.next()) {
					int id = rs.getInt("id");
					String title = rs.getString("title");
					String des = rs.getString("des");
					String image = rs.getString("image");
					double price = rs.getDouble("price");
					double sale = rs.getDouble("sale");
					String collection = rs.getString("collection");
					String brand = rs.getString("brand");
					int stk =  rs.getInt("instock");
					String inshop = rs.getString("inshop");
					String status = rs.getString("status");
					int sup_id = rs.getInt("supplier");
					String shopdate = rs.getString("date");
					Products pro = new Products(id, title, des, image, price, sale, collection, brand, stk, status, sup_id, inshop, shopdate);
					System.out.println(pro.toString());
					products.add(pro);
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
		return products;
	}
	
	public static List<Products> ProductsDetail(int id)throws SQLException, ClassNotFoundException {
		System.out.println("Products Detail");
		List<Products > products = new ArrayList<Products>();
		try {
			String sql = "SELECT * FROM product WHERE id ='"+id+"' AND instock > 0 AND inshop = 'yes'";
			System.out.println("selectSql:" + sql);

			ConnectionFactory connDB = new ConnectionFactory();
			Connection con = connDB.getConnection();

			Statement stmnt = null;
			if (con != null) {
				stmnt = con.createStatement();
				ResultSet rs = stmnt.executeQuery(sql);
				while (rs.next()) {
					id = rs.getInt("id");
					String title = rs.getString("title");
					String des = rs.getString("des");
					String image = rs.getString("image");
					double price = rs.getDouble("price");
					double sale = rs.getDouble("sale");
					String collection = rs.getString("collection");
					String brand = rs.getString("brand");
					int stk =  rs.getInt("instock");
					String inshop = rs.getString("inshop");
					String status = rs.getString("status");
					int sup_id = rs.getInt("supplier");
					String shopdate = rs.getString("date");
					Products pro = new Products(id, title, des, image, price, sale, collection, brand, stk, status, sup_id, inshop, shopdate);
					System.out.println(pro.toString());
					products.add(pro);
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
		return products;
	}
	
	public boolean DeleteProduct(int id){
		System.out.println("Delete Product");
		boolean set = false;
		try {
			String sql = "DELETE FROM product WHERE id='"+id+"'";
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

}
