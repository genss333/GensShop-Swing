package model;

public class Recipt {
	int rec_id;
	int g_id;
	String good_name;
	double price;
	int qty;
	double amount;
	String cus_name;
	String cus_phone;
	String email;
	int status;
	public Recipt(int rec_id, int g_id, String good_name, double price, int qty, double amount, String cus_name,
			String cus_phone, String email, int status) {
		super();
		this.rec_id = rec_id;
		this.g_id = g_id;
		this.good_name = good_name;
		this.price = price;
		this.qty = qty;
		this.amount = amount;
		this.cus_name = cus_name;
		this.cus_phone = cus_phone;
		this.email = email;
		this.status = status;
	}
	@Override
	public String toString() {
		return "Recipt [rec_id=" + rec_id + ", g_id=" + g_id + ", good_name=" + good_name + ", price=" + price
				+ ", qty=" + qty + ", amount=" + amount + ", cus_name=" + cus_name + ", cus_phone=" + cus_phone
				+ ", email=" + email + ", status=" + status + "]";
	}
	public int getRec_id() {
		return rec_id;
	}
	public void setRec_id(int rec_id) {
		this.rec_id = rec_id;
	}
	public int getG_id() {
		return g_id;
	}
	public void setG_id(int g_id) {
		this.g_id = g_id;
	}
	public String getGood_name() {
		return good_name;
	}
	public void setGood_name(String good_name) {
		this.good_name = good_name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCus_name() {
		return cus_name;
	}
	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}
	public String getCus_phone() {
		return cus_phone;
	}
	public void setCus_phone(String cus_phone) {
		this.cus_phone = cus_phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
}
