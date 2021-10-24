package com.eng_hussein_khalaf066336.newsapp.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eng_hussein_khalaf066336.newsapp.R;
import com.eng_hussein_khalaf066336.newsapp.databinding.FragmentNoInternetBinding;
import com.eng_hussein_khalaf066336.newsapp.utils.Utils;

public class NoInternetFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private FragmentNoInternetBinding binding;
    private Fragment fragment = null;


    public NoInternetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNoInternetBinding.inflate(inflater, container, false);
        binding.fragmentNoInternetSwipeRefreshLayout.setOnRefreshListener(this);
        loadFragment();
        return binding.getRoot();
    }

    @Override
    public void onRefresh() {
        onLoadingSwipingRefresh();
    }
    public void onLoadingSwipingRefresh() {
        binding.fragmentNoInternetSwipeRefreshLayout.post(() -> loadFragment());
    }
    private void loadFragment() {
        if (Utils.isConnected(getActivity())) {
            fragment = new NewsFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.containet1, fragment).commit();
        }
        else {
            binding.fragmentNoInternetSwipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getContext(), "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }
}
