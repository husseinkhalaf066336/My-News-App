package com.eng_hussein_khalaf066336.newsapp.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;


import com.eng_hussein_khalaf066336.newsapp.R;
import com.eng_hussein_khalaf066336.newsapp.adapter.CustomAdapterSpinner;
import com.eng_hussein_khalaf066336.newsapp.databinding.FragmentLanguageBinding;
import com.eng_hussein_khalaf066336.newsapp.ui.activity.SplashScreenActivity;

import java.util.Locale;

public class LanguageFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    public static String Country="default";
    private FragmentLanguageBinding binding;
    String[] countryNames;
    int flags[] = { R.drawable.ic_add, R.drawable.ic_language,R.drawable.ic_uae,
                    R.drawable.ic_egypt, R.drawable.america,R.drawable.uk_flags,
                    R.drawable.ic_france, R.drawable.germany,R.drawable.italy,
                    R.drawable.turkey, R.drawable.india, R.drawable.china,
                    R.drawable.japan,R.drawable.ic_russia};
    public LanguageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        countryNames=context.getResources().getStringArray(R.array.Spinner_languages_array);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLanguageBinding.inflate(inflater, container, false);
        binding.languagesSpinner.setOnItemSelectedListener(this);

        CustomAdapterSpinner customAdapter=new CustomAdapterSpinner(getContext(),flags,countryNames);
        binding.languagesSpinner.setAdapter(customAdapter);
        return binding.getRoot();
    }

    @SuppressWarnings("deprecation")
    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        Configuration conf = getResources().getConfiguration();
        conf.locale = myLocale;
        getResources().updateConfiguration(conf, dm);
        applyChangesApp();
    }

    private void applyChangesApp() {
        //go to "SplashScreenActivity" apply  changes  to the application
        Intent intent = new Intent(getContext(), SplashScreenActivity.class);
        startActivity(intent);
        getActivity().onBackPressed();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0://do nothing
                        break;
                    case 1:
                        Country="default";
                        applyChangesApp();
                        break;
                    case 2:
                        Country="ae";
                        setLocale("ar");
                        break;
                    case 3:
                        Country="eg";
                        setLocale("ar");
                        break;
                    case 4:
                        Country="us";
                        setLocale("en");
                        break;
                    case 5:
                        Country="gb";
                        setLocale("en");
                        break;
                    case 6:
                        Country="fr";
                        setLocale("en");
                        break;
                    case 7:
                        Country="de";
                        setLocale("en");
                        break;
                    case 8:
                        Country="it";
                        setLocale("en");
                        break;
                    case 9:
                        Country="tr";
                        setLocale("en");
                        break;
                    case 10:
                        Country="in";
                        setLocale("en");
                        break;
                    case 11:
                        Country="cn";
                        setLocale("ar");
                        break;
                    case 12:
                        Country="jp";
                        setLocale("en");
                        break;
                    case 13:
                        Country="ru";
                        setLocale("en");
                        break;
                }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto_generated method stub
    }
}
