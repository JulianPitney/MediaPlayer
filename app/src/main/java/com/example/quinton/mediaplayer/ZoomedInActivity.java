package com.example.quinton.mediaplayer;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import static com.example.quinton.mediaplayer.MainActivity.MediaManager;
import static com.example.quinton.mediaplayer.MainActivity.currentMedia;
import static com.example.quinton.mediaplayer.MainActivity.currentSong;
import static com.example.quinton.mediaplayer.MainActivity.qMediaPlayer;
import static com.example.quinton.mediaplayer.MainActivity.vPager;

public class ZoomedInActivity extends AppCompatActivity {

    public static ViewPager playViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zoomed_in);

        ArrayList<? extends MediaObject> albumData = currentMedia.data;;
        String albumName = currentMedia.name;


        /* The block below is setting up the tab TabLayout and ViewPager*/
        // Create tabLayout and tabViewPager and associate them with xml layouts in activity_main.xml
        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_menu_tab_layout2);
        ViewPager tabViewPager = (ViewPager) findViewById(R.id.tab_view_pager2);
        // Sets adapter of tabViewPager to be the custom adapter we've written to define
        // how the tabViewPager creates and displays fragments inside each page.
        tabViewPager.setAdapter(new ZoomedInTabFragmentPagerAdapter(getSupportFragmentManager(),
                ZoomedInActivity.this,albumData,MediaManager,qMediaPlayer,albumName));
        // Give tabLayout the tabViewPager
        tabLayout.setupWithViewPager(tabViewPager);

        playViewPager = (ViewPager) findViewById(R.id.play_view_pager2);
        playViewPager.setAdapter(new CustomPlayBarFragmentPagerAdapter(getSupportFragmentManager(), ZoomedInActivity.this,currentSong, false));

    }



}
