package com.example.quinton.mediaplayer;

import android.content.Context;
import android.icu.text.DateFormat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by julian on 3/29/2017.
 */


    // We're extending FragmentStatePagerAdapter here to avoid eating up memory storing too many fragments.
    // StatePagerAdapter handles saving and storing fragment instances and destroys the fragments when they're
    // not in view. There's more overhead to generate each fragment but it also only stores 1 in memory at a time.
    // This is a good trade off because our play bar will only display 1 fragment at a time but could have an arbitrary and
    // possibly very large number to cycle through.
public class CustomPlayBarFragmentPagerAdapter extends FragmentStatePagerAdapter {

    final int fragment_number = 6;
    private String tabTitles[] = new String[] { "PLAYLISTS", "ARTISTS", "ALBUMS", "SONGS", "VIDEOS", "PHOTOS" };
    private Context c;
    Song data;
    Boolean startSong;

    public CustomPlayBarFragmentPagerAdapter(FragmentManager fm, Context context, Song data, Boolean startSong) {
        super(fm);
        this.c = context;
        this.data = data;
        this.startSong = startSong;

    }

    @Override
    public int getCount() {

        // TODO Disabled next/previous scrolling for now. No time to finish before deadline. Finish after.
        /*
        if (data != null && data.size() > 0)
        {
            return data.size()*1000;
        }
        else
        {
            return 1;
        }
        */

        return 1;

        //return fragment_number;
    }

    @Override
    public Fragment getItem(int position) {

        // TODO Disabled next/previous scrolling for now. No time to finish before deadline. Finish after.
        /*
        // Check if there is any data to be displayed
        if (data != null && data.size() > 0)
        {
            position = position % data.size();
           return PlayFragment.newInstance(position + 1,data);
        }
        else
        {
            return null;
           // return PlayFragment.newInstance(null);
        }
        */

        return PlayFragment.newInstance(1,data,startSong);
    }



    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position

        return tabTitles[position];
    }

}
