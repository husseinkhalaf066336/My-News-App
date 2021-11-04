package com.eng_hussein_khalaf066336.newsapp.viewModels;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eng_hussein_khalaf066336.newsapp.model.Articles;
import com.eng_hussein_khalaf066336.newsapp.model.News;
import com.eng_hussein_khalaf066336.newsapp.repository.NewsRepository;

import java.util.List;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ViewModelNews extends ViewModel {

    private NewsRepository newsRepository;
    private MutableLiveData<News> mutableLiveDataNews= new MutableLiveData<>();
    private MutableLiveData<News> mutableLiveDataSearchNews= new MutableLiveData<>();
    private static final String TAG = "ViewModelNews";

    @ViewModelInject
    public ViewModelNews (NewsRepository newsRepository)
    {
        this.newsRepository = newsRepository;
    }
    public LiveData<News> getNews(String Country ,String Category, String ApiKey)
    {
        newsRepository.getNews(Country,Category,ApiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<News>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull News news) {
                        mutableLiveDataNews.setValue(news);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return mutableLiveDataNews;
    }

    public LiveData<News> getSearchNews(String keyword,String language, String sortBy,String apiKey)
    {
        newsRepository.getSearchNews(keyword,language,sortBy,apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<News>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull News news) {
                        mutableLiveDataSearchNews.setValue(news);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return mutableLiveDataSearchNews;
    }

    public void insertOrUpdate (Articles articles)
    {
        newsRepository.insertOrUpdate(articles);
    }
    public void delete (Articles articles)
    {
        newsRepository.delete(articles);
    }

    public LiveData<List<Articles>> getAllArticles()
    {
        return newsRepository.getAllArticles();
    }

}
