package com.eng_hussein_khalaf066336.newsapp.dp;

import androidx.room.TypeConverter;

import com.eng_hussein_khalaf066336.newsapp.model.Source;

public class Converters {
    @TypeConverter
  public   String fromSource(Source source)
    {
        return source.getName();
    }
    @TypeConverter
    public Source toSource(String name)
    {
        return new Source(name,name);
    }
}
