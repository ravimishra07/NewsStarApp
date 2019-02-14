package com.ravimishra.newstar.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelNews {
    @SerializedName("title")
    @Expose
    String titlee;

    @SerializedName("description")
    @Expose
    String desc;

    @SerializedName("urlToImage")
    @Expose
    String url;

    public ModelNews(String titlee, String desc, String url) {
        this.titlee = titlee;
        this.desc = desc;
        this.url = url;
    }

    public String getTitlee() {
        return titlee;
    }

    public void setTitlee(String titlee) {
        this.titlee = titlee;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
