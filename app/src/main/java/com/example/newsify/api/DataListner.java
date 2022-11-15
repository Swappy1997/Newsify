package com.example.newsify.api;

import com.example.newsify.models.NewsSource;

import java.util.List;

public interface DataListner<NewsResponce>{
    void onDataFetch(List<NewsSource> list, String message);
    void onError(String message);
}
