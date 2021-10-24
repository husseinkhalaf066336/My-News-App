package com.eng_hussein_khalaf066336.newsapp.dp;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.eng_hussein_khalaf066336.newsapp.model.Articles;

@Database(entities = Articles.class,version = 1,exportSchema = false)
@TypeConverters(Converters.class)
public abstract class ArticleDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();
}
