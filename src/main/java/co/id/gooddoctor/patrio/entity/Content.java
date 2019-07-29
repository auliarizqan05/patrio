package co.id.gooddoctor.patrio.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Data
@Table
@Entity
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String author;
    String desc1;
    String link;
    @Type(type = "text")
    String contents;
    Date publishDate;
    String category;

    Date createdDate;
    Date lastDate;


    @PrePersist
    public void preSave() {
        createdDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        lastDate = new Date();
    }

}
