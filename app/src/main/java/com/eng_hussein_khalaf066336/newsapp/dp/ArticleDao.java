package com.eng_hussein_khalaf066336.newsapp.dp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.eng_hussein_khalaf066336.newsapp.model.Articles;
import java.util.List;

@Dao
public interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdate(Articles article);

    @Query("SELECT * FROM articles")
    LiveData<List<Articles>> getAllArticles();

    @Delete
    void delete(Articles article);

}
