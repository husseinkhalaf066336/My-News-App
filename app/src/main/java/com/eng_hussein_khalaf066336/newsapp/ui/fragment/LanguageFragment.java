package com.eng_hussein_khalaf066336.newsapp.ui.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.eng_hussein_khalaf066336.newsapp.databinding.FragmentLanguageBinding;
import com.eng_hussein_khalaf066336.newsapp.ui.activity.SplashScreenActivity;

import java.util.Locale;

public class LanguageFragment extends Fragment {
    public static String Country="";
    private FragmentLanguageBinding binding;


    public LanguageFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLanguageBinding.inflate(inflater, container, false);
        binding.LanguageBtnAr.setOnClickListener(v -> {
            setLocale("ar");
        });
        binding.LanguageBtnEn.setOnClickListener(v -> {
            setLocale("en");

        });
        return binding.getRoot();
    }

    @SuppressWarnings("deprecation")
    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        Configuration conf = getResources().getConfiguration();
        conf.locale = myLocale;
        getResources().updateConfiguration(conf, dm);
        if(lang=="ar")
        {
            Country="ae";
        }
        else {
            Country="us";
        }
        // is opened "SplashScreenActivity" Until changes are made to the application as a whole
        //While the same activity is not reopened, the application language will not change unless it is reopened
        Intent intent = new Intent(getContext(), SplashScreenActivity.class);
        startActivity(intent);
        getActivity().onBackPressed();
    }
}
