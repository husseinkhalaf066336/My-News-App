package com.eng_hussein_khalaf066336.newsapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.eng_hussein_khalaf066336.newsapp.R;
import com.eng_hussein_khalaf066336.newsapp.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {
    ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_splash_screen);
        binding.SplashScreenImageViewLogo.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
        binding.SplashScreenLottieAnimationView2.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
        binding.SplashScreenLottieAnimationView3.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);

            new Handler().postDelayed(() -> {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }, 4500);
    }


}
