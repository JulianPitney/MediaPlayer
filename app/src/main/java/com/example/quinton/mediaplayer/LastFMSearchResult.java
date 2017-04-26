package com.example.quinton.mediaplayer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by quinton on 3/21/2017.
 */

public class LastFMSearchResult {
    results results;
    class results{
        @SerializedName("opensearch:Query")
        @Expose
        Query query;
        @SerializedName("opensearch:totalResults")
        @Expose
        String totalResults;
        @SerializedName("opensearch:startIndex")
        @Expose
        String startIndex;
        @SerializedName("opensearch:itemsPerPage")
        @Expose
        String itemsPerPage;
        trackmatches trackmatches;
        @SerializedName("@attr")
        @Expose
        attr attr;
    }
    class opensearch{
        String totalResults;
        String startIndex;
        String itemsPerPage;
    }
    class attr{

    }
    class Query{
        @SerializedName("#text")
        @Expose
        String text;
        String role;
        String startPage;
    }
    class track{
        String name;
        String artist;
        String url;
        String streamable;
        String listeners;
        image[] image = null;
        String mbid;
    }
    class image{
        @SerializedName("#text")
        @Expose
        String text;
        String size;
    }
    class trackmatches{
        track[] track;
    }
}
