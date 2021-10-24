package com.eng_hussein_khalaf066336.newsapp.repository;

import androidx.lifecycle.LiveData;

import com.eng_hussein_khalaf066336.newsapp.api.ApiInterface;
import com.eng_hussein_khalaf066336.newsapp.dp.ArticleDao;
import com.eng_hussein_khalaf066336.newsapp.model.Articles;
import com.eng_hussein_khalaf066336.newsapp.model.News;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class NewsRepository {
      private ApiInterface apiInterface;
      private ArticleDao articleDao;
    @Inject
    public NewsRepository(ApiInterface apiInterface, ArticleDao articleDao )
    {
        this.apiInterface=apiInterface;
        this.articleDao=articleDao;
    }
    public Observable<News> getNews(String Country , String ApiKey)
    {
        return apiInterface.getNews(Country,ApiKey);
    }
    public Observable<News> getSearchNews(String keyword,String language, String sortBy,String apiKey)
    {
        return apiInterface.getSearchNews(keyword,language,sortBy,apiKey);
    }


    public void insertOrUpdate (Articles articles)
    {
        articleDao.insertOrUpdate(articles);
    }
    public void delete (Articles articles)
    {
        articleDao.delete(articles);
    }

    public LiveData<List<Articles>> getAllArticles()
    {
        return articleDao.getAllArticles();
    }


}
