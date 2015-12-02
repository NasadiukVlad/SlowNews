package com.slownews.model;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 30.11.2015.
 */

@XmlRootElement(name = "rss")
@XmlType(propOrder = {"title", "link", "description" , "titles", "descriptions", "links"})
@XmlAccessorType(XmlAccessType.FIELD)

public class BBCXpathMap {


    @XmlPath("channel/item/title/text()")
    private String title;

    @XmlPath("channel/item/title/text()")
    List titles = new ArrayList<String>();

    @XmlPath("channel/item/link/text()")
    private String link;

    @XmlPath("channel/item/link/text()")
    List links = new ArrayList<String>();

    @XmlPath("channel/item/description/text()")
    private String description;

    @XmlPath("channel/item/description/text()")
    List descriptions = new ArrayList<String>();


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List getTitles() {
        return titles;
    }

    public void setTitles(List titles) {
        this.titles = titles;
    }

    public List getLinks() {
        return links;
    }

    public void setLinks(List links) {
        this.links = links;
    }

    public List getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List descriptions) {
        this.descriptions = descriptions;
    }



}
