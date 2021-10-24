package com.eng_hussein_khalaf066336.newsapp.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;

import com.eng_hussein_khalaf066336.newsapp.R;
import com.eng_hussein_khalaf066336.newsapp.ui.fragment.AboutFragment;
import com.eng_hussein_khalaf066336.newsapp.ui.fragment.FavoriteNewsFragment;
import com.eng_hussein_khalaf066336.newsapp.ui.fragment.LanguageFragment;
import com.eng_hussein_khalaf066336.newsapp.ui.fragment.NewsFragment;
import com.eng_hussein_khalaf066336.newsapp.ui.fragment.NoInternetFragment;
import com.eng_hussein_khalaf066336.newsapp.utils.Utils;
import com.google.android.material.navigation.NavigationView;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
 {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
     Fragment fragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.nav_toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation);
        fragment = new NewsFragment();
        setSupportActionBar(toolbar);
        Drawable nav = toolbar.getNavigationIcon();
        if(nav != null) {
            nav.setTint(getResources().getColor(R.color.white));
        }
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //control icon color
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        //change icon menu
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_all_news_24dp);
        if (Utils.isConnected(this))
        {
            loadFragment(fragment);
        }
        else
        {
            fragment=new NoInternetFragment();
            loadFragment(fragment);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.News:
                if (Utils.isConnected(this))
                {
                    fragment=new NewsFragment();
                    loadFragment(fragment);                }
                else
                {
                    fragment=new NoInternetFragment();
                    loadFragment(fragment);
                }
                return true;
            case R.id.favorite:
                fragment=new FavoriteNewsFragment();
                loadFragment(fragment);
                return true;
            case R.id.Language:
                fragment=new LanguageFragment();
                loadFragment(fragment);
                return true;
            case R.id.AboutMy:
                fragment=new AboutFragment();
                loadFragment(fragment);
                return true;
        }
        return false;
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containet1, fragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
        //  fragmentTransaction.addToBackStack(null);
    }
 }
