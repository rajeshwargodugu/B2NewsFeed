package com.b2.raj.b2newsfeed.base;

/**
 * Created by raj on 3/1/2017.
 */

public class FeedConfig {

    public static final String FEED_URL = "https://api.rss2json.com/v1/api.json?rss_url=http://" +
            "www.abc.net.au/news/feed/51120/rss.xml";

    private FeedConfig() {
        // Making constructor private so that  it can't be instantiated
    }
}

