/**
 * @author Ciprian Tudose
 * Store Assesment
 */
import java.io.IOException;
import java.util.InputMismatchException;

public class Runner {
	private StoreActions actions = new StoreActions();
	private Returns r = new Returns();
	private static boolean allowNormalDisplay = true;
	private static boolean allowBFDisplay = false;
	
	public void run() throws ClassNotFoundException, IOException {
		actions.saveItems();
		menu();
	}
	
	
	public void menu() throws ClassNotFoundException, IOException {
	int option = 0;	
		
	do {
		System.out.println("Press 1 to display all products");
		System.out.println("Press 2 to sell a product");
		System.out.println("Press 3 to display the shop earnings");
		System.out.println("Press 4 to add a new product");
		System.out.println("Press 5 to add new stock for a product");
		System.out.println("Press 6 for Black Friday.");
		System.out.println("Press 7 to exit");
		
		try {
			option = r.returnInt();
		}catch(InputMismatchException e) {
			System.out.println("Invalid input! Only number accepted.");
			continue;
		}
		
		if(option == 1) {
			if(allowNormalDisplay) {
			actions.displayAll();
			}else {
				System.out.println("Today is Black Friday day.");
				System.out.println("Please use the Black Friday option to see the items");
			}
		}else if(option == 2) {
			actions.sell();
		}else if(option == 3) {
			actions.displayEarnings();
		}else if(option == 4) {
			actions.saveAnItem();
		}else if(option == 5) {
			actions.updateStocks();
		}else if(option == 6) {
			blackFriday();
		}else if(option == 7) {
			actions.saveAllChangesIntoFile();
			break;
		}else {
			System.out.println("ERROR ! The choosen option doesn't exist.");
			System.out.println();
		}
	}while(true);
	
		System.out.println("End of session");
		System.out.println("Thank you for using Dublin PC Parts");
	}
	
	
	
	
	public void blackFriday() {
		System.out.println("=---BLACK FRIDAY SYSTEM---=");
		System.out.println();
		System.out.println("Press 1 to set the discounts for products");
		System.out.println("Press 2 to see the new prices.");
		System.out.println("Press 3 to end the Black Friday discounts.");
		System.out.println();
		int option = r.returnInt();
		
		if(option == 1) {
			System.out.println("Please input the percentage for the discounts.");
			double discountPercent = 0;
			
			try {
				discountPercent = r.returnDouble();
			}catch(InputMismatchException e) {
				System.out.println("Invalid input! Only number accepted.");
			}
			
			actions.blackFriday(discountPercent);
			allowNormalDisplay = false;
			allowBFDisplay = true;
			
		}else if(option == 2) {
			
			if(allowBFDisplay) {
				actions.displayBlackFriday();
			}else {
				System.out.println("Is not Black Friday yet!");
			}
		}else if(option == 3) {
			actions.switchToNormal();
			allowNormalDisplay = true;
			allowBFDisplay = false;
			System.out.println("Black Friday ended...");
		}
	}
	
	
}//end class
