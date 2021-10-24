package com.eng_hussein_khalaf066336.newsapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eng_hussein_khalaf066336.newsapp.R;
import com.eng_hussein_khalaf066336.newsapp.adapter.AdapterNews;
import com.eng_hussein_khalaf066336.newsapp.databinding.FragmentFavoriteNewsBinding;
import com.eng_hussein_khalaf066336.newsapp.model.Articles;
import com.eng_hussein_khalaf066336.newsapp.ui.activity.ArticleActivity;
import com.eng_hussein_khalaf066336.newsapp.viewModels.ViewModelNews;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FavoriteNewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener
 {
    private FragmentFavoriteNewsBinding binding;
    private AdapterNews adapterNews;
    private List<Articles> articlesList = new ArrayList<>();
    private ViewModelNews viewModelNews;
    private LiveData<List<Articles>>  liveData;

    public FavoriteNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentFavoriteNewsBinding.inflate(inflater,container,false);
        doInitialization();
        return binding.getRoot();
    }

    private void doInitialization() {
        binding.fragmentNewsRvShowNews.setHasFixedSize(true);
        binding.fragmentNewsRvShowNews.setItemAnimator(new DefaultItemAnimator());
        binding.fragmentNewsRvShowNews .setNestedScrollingEnabled(false);
        binding.fragmentNewsSwipeRefreshLayout.setOnRefreshListener(this);
        binding.fragmentNewsSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        viewModelNews= new ViewModelProvider(this).get(ViewModelNews.class);
        onLoadingSwipingRefresh("");
    }
    public void getNews(final String  keyword){
        binding.fragmentNewsTvTitle.setVisibility(View.INVISIBLE);
        binding.fragmentNewsSwipeRefreshLayout.setRefreshing(true);
        articlesList.clear();
        liveData=  viewModelNews.getAllArticles();
        liveData.observe(this, articles -> {
            if (articles != null)
            {
                    articlesList.addAll(articles);
                    adapterNews = new AdapterNews(articlesList, getContext(), (view, position) -> {
                        Articles article = articlesList.get(position);
                        Intent intent =  new Intent(getActivity().getBaseContext(), ArticleActivity.class);
                        intent.putExtra("url", article.getUrl());
                        intent.putExtra("title", article.getTitle());
                        intent.putExtra("img",  article.getUrlToImage());
                        intent.putExtra("date",  article.getPublishedAt());
                        intent.putExtra("source",  article.getSource().getName());
                        startActivity(intent);
                    }, Position -> {
                           viewModelNews.insertOrUpdate(articlesList.get(Position));
                           Toast.makeText(getContext(), "Add the item in favorite", Toast.LENGTH_SHORT).show();
                    }, Position -> {
                        viewModelNews.delete(articlesList.get(Position));
                        Toast.makeText(getContext(), "delete the item in favorite", Toast.LENGTH_SHORT).show();
                        articlesList.clear();
                        adapterNews.notifyDataSetChanged();
                    },"delete");
                    binding.fragmentNewsRvShowNews.setAdapter(adapterNews);
                    adapterNews.notifyDataSetChanged();
                    binding.fragmentNewsTvTitle.setVisibility(View.VISIBLE);
                    binding.fragmentNewsSwipeRefreshLayout.setRefreshing(false);
            }
            else {
                binding.fragmentNewsSwipeRefreshLayout.setRefreshing(false);
                binding.fragmentNewsTvTitle.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void onLoadingSwipingRefresh(final String Keyword) {

        binding.fragmentNewsSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                getNews(Keyword);

            }
        });
    }

    @Override
    public void onRefresh() {
        getNews("");
    }
}
