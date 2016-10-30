package com.errorstation.wallpaper.database;

import io.realm.RealmObject;

/**
 * Created by Rubayet on 30-Oct-16.
 */

public class WallpaperModel extends RealmObject {


    private String picid;
    private String thumb;
    private String picurl;
    private String source;
    private String rating;
    private String title;
    private String description;
    private String category;
    private String views;
    private String downloads;
    private String liked;

    public void setPicid(String picid) {
        this.picid = picid;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public void setDownloads(String downloads) {
        this.downloads = downloads;
    }

    public void setLiked(String liked) {
        this.liked = liked;
    }

    public String getPicid() {

        return picid;
    }

    public String getThumb() {
        return thumb;
    }

    public String getPicurl() {
        return picurl;
    }

    public String getSource() {
        return source;
    }

    public String getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getViews() {
        return views;
    }

    public String getDownloads() {
        return downloads;
    }

    public String getLiked() {
        return liked;
    }
}
