package com.b2.raj.b2newsfeed.data;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

/**
 * Created by raj on 3/1/2017.
 */

public class FeedItems {

    @Expose
    private String title;

    @Expose
    private String pubDate;

    @Expose
    private String link;

    @Expose
    private String guid;

    @Expose
    private String author;

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Expose
    private String thumbnail;

    @Expose
    private String description;

    @Expose
    private String content;

    public String getTitle() {
        return title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getLink() {
        return link;
    }

    public String getGuid() {
        return guid;
    }

    public String getAuthor() {
        return author;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    @Expose
    private Enclosure enclosure;

    public Enclosure getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }


    // Not needed at the moment but good have this for future
    @Expose
    private ArrayList<String> categories;

    public ArrayList<String> getCategories() {
        return categories;
    }
}
