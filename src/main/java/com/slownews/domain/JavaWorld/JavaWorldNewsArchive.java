package com.slownews.domain.JavaWorld;

import javax.persistence.*;

/**
 * Created by Влад on 06.01.2016.
 */
@Entity
public class JavaWorldNewsArchive {
    @Id
    // @SequenceGenerator(initialValue=1, allocationSize=1, name="users_seq", sequenceName="users_seq")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "news_id")
    private long newsId;

    @Column(name = "title")
    private String title;

    @Column(name = "link")
    private String link;

    @Column(name = "description", columnDefinition = "varchar(1000)")
    private String description;

    public JavaWorldNewsArchive(String title, String description, String link) {
        this.title = title;
        this.description = description;
        this.link = link;

    }

    public JavaWorldNewsArchive() {

    }

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
