package entity;

public class Product {
	private int proId;
	private String proName;
	private int price;
	private int ctId;
	private String description;
	
	public Product() {
	}
	public Product(int proId, String proName, int price, int ctId) {
		this.proId = proId;
		this.proName = proName;
		this.price = price;
		this.ctId = ctId;
	}
	
	//proIdセッターゲッター
	public void setProId(int proId) {
		this.proId = proId;	
	}
	public int getProId() {
		return proId;
	}
	
	//proNameセッターゲッター
	public void setProName(String proName) {
		this.proName = proName;	
	}
	public String getProName() {
		return proName;
	}
	
	//priceセッターゲッター
	public void setPrice(int price) {
		this.price = price;	
	}
	public int getPrice() {
		return price;
	}
	
	//categorieIdセッターゲッター
	public void setCtId(int ctId) {
		this.ctId = ctId;	
	}
	public int getCtId() {
		return ctId;
	}
	
	//descriptionセッターゲッター
	public void setDescription(String description) {
		this.description = description;	
	}
	public String getDescription() {
		return description;
	}
	
}
