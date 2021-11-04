package com.eng_hussein_khalaf066336.newsapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eng_hussein_khalaf066336.newsapp.R;
import com.eng_hussein_khalaf066336.newsapp.adapter.PagerAdapter;
import com.eng_hussein_khalaf066336.newsapp.databinding.FragmentNewsCategoryBinding;
import com.eng_hussein_khalaf066336.newsapp.utils.Utils;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class NewsCategoryFragment extends Fragment {
    private FragmentNewsCategoryBinding binding;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    String [] categoryNames;
    public NewsCategoryFragment() {
        // Required empty public constructor

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        categoryNames=context.getResources().getStringArray(R.array.categoryNames_array);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentNewsCategoryBinding.inflate(inflater,container,false);
        for (int i = 0; i< Utils.ArrayCategoryName().size(); i++)
        {
            fragments.add(NewsFragment.newInstance(Utils.ArrayCategoryName().get(i)));
        }
        PagerAdapter pagerAdapter = new PagerAdapter(getActivity(),fragments);
        binding.fragmentNewsCategoryViewPager.setAdapter(pagerAdapter);
        new TabLayoutMediator(binding.fragmentNewsCategoryTabLayout, binding.fragmentNewsCategoryViewPager
                , (tab, position) -> {
                    switch (position)
                    {
                        case 0:tab.setText(categoryNames[position]);
                            tab.setIcon(R.drawable.ic_new_black);
                            break;
                        case 1:tab.setText(categoryNames[position]);
                            tab.setIcon(R.drawable.ic_business);
                            break;
                        case 2:tab.setText(categoryNames[position]);
                            tab.setIcon(R.drawable.ic_favorite_black_24dp);
                            break;
                        case 3:tab.setText(categoryNames[position]);
                            tab.setIcon(R.drawable.ic_school_black);
                            break;
                        case 4:tab.setText(categoryNames[position]);
                            tab.setIcon(R.drawable.ic_run_black_24dp);
                            break;
                        case 5:tab.setText(categoryNames[position]);
                            tab.setIcon(R.drawable.ic_computer_black_24dp);
                            break;
                    }

                    tab.setText(Utils.ArrayCategoryName().get(position));

                }).attach();
        return binding.getRoot();
    }
}
