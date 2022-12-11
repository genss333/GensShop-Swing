package model;

public class Invoice {
	int id;
	int sup_id;
	String sup_name;
	int good_id;
	String good_name;
	double price;
	int qty;
	double amount;
	int status;
	String date;
	public Invoice(int id, int sup_id, String sup_name, int good_id, String good_name, double price, int qty,
			double amount, int status, String date) {
		super();
		this.id = id;
		this.sup_id = sup_id;
		this.sup_name = sup_name;
		this.good_id = good_id;
		this.good_name = good_name;
		this.price = price;
		this.qty = qty;
		this.amount = amount;
		this.status = status;
		this.date = date;
	}
	@Override
	public String toString() {
		return "Invoice [id=" + id + ", sup_id=" + sup_id + ", sup_name=" + sup_name + ", good_id=" + good_id
				+ ", good_name=" + good_name + ", price=" + price + ", qty=" + qty + ", amount=" + amount + ", status="
				+ status + ", date=" + date + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSup_id() {
		return sup_id;
	}
	public void setSup_id(int sup_id) {
		this.sup_id = sup_id;
	}
	public String getSup_name() {
		return sup_name;
	}
	public void setSup_name(String sup_name) {
		this.sup_name = sup_name;
	}
	public int getGood_id() {
		return good_id;
	}
	public void setGood_id(int good_id) {
		this.good_id = good_id;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
	

}
