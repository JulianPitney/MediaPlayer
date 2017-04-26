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
import java.util.Map;

import static com.example.quinton.mediaplayer.MainActivity.currentMedia;


/**
 * Created by julian on 3/29/2017.
 */

public class ArtistsFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    static ArrayList<? extends MediaObject> data;
    static MediaManager mm;
    static int pageNum;
    static String fragmentName;
    static QMediaPlayer qmp;
    ArtistsGridAdapter gridAdapter;
    View view;

    public static ArtistsFragment newInstance(int page, ArrayList<? extends MediaObject> incomingData, MediaManager inputMM, QMediaPlayer inputMP, String fragName) {
        Bundle args = new Bundle();
        data = incomingData;
        mm = inputMM;
        qmp = inputMP;
        args.putInt(ARG_PAGE, page);
        ArtistsFragment fragment = new ArtistsFragment();
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


        System.out.println("ARTISTS FRAG");

        view = inflater.inflate(R.layout.tab_fragment, container, false);

        //Setup gridView
        GridView gridView = (GridView) view.findViewById(R.id.artists_grid);

        // This is a custom adapter to populate a GridView with an array of MediaObjects.
        // Adapters take in a layout (in this case one we've defined in xml) and some data.
        // The adapter knows how to populate the custom layout it receives with the data it receives
        // using some predefined functions (in this case custom functions we've written in MediaGridAdapter.java).
        // TODO we need to overload this constructor handle different "data" inputs correctly.
        // TODO So if it gets an ArrayList of Playlists it does one thing and if it gets an ArrayList of Artists it does another.
        Thread t = new Thread(new Runnable() {
            ArtistsFragment parentFrag;
            public void run() {
                System.out.println("WAITING TO POPULATE ARTISTS!");
                while(!parentFrag.mm.doneLoading()){
                    //Waits until done loading data
                    //Sleeping to save cycles
                    System.out.println("Waiting...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                parentFrag.updateAdapter();
            }
            public Runnable setParams(ArtistsFragment _parentFrag){
                parentFrag = _parentFrag;
                return this;
            }
        }.setParams(this));
        t.start();

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
                currentMedia.name = (String) "Artist: " + test.gridItemText.getText();
                getActivity().startActivity(albumIntent);
            }
        });



        return view;
    }
    public void updateAdapter(){
        System.out.println("POPULATING ARTISTS!");
        //Generates Albums
        mm.generateArtists();
        System.out.println(mm.getArtists().toString());


        Map<String, ArrayList<Song>> artists = mm.getArtists();

        //Just get it to use the getArtists() Data here
        gridAdapter = new ArtistsGridAdapter(view.getContext(),R.layout.grid_item, data, pageNum, artists);
    }
}