package data;

import java.util.HashMap;

//En hjälpklass som tillhandahåller url-länkar
public class UrlProvider {
    private static HashMap<String, String> urls;

    //Här kan fler länkar läggas till i HashMap
    private static void populateHashMapWithUrls() {
        urls = new HashMap<>();
        urls.put("register", "C:\\Users\\Admin\\Downloads\\Register\\Register.html");
    }

    public static String getUrl(String page) {
        //Initialisera HashMap med url-länkar
        populateHashMapWithUrls();

        //Hämta nyckelord från testdata med felhantering
        String key = page.toLowerCase();
        String url = urls.get(key);

        //Om nyckel inte finns kastas en exception med beskrivande meddelande
        if (url == null) {
            throw new IllegalArgumentException("Sidan" + page + " finns inte definierad i din UrlProvider. " +
                    "\n Kontrollera att du stavat rätt i feature-fil");
        }

        return url;

    }
}
