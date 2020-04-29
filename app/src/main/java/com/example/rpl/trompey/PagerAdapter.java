package com.example.rpl.trompey;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int nTabs;
    public PagerAdapter(@NonNull FragmentManager fm, int nTabs) {
        super(fm);
        this.nTabs = nTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragmentHome home = new FragmentHome();
                return home;
            case 1:
                FragmentUser user = new FragmentUser();
                return user;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return nTabs;
    }
}
