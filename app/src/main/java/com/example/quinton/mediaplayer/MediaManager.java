package com.example.quinton.mediaplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;


public class MediaManager{
    private ArrayList<File> mediaLocal;
    private Map<String, ArrayList<Song>> albums;
    private Map<String, ArrayList<Song>> artists;
    private ArrayList<Song> songsLocal;
    private int index;

    public MediaManager(){
        mediaLocal = new ArrayList<>();
        songsLocal = new ArrayList<>();
        albums = new TreeMap<>();
        artists = new TreeMap<>();
        discoverLocalMedia();
    }
    private void discoverLocalMedia(){
        //Discovers Local Content and Adds to ArrayList
        if(isExternalStorageWritable()){
            File localMediaDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
            for(int i=0;i< localMediaDirectory.listFiles().length;i++){
                Song temp = new Song(localMediaDirectory.listFiles()[i], i);
                songsLocal.add(temp);
            }
            mediaLocal.addAll(new ArrayList<>(Arrays.asList(localMediaDirectory.listFiles())));
        }
    }
    public ArrayList<Song> getSongs(){
        return songsLocal;
    }
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    public boolean generateArtists(){
        Map<String, ArrayList<Song>> tempMap = new TreeMap<>();
        System.out.println("LAST SONG HAS BEEN LOADED:     " +Boolean.toString(doneLoading()));
        //Makes sure last song has been loaded
        if(doneLoading()) {
            for (Song s : songsLocal) {
                //Make sure the song recieved the full result
                if (s.infoResult != null && !s.infoResult.track.artist.name.equals("")) {
                    if (!tempMap.containsKey(s.infoResult.track.artist.name)) {
                        ArrayList<Song> tempArrayList = new ArrayList<>();
                        tempArrayList.add(s);
                        tempMap.put(s.infoResult.track.artist.name, tempArrayList);
                    } else {
                        ArrayList<Song> tempArrayList = tempMap.get(s.infoResult.track.artist.name);
                        tempArrayList.add(s);
                        tempMap.put(s.infoResult.track.artist.name, tempArrayList);
                    }
                }
            }
            System.out.println("ARTISTS GENERATED!");
            artists = tempMap;
            return true;
        }
        return false;
    }
    public boolean generateAlbums(){
        Map<String, ArrayList<Song>> tempMap = new TreeMap<>();
        System.out.println("LAST SONG HAS BEEN LOADED:     " +Boolean.toString(doneLoading()));
        //Makes sure last song has been loaded
        if(doneLoading()) {
            for (Song s : songsLocal) {
                //Makes sure the song recieved a full result
                if (s.infoResult != null && !s.infoResult.track.album.title.equals("")) {
                    if (!tempMap.containsKey(s.infoResult.track.album.title)) {
                        ArrayList<Song> tempArrayList = new ArrayList<>();
                        tempArrayList.add(s);
                        tempMap.put(s.infoResult.track.album.title, tempArrayList);
                    } else {
                        ArrayList<Song> tempArrayList = tempMap.get(s.infoResult.track.album.title);
                        tempArrayList.add(s);
                        tempMap.put(s.infoResult.track.album.title, tempArrayList);
                    }
                }
            }
            System.out.println("ALBUMS GENERATED!");
            albums = tempMap;
            return true;
        }
        return false;
    }
    public void setIndex(int _index){
        index = _index;
    }
    public int getIndex(){
        return index;
    }
    public Map<String, ArrayList<Song>> getAlbums(){
        return albums;
    }
    public Map<String, ArrayList<Song>> getArtists(){
        return artists;
    }
    public boolean doneLoading(){
        //Potentially use a while loop like so to wait to generate while(!mp.checkState()){generateAlbums; generateArtists}
        if(songsLocal.get(songsLocal.size()-1).isLoaded){
            return true;
        }
        return false;
    }
}
