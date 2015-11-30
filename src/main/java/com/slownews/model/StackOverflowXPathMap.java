package com.slownews.model;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by Влад on 27.11.2015.
 */
@XmlRootElement(name = "feed")
@XmlType(propOrder = {"id", "title", "author", "link"})
@XmlAccessorType(XmlAccessType.FIELD)

public class StackOverflowXPathMap {

    @XmlPath("entry/id/text()")
    private String id;

    @XmlPath("entry/title/text()")
    private String title;

    @XmlPath("entry/author/name/text()")
    private String author;

    @XmlPath("entry/link/text()")
    private String link;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }



   /* @XmlPath("entry/published/text()")
    private String published;

    @XmlPath("entry/summary/text()")
    private String summary;*/
}
