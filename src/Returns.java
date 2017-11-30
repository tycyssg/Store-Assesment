/**
 * @author Ciprian Tudose
 * Store Assesment
 */
import java.util.Scanner;

public class Returns {

	private Scanner key;
	
	public String returnString() {
		key = new Scanner(System.in);
		return key.nextLine();
	}
	
	public int returnInt() {
		key = new Scanner(System.in);
		return key.nextInt();
	}
	
	public double returnDouble() {
		key = new Scanner(System.in);
		return key.nextDouble();
	}
	
}
