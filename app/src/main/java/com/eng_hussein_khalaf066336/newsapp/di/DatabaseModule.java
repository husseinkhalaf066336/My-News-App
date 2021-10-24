package com.eng_hussein_khalaf066336.newsapp.di;

import android.app.Application;

import androidx.room.Room;

import com.eng_hussein_khalaf066336.newsapp.dp.ArticleDao;
import com.eng_hussein_khalaf066336.newsapp.dp.ArticleDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DatabaseModule {
    @Provides
    @Singleton
    public static ArticleDatabase articleDatabase(Application application)
    {
        return Room.databaseBuilder(application,ArticleDatabase.class,"fav_DB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }


    @Provides
    @Singleton
    public  static ArticleDao ProvideDao(ArticleDatabase articleDatabase)
    {
        return articleDatabase.articleDao();
    }
}
