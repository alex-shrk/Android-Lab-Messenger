package ru.ssau.sanya.messenger.Adapter;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import ru.ssau.sanya.messenger.Fragments.ContactsFragment;
import ru.ssau.sanya.messenger.Fragments.MessagesFragment;
import ru.ssau.sanya.messenger.R;


public class TabsPagerAdapter extends FragmentStatePagerAdapter {

    private  String[] tabs;
    private  int FRAGMENT_CNT;
    public TabsPagerAdapter(FragmentManager supportFragmentManager,String[] tabs) {
        super(supportFragmentManager);
        this.tabs = tabs;
        this.FRAGMENT_CNT=tabs.length;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ContactsFragment();
            case 1:
                return new MessagesFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return FRAGMENT_CNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return tabs[0];
            case 1:
                return tabs[1];

        }
        return null;
    }
}
