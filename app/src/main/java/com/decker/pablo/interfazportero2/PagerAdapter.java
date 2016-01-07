package com.decker.pablo.interfazportero2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Pablo on 03/01/2016.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabEstado tabEstado = new TabEstado();
                return tabEstado;
            case 1:
                TabSalidas tabSalidas = new TabSalidas();
                return tabSalidas;
            case 2:
                TabConfig1 tabConfig1 = new TabConfig1();
                return tabConfig1;
            case 3:
                TabConfig2 tabConfig2 = new TabConfig2();
                return tabConfig2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
