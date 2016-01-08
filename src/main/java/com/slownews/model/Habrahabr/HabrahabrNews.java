package com.slownews.model.Habrahabr;

/**
 * Created by Влад on 02.01.2016.
 */
public class HabrahabrNews {
    private String title = "";
    private String description = "";
    private String link = "";


    public HabrahabrNews(String title, String description, String link) {
        this.title = title;
        this.description = description;
        this.link = link;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
