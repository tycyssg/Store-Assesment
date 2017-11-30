/**
 * @author Ciprian Tudose
 * Store Assesment
 */
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class StoreActions {

	private ExtractProductsFromFile extractItems = new ExtractProductsFromFile();
	private SaveProductsInFile save = new SaveProductsInFile();
	private Returns r = new Returns();
	private DecimalFormat df = new DecimalFormat("€0.00");
	
	static ArrayList<Product> products = new ArrayList<>();
	private static ArrayList<Double> tempPrices = new ArrayList<>();
	private static double earnings = 0;
	private static double percentage = 0;

	
	
	//block of code
	{
		try {
		products = extractItems.extractTheProducts();
		earnings = extractItems.extractEarnings();
		}catch(Exception e) {
			
		}
	}

	
	public void saveItems() throws ClassNotFoundException, IOException {
		welcomeMessage();
		save.addItems();
	}
	public void saveAnItem() throws ClassNotFoundException, IOException {
		addMessage();
		save.addItems();
	}

	public void sell() throws ClassNotFoundException, IOException {
		String answer = "";
		System.out.println("Sell items option is active! Please proceed.");
		System.out.println();

		do {
			Product p = checkId();
			int quantity = checkQuantity(p);

			if (quantity == 0) {
				System.out.println("This sale has been cancel");
			} else {
				sellProducts(p, quantity);
			}

			System.out.println();
			System.out.println("Do you want to sell other product ? Press Yes/No");
			answer = r.returnString().toLowerCase();

		} while (!answer.equals("no"));

	}

	private int checkQuantity(Product product) throws ClassNotFoundException, IOException {
		boolean wrongQuatity = false;
		String answer = "";
		int quantity = 0;

		do {
			System.out.println("Please input the quantity ");
			quantity = r.returnInt();

			if (quantity > product.getQuantity()) {
				System.out.println("ERROR ! The quantity requested is bigger than our stock");
				wrongQuatity = true;
			}

			if (wrongQuatity) {
				System.out.println("Do you want to cancel ? Press Yes / No");
				answer = r.returnString().toLowerCase();
				quantity = 0;
			} else {
				answer = "yes";
			}

			wrongQuatity = false;

		} while (answer.equals("no"));

		return quantity;

	}

	private Product checkId() throws ClassNotFoundException, IOException {
		int position = 0;
		boolean idNotExist = true;

		do {
			System.out.println("Please input the item id");
			String id = r.returnString();

			for (int i = 0; i < products.size(); i++) {
				if (id.equalsIgnoreCase(products.get(i).getId())) {
					idNotExist = false;
					position = i;
				}
			}

			if (idNotExist) {
				System.out.println("ERROR ! This item doesn't exist in our stock.");
				System.out.println("Please try again !");
				System.out.println();
			}

		} while (idNotExist);

		return products.get(position);
	}

	private void sellProducts(Product product, int quantity) throws ClassNotFoundException, IOException {
		System.out.println();
		System.out.println("You select " + product.getProductName() + " which cost "+ df.format(product.getProductPrice()) + " each");
		
		int index = products.indexOf(product);
		int productsLeft = product.getQuantity() - quantity;
		double newEarnings = product.getProductPrice() * quantity;
		earnings += newEarnings;

		product.setQuantity(productsLeft);
		
		System.out.println("Products successfully selled.");
		System.out.println(product.getQuantity() + " " + product.getProductName() + " left in the stock.");
		
		products.set(index, product);

	}

	
	//update Stocks
	public void updateStocks() throws ClassNotFoundException, IOException {
		
		System.out.println("The following products exist in the StoreDB");
		System.out.println("Please type the id of any product to update the stock");
		for(Product prod : products) {
			displayNamesAndId(prod);
		}
		String answer = "";
		
		do {
			Product productToUpdate = checkId();
			System.out.println("Please input the quantity to update the stock");
			int quantity = 0;
			try {
				quantity = r.returnInt();
			}catch(InputMismatchException e) {
				System.out.println("ERROR ! Only numbers are accepted.");
				continue;
			}
			
			int index = products.indexOf(productToUpdate);
			productToUpdate.setQuantity(productToUpdate.getQuantity()+quantity);
			
			products.set(index, productToUpdate);
			
			System.out.println("Success! The stock was updated.");
			System.out.println("The new stock for "+productToUpdate.getProductName()+" is: "+productToUpdate.getQuantity());
			
			System.out.println("Do you want to update other product stock ? Yes/No");
			answer = r.returnString().toLowerCase();
			
		}while(answer.equals("yes"));
				
	}
	
	
	public void blackFriday(double discountPercent) {
			tempPrices = copyOldPrices();
			percentage = discountPercent;
			
		for(Product prod : products) {
			double discount = (prod.getProductPrice()*discountPercent) / 100;
			prod.setProductPrice(prod.getProductPrice() - discount);
		}
		
		System.out.println("Discount of "+discountPercent+"% has successfully applied.");
		System.out.println();
	}
	
	public void switchToNormal() {
		for(int i = 0;i < products.size();i++) {
			products.get(i).setProductPrice(tempPrices.get(i));
		}
	}
	
	public void displayBlackFriday() {
		for(int i = 0;i < products.size();i++) {
			displayBlackFridayProduct(products.get(i),tempPrices.get(i));
		}
		System.out.println();
	}
	
	
	public ArrayList<Double> copyOldPrices() {
	ArrayList<Double> tempProducts = new ArrayList<>();
	for(int i = 0; i < products.size();i++) {
		tempProducts.add(products.get(i).getProductPrice());
	}
	return tempProducts;
}
	
	//save into file
	public void saveAllChangesIntoFile() throws IOException {
		save.writeItemsIntoFile(products);
		save.addEarningsToFile(earnings);
	}
	
	
	
	//display All products
	public void displayAll() throws ClassNotFoundException, IOException {
		for (Product product : products) {
			displayProduct(product);
			System.out.println();
		}
	}

	//display the Earnings
	public void displayEarnings() throws ClassNotFoundException, IOException {
		System.out.println("The total sales of the store is: "+ df.format(earnings));
	}
	
	
	
	//display id's and names only
	public void displayNamesAndId(Product product) {
		System.out.println();
		System.out.println("PRODUCT ID:   " + product.getId());
		System.out.println("PRODUCT NAME: " + product.getProductName());
	}
	
	//display a product
	public void displayProduct(Product product) {
		System.out.println(" =--- CATEGORY : " + product.getProductType() + " ---=");
		System.out.println();
		System.out.println("PRODUCT ID:   " + product.getId());
		System.out.println("PRODUCT NAME: " + product.getProductName());
		System.out.println("QUANTITY:     " + product.getQuantity());
		System.out.println("PRICE:        " + df.format(product.getProductPrice()));
	}
	
	//display a product blackfriday product
	public void displayBlackFridayProduct(Product discountedproduct, double oldProductPrice) {
		System.out.println(" =--- BLACK FRIDAY "+percentage+"% OFF ---=");
		System.out.println();
		System.out.println("PRODUCT ID:   " + discountedproduct.getId());
		System.out.println("PRODUCT NAME: " + discountedproduct.getProductName());
		System.out.println("QUANTITY:     " + discountedproduct.getQuantity());
		System.out.println("PRICE:  " + df.format(discountedproduct.getProductPrice()) + " OLD PRICE: "+ df.format(oldProductPrice));
	}

	
	//messages
	private void welcomeMessage() {
		System.out.println("|--- Welcome to Dublin PC Parts ---|");
		addMessage();
	}

	private void addMessage() {
		System.out.println("Adding items option is active. Please proceed");
		System.out.println("Add as many items as you want or press 0 to stop.");
		System.out.println();
	}
}
