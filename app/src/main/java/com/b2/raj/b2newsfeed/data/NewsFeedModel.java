package com.b2.raj.b2newsfeed.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;

/**
 * Created by raj on 3/1/2017.
 * Main model class for JSon Response. It has the complete JSON feed Structure
 */

public class NewsFeedModel {

    private static NewsFeedModel sModel;

    private NewsFeedModel() {

    }

    public static NewsFeedModel getInstance() {
        if (sModel == null) {
            sModel = new NewsFeedModel();
        }
        return sModel;
    }

    @Expose
    private String status;

    @Expose
    private FeedFeed feed;


    @Expose
    private ArrayList<FeedItems> items;

    public FeedFeed getFeed() {
        return feed;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<FeedItems> getItems() {
        return items;
    }

    public void updateModel(String jsonData) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .create();
        sModel = gson.fromJson(jsonData, NewsFeedModel.class);
    }

}


