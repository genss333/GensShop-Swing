package model;

public class Supplier_Good {
	public int s_id;
	public String s_name;
	public int g_id;
	public String name;
	public double price;
	public String image;
	public Supplier_Good(int s_id, String s_name, int g_id, String name, double price, String image) {
		super();
		this.s_id = s_id;
		this.s_name = s_name;
		this.g_id = g_id;
		this.name = name;
		this.price = price;
		this.image = image;
	}
	@Override
	public String toString() {
		return "Supplier_Good [s_id=" + s_id + ", s_name=" + s_name + ", g_id=" + g_id + ", name=" + name + ", price="
				+ price + ", image=" + image + "]";
	}
	public int getS_id() {
		return s_id;
	}
	public void setS_id(int s_id) {
		this.s_id = s_id;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public int getG_id() {
		return g_id;
	}
	public void setG_id(int g_id) {
		this.g_id = g_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	
}
