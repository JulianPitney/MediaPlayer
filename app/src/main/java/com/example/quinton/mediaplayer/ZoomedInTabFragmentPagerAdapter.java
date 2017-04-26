package com.example.quinton.mediaplayer;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by julian on 3/29/2017.
 */

public class ZoomedInTabFragmentPagerAdapter extends FragmentPagerAdapter {

    final int fragment_number = 1;
    private String tabTitles[] = new String[1];
    private Context c;
    ArrayList<? extends MediaObject> data;
    MediaManager mm;
    QMediaPlayer mp;

    public ZoomedInTabFragmentPagerAdapter(FragmentManager fm, Context context, ArrayList<? extends MediaObject> data, MediaManager inputMM, QMediaPlayer inputMP, String mediaName) {
        super(fm);
        this.c = context;
        this.data = data;
        this.mm = inputMM;
        this.mp = inputMP;
        this.tabTitles[0] = mediaName;
    }

    @Override
    public int getCount() {
        return fragment_number;
    }

    @Override
    public Fragment getItem(int position)
    {
        return ZoomedInFragment.newInstance(position + 1, data, mm, mp, "ALBUM NAME");
    }



    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position

        return tabTitles[position];
    }

}
