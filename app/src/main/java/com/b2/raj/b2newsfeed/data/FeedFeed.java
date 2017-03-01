package com.b2.raj.b2newsfeed.data;

import com.google.gson.annotations.Expose;

/**
 * Created by raj on 3/1/2017.
 * This class is not needed at present but good to have.
 */

public class FeedFeed {

    @Expose
    private String url;

    @Expose
    private String title;

    @Expose
    private String link;

    @Expose
    private String author;

    @Expose
    private String description;

    @Expose
    private String image;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) { this.image = image; }

}