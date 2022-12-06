import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.jdom.Element;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class RssFeedTest {

    @Test
    public void extractFeedFile() {
        try {
            URL feedSource = new URL("https://www.guesehat.com/feed");
            SyndFeedInput input = new SyndFeedInput();

            SyndFeed feed = input.build(new XmlReader(feedSource));

            assertNotNull("Entries from RSS = " + feed.getEntries(), feed);

        } catch (FeedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void extractElementFile() {
        try {
            URL feedSource = new URL("https://www.guesehat.com/feed");
            SyndFeedInput input = new SyndFeedInput();

            SyndFeed feed = input.build(new XmlReader(feedSource));

            List<SyndEntryImpl> entries = feed.getEntries();
            entries.forEach(entry -> {
                        List<Element> po = (List<Element>) entry.getForeignMarkup();
                        po.stream().forEach(p -> {
                            String imgURL = p.getAttribute("url").getValue();

                            System.out.println("URL for images = " + imgURL);
                        });

                    });
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
