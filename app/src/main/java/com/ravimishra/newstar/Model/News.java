package com.ravimishra.newstar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class News {
@SerializedName("status")
    @Expose
    String status;


    @SerializedName("totalResult")
    @Expose
    String totalResult;


    @SerializedName("articles")
    @Expose
    List<Articles> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(String totalResult) {
        this.totalResult = totalResult;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }

    public News(String status, String totalResult, List<Articles> articles) {

        this.status = status;
        this.totalResult = totalResult;
        this.articles = articles;
    }
}
