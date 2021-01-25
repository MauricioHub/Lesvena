package com.example.nvm.lesvena;

/*import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;*/

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by NVM on 10/31/2017.
 */

public class MyAdapterGallery extends FragmentPagerAdapter {

    public MyAdapterGallery(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new DtsListFragment();

        }
        return null;
    }

    @Override
    public int getCount() {

        return 1;
        //return int_items;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "LOCAL DATASET";

        }

        return null;
    }
}
