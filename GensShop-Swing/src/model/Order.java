package model;

public class Order{
	
	int id;
	int pid;
	int qty;
	double total;
	String user;
	String cctype;
	String ccname;
	String ccnum;
	String date;
	String status;
	public Order(int id, int pid, int qty, double total, String user, String cctype, String ccname, String ccnum,
			String date, String status) {
		super();
		this.id = id;
		this.pid = pid;
		this.qty = qty;
		this.total = total;
		this.user = user;
		this.cctype = cctype;
		this.ccname = ccname;
		this.ccnum = ccnum;
		this.date = date;
		this.status = status;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", pid=" + pid + ", qty=" + qty + ", total=" + total + ", user=" + user + ", cctype="
				+ cctype + ", ccname=" + ccname + ", ccnum=" + ccnum + ", date=" + date + ", status=" + status + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getCctype() {
		return cctype;
	}
	public void setCctype(String cctype) {
		this.cctype = cctype;
	}
	public String getCcname() {
		return ccname;
	}
	public void setCcname(String ccname) {
		this.ccname = ccname;
	}
	public String getCcnum() {
		return ccnum;
	}
	public void setCcnum(String ccnum) {
		this.ccnum = ccnum;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	
}

