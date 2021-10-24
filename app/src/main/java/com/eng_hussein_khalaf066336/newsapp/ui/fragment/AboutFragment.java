package com.eng_hussein_khalaf066336.newsapp.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eng_hussein_khalaf066336.newsapp.databinding.FragmentAboutBinding;
import com.eng_hussein_khalaf066336.newsapp.utils.Constants;

public class AboutFragment extends Fragment {
    FragmentAboutBinding binding;
    public AboutFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentAboutBinding.inflate(inflater,container,false);

        binding.cardViewGmail.setOnClickListener(v -> {
            goToUri(Constants.UriGmail);
        });
        binding.cardViewFacebook.setOnClickListener(v -> {
            goToUri(Constants.UriFacebook);
        });
        binding.cardViewGithub.setOnClickListener(v -> {
            goToUri(Constants.UriGithub);

        });
        binding.cardViewLinkedIn.setOnClickListener(v -> {
            goToUri(Constants.UriLinkedIh);
        });
        binding.cardViewYoutube.setOnClickListener(v -> {
            goToUri(Constants.UriYoutube);
        });
        binding.cardViewInsta.setOnClickListener(v -> {
            goToUri(Constants.UriInstagram);
        });
        return binding.getRoot();
    }
    private void goToUri(String s)
    {
      Uri uri= android.net.Uri.parse(s);
      startActivity(new Intent(Intent.ACTION_VIEW,uri));

    }
}
