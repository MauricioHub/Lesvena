package com.example.nvm.lesvena;

/*import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;*/

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by Mauricio on 9/3/2017.
 */

public class MyAdapterRecognize extends FragmentPagerAdapter {

    public MyAdapterRecognize(FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new PictureRFragment();
            case 1:
                return new VisualizeRFragment();
            case 2:
                return new CompareFragment();
            case 3:
                return new RecognizingFragment();
            case 4:
                return new DetailFragment();

        }
        return null;
    }

    @Override
    public int getCount() {

        return 5;
        //return int_items;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "CAP";
            case 1:
                return "VIEW";
            case 2:
                return "COM";
            case 3:
                return "REC";
            case 4:
                return "DET";

        }

        return null;
    }
}
