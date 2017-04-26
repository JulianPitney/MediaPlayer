package com.example.quinton.mediaplayer;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by julian on 3/29/2017.
 */

public class CustomTabFragmentPagerAdapter extends FragmentPagerAdapter {

    final int fragment_number = 4;
    private String tabTitles[] = new String[] { "PLAYLISTS", "ARTISTS", "ALBUMS", "SONGS" };
    private Context c;
    ArrayList<? extends MediaObject> data;
    MediaManager mm;
    QMediaPlayer mp;

    public CustomTabFragmentPagerAdapter(FragmentManager fm, Context context, ArrayList<? extends MediaObject> data, MediaManager inputMM, QMediaPlayer inputMP) {
        super(fm);
        this.c = context;
        this.data = data;
        this.mm = inputMM;
        this.mp = inputMP;
    }

    @Override
    public int getCount() {
        return fragment_number;
    }

    @Override
    public Fragment getItem(int position) {

        switch(position)
        {
            case 0:
                System.out.println(position);
                return PlaylistsFragment.newInstance(position + 1, data, mm, mp, "PLAYLISTS");

            case 1:
                System.out.println(position);
                return ArtistsFragment.newInstance(position + 1, data, mm, mp, "ARTISTS");

            case 2:
                System.out.println(position);
                return AlbumsFragment.newInstance(position + 1, data, mm, mp, "ALBUMS");
            case 3:
                System.out.println(position);
                return SongsFragment.newInstance(position + 1, data, mm, mp, "SONGS");
            default: return null;
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position

        return tabTitles[position];
    }

}
