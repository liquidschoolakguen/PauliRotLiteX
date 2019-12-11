package com.example.android.rssfeedlibary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RssFeedProvider {
    public static RssItem greet(String rssFeed) {


        return new RssItem("Hallo ", rssFeed);
    }
}