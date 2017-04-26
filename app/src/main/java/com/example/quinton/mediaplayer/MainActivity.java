package com.example.quinton.mediaplayer;

import android.app.Application;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  {


    // Temp for keeping track of activity lifecycle
    String lifecycleStatus = "";
    int order = 0;
    int savedSong;

    // Set this true/false to toggle activity state change messages.
    // They're pretty annoying unless you're specifically doing something
    // related to state changes.
    boolean displayActivityStateChangeMessages = false;

    static QMediaPlayer qMediaPlayer = new QMediaPlayer(); // TODO Use this as global reference to update PlayBar
    static MediaManager MediaManager = new MediaManager();
    ArrayList<? extends MediaObject> Media = MediaManager.getSongs();
    public static CurrentlyPlaying currentMedia = new CurrentlyPlaying();
    public static Song currentSong;
    public static ViewPager vPager; // TODO CHANGE 2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MediaManager.setIndex(qMediaPlayer.addSongs(MediaManager.getSongs()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedSong >= 0)
        {
            currentSong = qMediaPlayer.mps.get(0).get(savedSong);
        }
        else
        {
            currentSong = (Song) Media.get(0);
        }
        /* The block below is setting up the tab TabLayout and ViewPager*/

        // Create tabLayout and tabViewPager and associate them with xml layouts in activity_main.xml
        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_menu_tab_layout);
        ViewPager tabViewPager = (ViewPager) findViewById(R.id.tab_view_pager);
        // Sets adapter of tabViewPager to be the custom adapter we've written to define
        // how the tabViewPager creates and displays fragments inside each page.
        tabViewPager.setAdapter(new CustomTabFragmentPagerAdapter(getSupportFragmentManager(),
                MainActivity.this,Media,MediaManager,qMediaPlayer));
        // Give tabLayout the tabViewPager
        tabLayout.setupWithViewPager(tabViewPager);


        // TODO CHANGE 1
        /* The block below is setting up the currently playViewPager */
        vPager = (ViewPager) findViewById(R.id.play_view_pager);
        vPager.setAdapter(new CustomPlayBarFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this,currentSong,false));
    }



    // Tags for buttons so we can package some data with the button
    // and use one OnClickListener for all the buttons
    // NOTE: This is legacy, leaving it here in case we add any buttons.
    private static class ButtonTag {

        ButtonTag(String onClickMessage, Intent onClickIntent)
        {
            this.onClickMessage = onClickMessage;
            this.onClickIntent = onClickIntent;
        }

        String onClickMessage;
        Intent onClickIntent;
    }

    //Action to be taken when one of the main scrolling buttons is clicked.
    // Takes a tag from the button. The tag carries information for each specific
    // button so buttonAction can behave differently for each button that calls it.
    // NOTE: This is legacy, leaving it here in case we add any buttons.
    private void buttonAction(ButtonTag buttonTag)
    {
        CharSequence text = buttonTag.onClickMessage;
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();

        launchActivity(buttonTag.onClickIntent);
    }

    // Takes an explicit (specific activity to be launched is defined in the intent) intent for launching a new activity.
    // NOTE: This is legacy, leaving it here in case we add any activities.
    private void launchActivity(Intent intent) {

        // Check if the activity to handle this intent actually exists.
        if (intent.resolveActivity(getPackageManager()) != null)
        {

            // startActivity will find the activity(s) that handle the intent (assuming at least one exists).
            // Application will crash if no activity exists to handle the intent.
            startActivity(intent);
        }
    }






    public void displayStatus(){

        if (displayActivityStateChangeMessages)
        {
            order++;
            String message = String.valueOf(order) + ": " + lifecycleStatus;
            //t.setText(status);
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
    }


    // All the functions below are the activity state transition functions
    // that Android uses. I've overridden all of them to print a message every time
    // an activity state transition happens. This is so we can learn what's going on
    // easier and figure out the best place for various bits of code.
    // You can find activity lifecycle charts online to clear things up.
    //
    //Also note: Each activity has it's own unique lifecycle.
    @Override
    protected void onStart() {

        // Starting activity
        super.onStart();
        lifecycleStatus = "Activity Started!";
        displayStatus();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        // Restoring instance state
        super.onRestoreInstanceState(savedInstanceState);
        lifecycleStatus = "Restoring Instance!";
        savedSong = savedInstanceState.getInt("Song");
        displayStatus();
    }

    @Override
    protected void onResume() {

        // Resuming activity
        super.onResume();
        lifecycleStatus = "Activity Resumed!";
        vPager.setAdapter(new CustomPlayBarFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this,currentSong,false));
        displayStatus();
    }

    @Override
    protected void onPause() {
        //activity paused
        super.onPause();
        lifecycleStatus = "Activity Paused!";
        displayStatus();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //saving instance state
        super.onSaveInstanceState(outState);
        lifecycleStatus = "Saving Instance!";
        outState.putInt("Song",currentSong.getIndexInPlayer());
        displayStatus();

    }

    @Override
    protected void onStop() {
        //activity stopped
        super.onStop();
        lifecycleStatus = "Activity Stopped!";
        displayStatus();
    }

    @Override
    protected void onDestroy() {
        //activity destroyed
        super.onDestroy();
        lifecycleStatus = "Activity Destroyed!";
        displayStatus();
    }

    @Override
    protected void onRestart() {
        //activity destroyed
        super.onRestart();
        lifecycleStatus = "Activity Restarted!";
        displayStatus();
    }

}
