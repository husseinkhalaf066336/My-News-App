package com.eng_hussein_khalaf066336.newsapp.api;

import com.eng_hussein_khalaf066336.newsapp.model.News;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("top-headlines")
    Observable<News> getNews(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );
    @GET("everything")
    Observable<News> getSearchNews(
            @Query("q") String keyword,
            @Query("language") String language,
            @Query("sortBy") String sortBy,
            @Query("apiKey") String apiKey
    );
}
