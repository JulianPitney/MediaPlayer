package com.example.quinton.mediaplayer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import java.util.ArrayList;

import static com.example.quinton.mediaplayer.MainActivity.currentSong;
import static com.example.quinton.mediaplayer.ZoomedInActivity.playViewPager;


/**
 * Created by julian on 3/29/2017.
 */

public class ZoomedInFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    static ArrayList<? extends MediaObject> data;
    public static MediaManager mm;
    static int pageNum;
    static String fragmentName;
    static QMediaPlayer qmp;
    AlbumsGridAdapter gridAdapter;
    View view;

    public static ZoomedInFragment newInstance(int page, ArrayList<? extends MediaObject> incomingData, MediaManager inputMM, QMediaPlayer inputMP, String fragName) {
        Bundle args = new Bundle();
        data = incomingData;
        mm = inputMM;
        qmp = inputMP;
        args.putInt(ARG_PAGE, page);
        ZoomedInFragment fragment = new ZoomedInFragment();
        fragment.setArguments(args);
        pageNum = page;
        fragmentName = fragName;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        System.out.println("ALBUMS FRAG");

        view = inflater.inflate(R.layout.tab_fragment, container, false);


        //Setup gridView
        GridView gridView = (GridView) view.findViewById(R.id.artists_grid);


        //Just get it to use the getAlbums() Data here
        MediaGridAdapter gridAdapter = new MediaGridAdapter(view.getContext(),R.layout.grid_item, data, pageNum);

        // We then set the GridView's adapter to be our custom MediaGridAdapter.
        // The GridView then requests that each of it's "grid slots" be populated by the custom layout+data
        // that we gave to the MediaGridAdapter. The adapter does it's thing and then returns the view for that
        // particular slot and repeats until there is no more data to fill slots. Easy unlimited grid slots.
        gridView.setAdapter(gridAdapter);

        // GridView listener that grabs the tag of a gridItem when the item is clicked.
        // The tag of each gridItem points to the MediaObject associated with that gridItem.
        // This reference is then given to the global MediaManager which handles playing,
        // stopping, pausing, etc. (Note that all playing is done through the global media manager
        // to ensure only 1 file is ever loaded for playing at a time.
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                PlaceHolder placeHolder = (PlaceHolder) v.getTag();
                MediaObject mediaObject = placeHolder.mediaObject;

                //temp
                Song songobjv = (Song) mediaObject;
                Toast.makeText(view.getContext(),songobjv.fileName ,
                        Toast.LENGTH_SHORT).show();
                currentSong = songobjv;
                playViewPager.setAdapter(new CustomPlayBarFragmentPagerAdapter(getFragmentManager(), getContext(),songobjv,true));
                qmp.playSongat(songobjv.getIndexInPlayer(), mm.getIndex());
            }
        });


        return view;
    }
}