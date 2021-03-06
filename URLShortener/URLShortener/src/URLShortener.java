import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class URLShortener implements Runnable{

	private int Time;
	private static HashMap<String, URLShortener> associations = new HashMap<String, URLShortener>();
	private String longURL, shortURL;

	public URLShortener(String longURL, String shortURL) {
		this.Time = 3;
		this.longURL = longURL;
		this.shortURL = shortURL;
	}

	public URLShortener() {
	}

	@Override
	 public void run() {
		ArrayList<String> obseleteURLS = new ArrayList<String>();
    	for (Map.Entry mapElement : associations.entrySet()) { 
			URLShortener URL = (URLShortener)mapElement.getValue();
			URL.Time--;
			System.out.println("Decrementing " + URL.shortURL + " Time : " + URL.Time);
			if(URL.Time == 0) {
				obseleteURLS.add(URL.shortURL);
			}
    	}
    	for(String url : obseleteURLS) {
			System.out.println("Removed LongURL : " + associations.get(url).longURL + " shortURL : " + url  + " as URLExpired");
    		associations.remove(url);
    	}
    }
	
	public void start(){
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        Runnable task = new URLShortener();
        int initialDelay = 0;
        int periodicDelay = 1;
        scheduler.scheduleAtFixedRate(task, initialDelay, periodicDelay,TimeUnit.SECONDS);
	}

	private String addShortUrl(String longURL, String baseString ) {
		String webName = "www.lilURL.com/";
		associations.put(webName + baseString, new URLShortener(longURL, webName + baseString));
		System.out.println("Added Shorter URL " + webName + baseString + " for longer URL " + longURL);
		return baseString;
	}

	public String incrementString(String baseString) {
		for(int i = baseString.length()-1; i >= 0; i--) {
			char temp = baseString.charAt(i) ;
			if(temp == '9') {
				if( i== 0) {
					baseString += 'a';
					break;
				}
				else continue;
			}
			if(temp >= 'A' && temp <= 'Z' || temp >= 'a' && temp <= 'y' || temp >= '0' && temp <= '8') {
				temp += 1;
				baseString = baseString.substring(0, i) + temp;
				break;
			}
			else {
				if(temp == 'X') temp = '0';
				else if (temp == 'x') temp = 'X';
				break;
			}
		}
		return baseString;
	}



	private void printListedURLS() {
		for (Map.Entry mapElement : associations.entrySet()) { 
			String key = (String)mapElement.getKey(); 
			URLShortener URL = (URLShortener)mapElement.getValue(); 
			String value = URL.longURL;
			System.out.println("LongURL : " + key + " shortURL : " + value );
		}
	}

	public static void main(String[] args) {
		String baseString = "a";
		new URLShortener().addShortUrl("www.youtube.com/sgergeERGEwegwegERG", baseString);
		baseString = new URLShortener().incrementString(baseString) ;
		new URLShortener().addShortUrl("www.youtube.com/qwdqqwdQWQWwwqwfweg", baseString);
		baseString = new URLShortener().incrementString(baseString) ;
		new URLShortener().addShortUrl("www.youtube.com/ERGewreergggeRGEggq", baseString);
		baseString = new URLShortener().incrementString(baseString) ; 
		new URLShortener().addShortUrl("www.youtube.com/qWQQQQQQdqwdqeffwef", baseString);
		//new URLShortener().printListedURLS();
		
		URLShortener x = new URLShortener();
		x.start();
	}

	
}
