package com.example.quinton.mediaplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import static com.example.quinton.mediaplayer.MainActivity.currentMedia;


/**
 * Created by julian on 3/29/2017.
 */

public class PlaylistsFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    static ArrayList<? extends MediaObject> data;
    static MediaManager mm;
    static int pageNum;
    static String fragmentName;
    public static ArrayList<Playlist> playlists;
    static QMediaPlayer qmp;
    private static PlaylistManager plm = new PlaylistManager();

    public static PlaylistsFragment newInstance(int page, ArrayList<? extends MediaObject> incomingData, MediaManager inputMM, QMediaPlayer inputMP, String fragName) {
        Bundle args = new Bundle();
        playlists = plm.getPlaylists();
        mm = inputMM;
        qmp = inputMP;
        args.putInt(ARG_PAGE, page);
        PlaylistsFragment fragment = new PlaylistsFragment();
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


        System.out.println("PLAYLISTS FRAG");

        final View view = inflater.inflate(R.layout.tab_fragment, container, false);
        GridView gridView;
        PlaylistsGridAdapter gridAdapter;

        //Setup gridView
        gridView = (GridView) view.findViewById(R.id.artists_grid);

        // This is a custom adapter to populate a GridView with an array of MediaObjects.
        // Adapters take in a layout (in this case one we've defined in xml) and some data.
        // The adapter knows how to populate the custom layout it receives with the data it receives
        // using some predefined functions (in this case custom functions we've written in MediaGridAdapter.java).

        gridAdapter = new PlaylistsGridAdapter(view.getContext(),R.layout.grid_item, data, pageNum, playlists);

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

                Intent albumIntent = new Intent(getActivity(),ZoomedInActivity.class);
                PlaceHolder test = (PlaceHolder) v.getTag();
                currentMedia.data = test.media;
                currentMedia.name = (String) "Playlist: " + test.gridItemText.getText();
                getActivity().startActivity(albumIntent);

            }
        });


        return view;
    }
}