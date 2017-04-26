package com.example.quinton.mediaplayer;

import java.util.ArrayList;

/**
 * Created by quinton on 3/31/2017.
 */

public class Playlist {
    ArrayList<Song> songs = new ArrayList<>();
    String name;
    int playerIndex;
    public Playlist(String _name, int _playerIndex){
        name = _name;
        playerIndex = _playerIndex;
    }
    public void addSong(Song song){
        songs.add(song);
    }
}
