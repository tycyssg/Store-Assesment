/**
 * @author Ciprian Tudose
 * Store Assesment
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;


public class SaveProductsInFile {

	
	private FileOutputStream fo;
	private ObjectOutputStream o;

	Returns r = new Returns();
	

	public void addItems() {
		ArrayList<Product> products = StoreActions.products;
		
		double productPrice = 0;
		int quantity = 0;
		
		do {
			System.out.println("Input the product type (processor,memory,HDD,etc.)");
			String productType = r.returnString();
			System.out.println("Input the product name (Intel I9-7600K)");
			String productName = r.returnString();
			try {
			System.out.println("Input the product price in €");
			productPrice = r.returnDouble();
			System.out.println("Input the product quantity");
			quantity = r.returnInt();
			}catch(InputMismatchException e) {
				System.out.println("ERROR ! Only numbers are allowed!");
				System.out.println("Please try again!");
				System.out.println();
				continue;
			}
			
			int size = (products.isEmpty()) ? 1 : products.size()+1;
			
			Product p = new Product(size,productType,productName,productPrice,quantity);
			products.add(p);
			System.out.println("Product successfully added into stock.");
			System.out.println();
			System.out.println("Press 0 to stop or enter to continue");
			System.out.println();

		}while(!r.returnString().equals("0"));
	}
	
	
	public void addEarningsToFile(double earnings) throws IOException {
		createEarningsFile();
		o.writeObject(earnings);
		System.out.println("Payment information successfully saved into the file");
	}
	
	
	public void writeItemsIntoFile(ArrayList<Product> products) throws IOException {
			createFileAndStreams();
			o.writeObject(products);
			System.out.println("Products successfully saved into the file");
			
		o.close();
		fo.close();
	}
	
	
	public void createFileAndStreams() throws IOException {
		fo = new FileOutputStream(new File("products.txt"));
	    o = new ObjectOutputStream(fo);
	}
	
	
	public void createEarningsFile() throws IOException {
		fo = new FileOutputStream(new File("earnings.txt"));
	    o = new ObjectOutputStream(fo);
	}	

}
	

