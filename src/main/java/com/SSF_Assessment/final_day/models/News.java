package com.SSF_Assessment.final_day.models;

import jakarta.json.JsonObject;

public class News {

    private String id; // long
    private Integer published_on; // long
    private String title;
    private String url;
    private String imageurl;
    private String body;
    private String tags;
    private String categories; // Trading|BTC|Market

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPublished_on() {
        return published_on;
    }

    public void setPublished_on(Integer published_on) {
        this.published_on = published_on;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public static News create(JsonObject jo) {
        News w = new News();
        w.setId(jo.getString("id"));
        w.setPublished_on(jo.getInt("published_on"));
        w.setTitle(jo.getString("title"));
        w.setUrl(jo.getString("url"));
        w.setImageurl(jo.getString("imageurl"));
        w.setBody(jo.getString("body"));
        w.setTags(jo.getString("tags"));
        w.setCategories(jo.getString("categories"));
        return w;
    }
}