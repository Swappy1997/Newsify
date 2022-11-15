package com.example.newsify.models;

import java.io.Serializable;
import java.util.List;

public class NewsResponce implements Serializable {
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalresult() {
        return totalresult;
    }

    public void setTotalresult(int totalresult) {
        this.totalresult = totalresult;
    }

    public List<NewsSource> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsSource> articles) {
        this.articles = articles;
    }

    String status;
    int totalresult;
    List<NewsSource>articles;
}
