package com.eng_hussein_khalaf066336.newsapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eng_hussein_khalaf066336.newsapp.R;
import com.eng_hussein_khalaf066336.newsapp.adapter.AdapterNews;
import com.eng_hussein_khalaf066336.newsapp.databinding.FragmentNewsBinding;
import com.eng_hussein_khalaf066336.newsapp.model.Articles;
import com.eng_hussein_khalaf066336.newsapp.model.News;
import com.eng_hussein_khalaf066336.newsapp.ui.activity.ArticleActivity;
import com.eng_hussein_khalaf066336.newsapp.utils.Constants;
import com.eng_hussein_khalaf066336.newsapp.utils.Utils;
import com.eng_hussein_khalaf066336.newsapp.viewModels.ViewModelNews;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener
 {
     private static final String ARG_CATEGORY= "param2";
     private String mCategory;
    private FragmentNewsBinding binding;
    private AdapterNews adapterNews;
    private List<Articles> articlesList = new ArrayList<>();
    private ViewModelNews viewModelNews;
    private LiveData<News> liveData;
    private String country;
    private String language;
    public static MenuItem menuItem ;
     public NewsFragment() {
        // Required empty public constructor
    }
     public static NewsFragment newInstance(String category) {
         NewsFragment fragment = new NewsFragment();
         Bundle args = new Bundle();
         args.putString(ARG_CATEGORY, category);
         fragment.setArguments(args);
         return fragment;
     }

     @Override
     public void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         if (getArguments() != null) {
             mCategory = getArguments().getString(ARG_CATEGORY);
         }
     }

     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        binding=FragmentNewsBinding.inflate(inflater,container,false);
        doInitialization();
        return binding.getRoot();

    }
    private void doInitialization() {
        binding.fragmentNewsRvShowNews.setHasFixedSize(true);
        binding.fragmentNewsRvShowNews.setItemAnimator(new DefaultItemAnimator());
        binding.fragmentNewsRvShowNews .setNestedScrollingEnabled(false);
        binding.fragmentNewsSwipeRefreshLayout.setOnRefreshListener(this);
        binding.fragmentNewsSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        binding.fragmentNewsRvShowNews.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        viewModelNews= new ViewModelProvider(this).get(ViewModelNews.class);
        onLoadingSwipingRefresh("");
    }

    public void getNews(final String  keyword){
        binding.fragmentNewsSwipeRefreshLayout.setRefreshing(true);
        if(LanguageFragment.Country!="default")
        {
            country=LanguageFragment.Country;
        }
        else {
            country  = Utils.getCountry();
        }
        language = Utils.getLanguage();
        if (keyword.length()>0)
        {
            liveData= viewModelNews.getSearchNews(keyword,language,"publishedAt", Constants.ApiKey);
        }
        else {liveData= viewModelNews.getNews(country,mCategory, Constants.ApiKey);}
        liveData.observe(requireActivity(), new Observer<News>() {
            @Override
            public void onChanged(News news) {
                if (news != null)
                {
                    if (news.getArticles() !=null)
                    {
                        if (!articlesList.isEmpty()){
                            articlesList.clear();
                        }
                        articlesList.addAll(news.getArticles());
                        adapterNews = new AdapterNews(articlesList, getContext(), (view, position) -> {
                            Articles article = articlesList.get(position);
                            Intent intent = new Intent(getActivity().getBaseContext(), ArticleActivity.class);
                            intent.putExtra("url", article.getUrl());
                            intent.putExtra("title", article.getTitle());
                            intent.putExtra("img", article.getUrlToImage());
                            intent.putExtra("date", article.getPublishedAt());
                            intent.putExtra("source", article.getSource().getName());
                            intent.putExtra("author", article.getAuthor());
                            startActivity(intent);
                        }, Position -> {
                            viewModelNews.insertOrUpdate(articlesList.get(Position));
                            Toast.makeText(getContext(), "Add the item in favorite", Toast.LENGTH_SHORT).show();
                        }, Position -> {
                            viewModelNews.delete(articlesList.get(Position));
                            Toast.makeText(getContext(), "delete the item in favorite", Toast.LENGTH_SHORT).show();

                        },"add");
                        binding.fragmentNewsRvShowNews.setAdapter(adapterNews);
                        adapterNews.notifyDataSetChanged();
                        binding.fragmentNewsTvTitle.setVisibility(View.VISIBLE);
                        binding.fragmentNewsSwipeRefreshLayout.setRefreshing(false);
                    }
                }
                else {
                    binding.fragmentNewsSwipeRefreshLayout.setRefreshing(false);
                    binding.fragmentNewsTvTitle.setVisibility(View.INVISIBLE);
                }

            }
        });
    }
    public void onLoadingSwipingRefresh(final String Keyword)
    {
        binding.fragmentNewsSwipeRefreshLayout.post(() -> getNews(Keyword));
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_main,menu);
        SearchView searchView= (SearchView) menu.findItem(R.id.search).getActionView();
        menuItem=menu.findItem(R.id.search);
        menuItem.setVisible(true);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("Search Latest News...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length()>2)
                {
                    onLoadingSwipingRefresh(query);
                }
                else {
                    Toast.makeText(getContext(), "Type more than two letters!", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {return false;}
        });
        searchView.setOnCloseListener(() -> {
            onLoadingSwipingRefresh("");
            return false;
        });
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // searchView expanded
                } else {
                    // searchView not expanded
                    onLoadingSwipingRefresh("");
                }
            }
        });
        menuItem.getIcon().setVisible(true, true);
    }

    @Override
    public void onRefresh() {
        getNews("");

    }
}
