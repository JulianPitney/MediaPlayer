package com.example.quinton.mediaplayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.quinton.mediaplayer.MainActivity.MediaManager;
import static com.example.quinton.mediaplayer.MainActivity.qMediaPlayer;


/**
 * Created by julian on 3/29/2017.
 */

public class PlayFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    static Song data;
    static Boolean startSong2;
    Boolean isPlaying = false;
    View.OnClickListener playButtonToggle = new View.OnClickListener(){


        @Override
        public void onClick(View v) {

            if(isPlaying)
            {
                v.setBackgroundResource(R.drawable.play_icon);
                qMediaPlayer.pause();
            }
            else
            {
                v.setBackgroundResource(R.drawable.pause_icon);
                if(startSong2)
                {
                    qMediaPlayer.currentPlayer.start();
                }
                else
                {
                    qMediaPlayer.playSongat(data.getIndexInPlayer(), MediaManager.getIndex());
                    startSong2 = true;
                }
            }

            // Flip state
            isPlaying = !isPlaying;

        }
    };

    public static PlayFragment newInstance(int page, Song currentData, Boolean startSong) {
        Bundle args = new Bundle();
        data = currentData;
        startSong2 = startSong;
        args.putInt(ARG_PAGE, page);
        PlayFragment fragment = new PlayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.play_fragment, container, false);

        ImageView image = (ImageView) view.findViewById(R.id.play_bar_image);
        //Set gridItem image to scale properly within it's parent view
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        TextView title = (TextView) view.findViewById(R.id.play_bar_song_name);
        TextView author = (TextView) view.findViewById(R.id.play_bar_band_name);


        if (data.infoResult != null)
        {
            image.setImageBitmap(data.image);
            title.setText(data.infoResult.track.name);
            author.setText(data.infoResult.track.artist.name);

        }
        else
        {
            image.setImageResource(R.drawable.default_art);
            title.setText(data.fileName);
            author.setText("Author not loaded");
        }

        ImageButton playButton = (ImageButton) view.findViewById(R.id.play_bar_play_button);
        playButton.setOnClickListener(playButtonToggle);

        if(startSong2)
        {
            qMediaPlayer.playSongat(data.getIndexInPlayer(), MediaManager.getIndex());
            isPlaying = true;
            playButton.setBackgroundResource(R.drawable.pause_icon);
        }
        else if(qMediaPlayer.currentPlayer.isPlaying())
        {
            isPlaying = true;
            playButton.setBackgroundResource(R.drawable.pause_icon);
        }

        return view;
    }
}