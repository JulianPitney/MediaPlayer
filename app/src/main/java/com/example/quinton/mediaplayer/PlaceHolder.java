package com.example.quinton.mediaplayer;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

// A PlaceHolder object holds data for our custom View.
// This lets us set a PlaceHolder object as a tag for
// our custom View, so we can recycle the custom View (Since
// it's way less expensive to create PlaceHolder over and over
// than it is to inflate a custom View over and over). Since all the
// Views in the GridView are identical, save for some text and an imageID,
// this is very economical.
class PlaceHolder {

    TextView gridItemText;
    ImageView gridItemImage;
    MediaObject mediaObject;
    ArrayList<Song> media;

}