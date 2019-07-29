import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.URL;

public class Test {

    @org.junit.Test
    public void extractFeedFile(){
         try {
            URL feedSource = new URL("https://www.guesehat.com/feed");
            SyndFeedInput input = new SyndFeedInput();

            SyndFeed feed = input.build(new XmlReader(feedSource));
            System.out.println("Entries from RSS = " + feed);
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
