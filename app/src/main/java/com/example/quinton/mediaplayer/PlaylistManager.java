package com.example.quinton.mediaplayer;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Manages populating playlists from files.
 */

public class PlaylistManager {
    ArrayList<Playlist> playlists;
    public PlaylistManager(){
        playlists = new ArrayList<>();
        try {
            checkFolder();
            retrievePlaylists();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void checkFolder(){
        File playlistFolder = new File("/data/data/com.example.quinton.mediaplayer/Playlists/");
        //Makes folder if not found
        if(!playlistFolder.exists()){
            playlistFolder.mkdir();
        }
        System.out.println("FOUND PLAYLIST DIR @:       "+playlistFolder.getAbsolutePath());
    }
    //Retreives Playlists from a hardcoded folder
    //Playlists should be text files with each song path being on a new line
    private void retrievePlaylists() throws IOException {
        System.out.println("LOADING PLAYLISTS!");
        //Sets the directory where playlists are stored
        File playlistLocation = new File("/data/data/com.example.quinton.mediaplayer/Playlists/");
        if(playlistLocation.exists()) {
            //Searches through all child files to populate the playlists Array
            for (File f : playlistLocation.listFiles()) {
                //Opens the file
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                //Creates the playlist to be populated
                Playlist pl = new Playlist(f.getName(), playlists.size());
                String filepath = "";
                while ((filepath = br.readLine()) != null) {
                    pl.addSong(new Song(new File(filepath), 1));
                }
                //Adds the playlist to global array
                playlists.add(pl);
                //Closes Streams
                br.close();
                fr.close();
            }
        }
    }
    public void savePlaylist(int index){
        Playlist currPlaylist = playlists.get(index);
        //Opens up a file stream
    }
    //Returns Arraylist of all playlists
    public ArrayList<Playlist> getPlaylists(){
        return playlists;
    }
}
