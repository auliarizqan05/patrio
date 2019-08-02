package co.id.gooddoctor.patrio.service;

import co.id.gooddoctor.patrio.dao.ContentDao;
import co.id.gooddoctor.patrio.entity.Content;
import com.sun.syndication.feed.synd.*;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.jdom.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcessExtractRSS {

    @Autowired
    private ContentDao contentDao;

    @Value("${link.rss}")
    private String linkRSS;

    public List<Content> extractRSS() {
        List<Content> listContent = new ArrayList<>();
        try {
            URL feedSource = new URL(linkRSS);
            SyndFeedInput input = new SyndFeedInput();

            SyndFeed feed = input.build(new XmlReader(feedSource));

            listContent = (List<Content>) feed.getEntries()
                    .stream()
                    .map(entries -> {
                        SyndEntryImpl entry = (SyndEntryImpl) entries;

                        //mapping to object Content
                        Content content = mappingToContent(entry);

                        //save to db
//                        contentDao.save(content);

                        return content;
                    }).collect(Collectors.toList());
        } catch (FeedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listContent;
    }

    public List<Content> retrieveData(){
        List<Content> listContent = contentDao.findAll();

        return listContent;
    }

    private Content mappingToContent(SyndEntryImpl entry) {
        Content content = new Content();
        content.setAuthor(entry.getAuthor());
        content.setLink(entry.getLink());
        content.setDesc1(entry.getDescription().getValue());
        content.setPublishDate(entry.getPublishedDate());
        content.setTitle(entry.getTitle());

        List<Element> foreignMarkup = (List<Element>) entry.getForeignMarkup();
        foreignMarkup
                .stream()
                .forEach(img -> {
                    String imgURL = img.getAttribute("url").getValue();
                    content.setImgThumbnail(imgURL);
                });

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
                    SyndContent o = (SyndContent) contentImpl.getInterface().cast(contentImpl);
                    content.setContents(contentImpl.getValue());
                });

        return content;
    }

}
