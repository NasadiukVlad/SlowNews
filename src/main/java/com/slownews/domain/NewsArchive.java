package com.slownews.domain;

import javax.persistence.*;

/**
 * Created by ���� on 21.12.2015.
 */

@Entity
public class NewsArchive {
    @Id
    // @SequenceGenerator(initialValue=1, allocationSize=1, name="users_seq", sequenceName="users_seq")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "news_id")
    private long newsId;

    @Column(name = "title")
    private String title;

    @Column(name = "link")
    private String link;

    @Column(name = "description")
    private String description;

    public long getNewsId() {
        return newsId;
    }

    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }

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

}
