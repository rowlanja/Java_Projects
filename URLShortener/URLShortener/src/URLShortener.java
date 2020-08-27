import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;

public class URLShortener extends Thread{

	private int Time;
	private HashMap<String, URLShortener> associations = new HashMap<String, URLShortener>();
	private String longURL, shortURL;

	public URLShortener(String longURL, String shortURL) {
		this.Time = 10;
		this.longURL = longURL;
		this.shortURL = shortURL;
	}

	public URLShortener() {
	}

	public void run() {
		try {
			boolean finished = false;
			Iterator hmIterator = associations.entrySet().iterator(); 
			while (hmIterator.hasNext() && !finished) { 
				Map.Entry mapElement = (Map.Entry)hmIterator.next(); 
				System.out.println(mapElement.getKey()); 
			} 
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private String addShortUrl(String longURL, String baseString ) {
		System.out.println(baseString);
		String x =  baseString.substring(baseString.length()-2, baseString.length()-1);
		x = Character.toString(x.charAt(0) + 1);
		System.out.println(x);
		baseString = baseString.substring(0, baseString.length()-1) + x;
		associations.put(baseString, new URLShortener(longURL, baseString));
		System.out.println(associations.get(baseString).shortURL );
		return baseString;
	}

	private String getLongURL(String ShortURL) {
		return this.longURL;
	}

	private String getShortURL(String ShortURL) {
		return this.longURL;
	}

	public void incrementString(String baseString) {
		for(int i = baseString.length()-1; i >= 0; i--) {
			char temp = baseString.charAt(i) 
			if (temp >= 'A' && temp <= 'Z')  
			else if (temp >= 'a' && temp <= 'z')  
			else if ()
						System.out.println("\n" + ch +  
								" is not an aplhabetic character" );  
		}
	}

	public static void main(String[] args) {
		String baseString = "aaaaa";
		baseString = new URLShortener().addShortUrl("www.youtube.com/aaaabbbbbccccdddd", baseString) ;
		baseString = new URLShortener().addShortUrl("www.youtube.com/aaaabbbbbcccceeee", baseString) ;
		baseString = new URLShortener().addShortUrl("www.youtube.com/aaaabbbbbccccffff", baseString) ;

		Thread x = new Thread();
		x.start();
	}
}
