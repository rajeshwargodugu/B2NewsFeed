package com.b2.raj.b2newsfeed.data;

import com.google.gson.annotations.Expose;

/**
 * Created by raj on 3/1/2017.
 */

public class Enclosure {

    @Expose
    private String link;

    @Expose
    private String type;

    @Expose
    private String thumbnail;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
