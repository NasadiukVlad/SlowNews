package com.slownews.model.BBC;

/**
 * Created by Влад on 01.12.2015.
 */
public class BBCNews {

    private String title = "";
    private String description = "";
    private String link = "";

    public BBCNews(String title, String description, String link) {
        this.title = title;
        this.description = description;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
