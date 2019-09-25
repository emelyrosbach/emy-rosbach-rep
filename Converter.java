import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import acm.util.RandomGenerator;

public class Converter {
	// variables
	private HashMap<String, String> urls;
	private RandomGenerator rgen = new RandomGenerator();

	// constructor
	public Converter() {
		readUrls();
	}

	// methods
	public String getLongURL(String sh) {
		String lng = "";
		for (String key : urls.keySet()) {
			if (urls.get(key).equals(sh)) {
				lng = key;
			}
		}
		return lng;
	}

	public String getShortURL(String lng) {
		return urls.get(lng);
	}

	public void createShort(String url) {

		if (isValidURL(url) && !urls.containsKey(url)) {
			String shrt = createURL();
			urls.put(url, shrt);
			writeUrls();
		}
	}

	public void writeUrls() {
		try {
			FileOutputStream fos = new FileOutputStream("map.ser");
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(urls);
			fos.close();
		} catch (Exception e) {
			System.out.println("Error");

		}
	}

	public void readUrls() {
		try {
			FileInputStream fis = new FileInputStream("map.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object obj = ois.readObject();
			urls = (HashMap<String,String>) obj;
			ois.close();
			fis.close();
		} catch (Exception e) {
			urls = new HashMap<String, String>();
		}

	}

	private String createURL() {
		String s ="https://sho.rt/";
		while (s.length() < 6) {
			int i = rgen.nextInt() % 3;
			switch (i) {
			case 0:
				s = s + (char) rgen.nextInt(48, 57);
				break;
			case 1:
				s = s + (char) rgen.nextInt(65, 90);
				break;
			case 2:
				s = s + (char) rgen.nextInt(97, 122);
				break;
			default:
				break;
			}
		}
		return s;
	}

	private boolean isValidURL(String s) {
		try {
			if (s.substring(0, 7).equals("http://") || s.substring(0, 8).equals("https://")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception exception) {
			return false;
		}
	}

}
