package com.ravimishra.newstar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Articles {

    @SerializedName("source")
    @Expose
    Source source ;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @SerializedName("publishedAt")
    @Expose
    String date;

    @SerializedName("author")
    @Expose
    String author;

    @SerializedName("title")
    @Expose
    String title;

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("url")
    @Expose
    String url;


    @SerializedName("urlToImage")
    @Expose
    String urlToImage;
}
