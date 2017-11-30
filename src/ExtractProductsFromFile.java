/**
 * @author Ciprian Tudose
 * Store Assesment
 */
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ExtractProductsFromFile {

	private FileInputStream fi;
	private ObjectInputStream oi;


	public void readFileAndCreateStreams() throws IOException, EOFException {

		File f = new File("products.txt");
		if (f.exists()) {

			try {
				fi = new FileInputStream(f);
				oi = new ObjectInputStream(fi);
			} catch (EOFException e) {

			}

		} else {
			f.createNewFile();

		}

	}
	
	public void readEarnings() throws IOException, EOFException {

		File f = new File("earnings.txt");
		if (f.exists()) {

			try {
				fi = new FileInputStream(f);
				oi = new ObjectInputStream(fi);
			} catch (EOFException e) {

			}

		} else {
			f.createNewFile();

		}

	}

	public ArrayList<Product> extractTheProducts() throws IOException, ClassNotFoundException {
		readFileAndCreateStreams();
		ArrayList<Product> products = new ArrayList<>();

		try {
			products = (ArrayList<Product>) oi.readObject();
		} catch (NullPointerException e) {

		}
		oi.close();
		fi.close();
		return products;
	}
	
	public double extractEarnings() throws IOException, ClassNotFoundException {
		readEarnings();
		double earnings = 0;

		try {
			earnings = (double) oi.readObject();
		} catch (NullPointerException e) {
			
		}
		oi.close();
		fi.close();
		return earnings;
	}

}// end class
