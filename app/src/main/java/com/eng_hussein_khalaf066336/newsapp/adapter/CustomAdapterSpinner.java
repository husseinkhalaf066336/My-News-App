package com.eng_hussein_khalaf066336.newsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eng_hussein_khalaf066336.newsapp.R;

public class CustomAdapterSpinner extends BaseAdapter {
    Context context;
    int flags[];
    String[] countryNames;
    LayoutInflater inflter;

    public CustomAdapterSpinner(Context applicationContext, int[] flags, String[] countryNames) {
        this.context = applicationContext;
        this.flags = flags;
        this.countryNames = countryNames;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return flags.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner, null);
        ImageView icon = view.findViewById(R.id.custom_spinner_imageView);
        TextView names = view.findViewById(R.id.custom_spinner_textView);
        icon.setImageResource(flags[i]);
        names.setText(countryNames[i]);
        return view;
    }
}