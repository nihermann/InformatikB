package Network.FromLecture.url1;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Demonstrating the URL object
 */
public class URLTest {

    public static void main(String[] args) {
        URL url;
        try {
            // NO connection yet!
//            url = new URL("http://www.inf.uos.de/index.php");
            url = new URL("http://de.selfhtml.org/html/referenz/elemente.htm#inline_elemente");
//            url = new URL("http", "www.google.de", 80, "index.html");
//            url = new URL("http://www.google.de/search?source=ig&hl=de&q=java&btnG=Google-Suche&meta=");
//            url = new URL("INFOB://www.inf.uos.de/index.php");
            System.out.println("Protocol        : " + url.getProtocol() ); 
            System.out.println("Hostname        : " + url.getHost() ); 
            System.out.println("Port            : " + url.getPort() );
            System.out.println("Filename/Query : " + url.getFile() ); 
            System.out.println("Path            : " + url.getPath() ); 
            System.out.println("Query-String    : " + url.getQuery() ); 
            System.out.println("Anchor           : " + url.getRef() );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } 
        
    }
}
