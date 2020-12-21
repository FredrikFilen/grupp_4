package grupp4;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

//Encoder and decoder
public class XML {
	public static void encode(ArrayList<Skier> skierList) {

		XMLEncoder encoder = null;

		try {
			encoder = new XMLEncoder(new FileOutputStream((new File("./src/grupp4/Assets/skiers.xml"))));
		} catch (FileNotFoundException fileNotFound) {
			System.out.println("Error: while creating or openeing the file skiers.xml");
		}

		encoder.writeObject(skierList);
		System.out.println("Write sucessful");

		encoder.close();

	}

	public static ArrayList<Skier> decode() {

		ArrayList<Skier> skierList = new ArrayList<Skier>();
		XMLDecoder decoder = null;

		try {
			decoder = new XMLDecoder(new FileInputStream((new File("./src/grupp4/Assets/skiers.xml"))));
			System.out.println("File stream opened and XMLDecoder created");

			skierList = (ArrayList<Skier>) decoder.readObject();

			System.out.println("Read successful from skiers.xml");

		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File skiers.xml not found");

		} finally {
			if (decoder != null) {
				decoder.close();
				System.out.println("decoder closed");

			}

		}

		return skierList;
	}
}
