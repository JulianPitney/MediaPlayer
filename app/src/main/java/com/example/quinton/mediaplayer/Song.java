package com.example.quinton.mediaplayer;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.Serializable;

import static android.os.UserHandle.readFromParcel;

/**
 * Created by quinton on 3/18/2017.
 */

public class Song extends MediaObject {
    public LastFMInfoResult infoResult;
    public LastFMSearchResult searchResult;
    private int index;
    private int indexInPlayer;
    public boolean isLoaded;
    public Song(File media, int index){
        super(media);
        indexInPlayer = index;
        LastFMResponseManager lfrm = new LastFMResponseManager(fileName, this);
        lfrm.execute();
    }
    public void setIndex(){

    }
    public int getIndexInPlayer(){
        return indexInPlayer;
    }


}
