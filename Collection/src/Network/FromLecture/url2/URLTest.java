package Network.FromLecture.url2;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Read a web page with an URL-object
 */
public class URLTest {

    public static void main(String[] args) {
        URL url;
        try {
//            url = new URL("http://www.inf.uos.de/index.php");
            url = new URL("ftp://ftp.heise.de/pub/ct/README.ct");
            
            
            // InputStream in = url.openConnection().getInputStream(); // alternative
            InputStream in = url.openStream();
            
            int len = 0;
            byte[] b = new byte[1024];
            while((len = in.read(b))!=-1) {
                System.out.write(b,0,len);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}
