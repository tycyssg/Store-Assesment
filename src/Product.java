/**
 * @author Ciprian Tudose
 * Store Assesment
 */
import java.io.Serializable;

public class Product implements Serializable{


	private String id;
	private String productType;
	private String productName;
	private double productPrice;
	private int quantity;
	
	
	public Product() {};
	
	public Product(int id,String productType, String productName, double productPrice, int quantity) {
		this.id = id+productName.substring(0,3);
		this.productType = productType;
		this.productName = productName;
		this.productPrice = productPrice;
		this.quantity = quantity;
	}


	public String getId() {
		return id;
	}

	public String getProductType() {
		return productType;
	}


	public void setProductType(String productType) {
		this.productType = productType;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public double getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	
	
}
