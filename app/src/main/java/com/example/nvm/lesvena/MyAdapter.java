package com.example.nvm.lesvena;

/*import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;*/
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by Mauricio on 8/31/2017.
 */

public class MyAdapter extends FragmentPagerAdapter {

    public static int banderita = 0;

    public MyAdapter(FragmentManager fm)
    {
        super(fm);
    }

   /* public MyAdapter(FragmentManager childFragmentManager) {

    }*/

    @Override
    public Fragment getItem(int position) {

       // System.out.println("SOY ADAPTER !!!!!!!!!!!!!!!!!!!" + position);
        switch (position){
            case 0:
                return new PictureFragment();
            case 1:
                return new VisualizeFragment();
            case 2:
                return new RegisterFragment();

        }
        return null;
    }

    @Override
    public int getCount() {

return 3;
        //return int_items;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Pic";
            case 1:
                return "View";
            case 2:
                return "Reg";

        }

        return null;
    }
}
