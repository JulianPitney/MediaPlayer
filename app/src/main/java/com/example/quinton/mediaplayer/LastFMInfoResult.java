package com.example.quinton.mediaplayer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by quinton on 3/21/2017.
 */

public class LastFMInfoResult {
    track track;
    class track{
        String name;
        String mbid;
        String url;
        String duration;
        streamable streamable;
        String listeners;
        String playcount;
        artist artist;
        album album;
        toptags toptags;
        wiki wiki;
    }
    class streamable{
        @SerializedName("#text")
        @Expose
        String text;
        String fulltrack;
    }
    class artist{
        String name;
        String mbid;
        String url;
    }
    class album{
        String artist;
        String title;
        String mbid;
        String url;
        image[] image = null;
        @SerializedName("@attr")
        @Expose
        attr attr;
    }
    class image{
        @SerializedName("#text")
        @Expose
        String text;
        String size;
    }
    class attr{
        String position;
    }
    class toptags{
        tag[] tag;
    }
    class tag{
        String name;
        String url;
    }
    class wiki{
        String published;
        String summary;
        String content;
    }
}
