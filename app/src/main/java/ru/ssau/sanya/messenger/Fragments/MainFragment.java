package ru.ssau.sanya.messenger.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.ssau.sanya.messenger.Adapter.TabsPagerAdapter;
import ru.ssau.sanya.messenger.R;



public class MainFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;


    public MainFragment() {
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.pager);
        String[] tabs = {getResources().getString(R.string.contacts),getResources().getString(R.string.messages)};
        viewPager.setAdapter(new TabsPagerAdapter(getChildFragmentManager(),tabs));
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
