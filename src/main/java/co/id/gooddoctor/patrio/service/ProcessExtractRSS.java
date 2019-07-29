package co.id.gooddoctor.patrio.service;

import co.id.gooddoctor.patrio.dao.ContentDao;
import co.id.gooddoctor.patrio.entity.Content;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
public class ProcessExtractRSS {

    @Autowired
    private ContentDao contentDao;

    @Value("${link.rss}")
    private String linkRSS;

    public String ExtractRSS() {
        try {
            URL feedSource = new URL(linkRSS);
            SyndFeedInput input = new SyndFeedInput();

            SyndFeed feed = input.build(new XmlReader(feedSource));

            feed.getEntries()
                    .stream()
                    .forEach(entries -> {
                        SyndEntryImpl entry = (SyndEntryImpl) entries;

                        //mapping to object Content
                        Content content = mappingToContent(entry);

                        //save to db
                        contentDao.save(content);
                    });
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    private Content mappingToContent(SyndEntryImpl entry) {
        Content content = new Content();
        content.setAuthor(entry.getAuthor());
        content.setLink(entry.getLink());
        content.setDesc1(entry.getDescription().getValue());
        content.setPublishDate(entry.getPublishedDate());
        content.setTitle(entry.getTitle());

        entry.getCategories()
                .stream()
                .forEach(cat -> {
                    SyndCategoryImpl catImpl = (SyndCategoryImpl) cat;
                    content.setCategory(catImpl.getName());
                });


        entry.getContents()
                .stream()
                .forEach(c -> {
                    SyndContentImpl contentImpl = (SyndContentImpl) c;
                    content.setContents(contentImpl.getValue());
                });

        return content;
    }

}
