package com.eng_hussein_khalaf066336.newsapp.ui.activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.eng_hussein_khalaf066336.newsapp.R;
import com.eng_hussein_khalaf066336.newsapp.databinding.ActivityArticleBinding;
import com.eng_hussein_khalaf066336.newsapp.utils.Utils;
import com.google.android.material.appbar.AppBarLayout;

public class ArticleActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener
 {
    ActivityArticleBinding binding;
    private boolean isHideToolbarView = false;
    private String mUrl, mImg, mTitle, mDate, mSource, mAuthor;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_article);
         setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.collapsingToolbar.setTitle("");
        binding.appbar.addOnOffsetChangedListener(this);
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("url");
        mImg = intent.getStringExtra("img");
        mTitle = intent.getStringExtra("title");
        mDate = intent.getStringExtra("date");
        mSource = intent.getStringExtra("source");
        mAuthor = intent.getStringExtra("author");
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(Utils.getRandomDrawbleColor());

        Glide.with(this)
                .load(mImg)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.backdrop);

        binding.titleOnAppbar.setText(mSource);
        binding.subtitleOnAppbar.setText(mUrl);
        binding.date.setText(Utils.DateFormat(mDate));
        binding.title.setText(mTitle);
        String author;
        if (mAuthor != null){
            author = " \u2022 " + mAuthor;
        } else {
            author = "";
        }

        binding.time.setText(mSource + author + " \u2022 " + Utils.DateToTimeFormat(mDate));

        initWebView(mUrl);
    }
    private void initWebView(String url){
        binding.webView.getSettings().setLoadsImagesAutomatically(true);
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.getSettings().setDomStorageEnabled(true);
        binding.webView.getSettings().setSupportZoom(true);
        binding.webView.getSettings().setBuiltInZoomControls(true);
        binding.webView.getSettings().setDisplayZoomControls(false);
        binding.webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        binding.webView.setWebViewClient(new WebViewClient());
        binding.webView.loadUrl(url);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        if (percentage == 1f && isHideToolbarView) {
            binding.dateBehavior.setVisibility(View.GONE);
            binding.titleAppbar.setVisibility(View.VISIBLE);
            isHideToolbarView = !isHideToolbarView;

        } else if (percentage < 1f && !isHideToolbarView) {
            binding.dateBehavior.setVisibility(View.VISIBLE);
            binding.titleAppbar.setVisibility(View.GONE);
            isHideToolbarView = !isHideToolbarView;
        }
    }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.articles_menu, menu);
         return true; }

     @Override
     public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         switch (item.getItemId())
         {
             case R.id.web_view:
                 Intent intent = new Intent(Intent.ACTION_VIEW);
                 intent.setData(Uri.parse(mUrl));
                 startActivity(intent);
                 return true;
             case R.id.share :
                 try{

                     Intent shareIntent = new Intent(Intent.ACTION_SEND);
                     shareIntent.setType("text/plan");
                     shareIntent.putExtra(Intent.EXTRA_SUBJECT, mSource);
                     String body = mTitle + "\n" + mUrl + "\n" + "Share from the News App" + "\n";
                     shareIntent.putExtra(Intent.EXTRA_TEXT, body);
                     startActivity(Intent.createChooser(shareIntent, "Share with :"));

                 }catch (Exception e){
                     Toast.makeText(this, "Hmm.. Sorry, \nCannot be share", Toast.LENGTH_SHORT).show();
                 }
         }
         return super.onOptionsItemSelected(item);
     }
 }
