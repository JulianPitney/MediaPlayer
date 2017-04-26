package com.example.quinton.mediaplayer;


import android.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Plays media given File objects. Holds songs globally etc
 * MediaManager may contain one of these and playlistManager may contain one...
 */

public class QMediaPlayer {
    ArrayList<ArrayList<Song>> mps = new ArrayList<>();
    MediaPlayer currentPlayer;
    int songIndex, playerIndex;

    public QMediaPlayer(){
        currentPlayer = new MediaPlayer();
    }
    public int addSongs(ArrayList<Song> songs){
        mps.add(songs);
        return mps.size()-1;
    }
    public void refreshPlayer(){
        currentPlayer.reset();
    }
    public void playSongat(int _songIndex, int _index){
        currentPlayer.reset();
        songIndex = _songIndex;
        playerIndex = _index;
        System.out.println("PLAYING SONG AT:        "+mps.get(playerIndex).get(songIndex).getFile().getPath());
        try {
            currentPlayer.setDataSource(mps.get(playerIndex).get(songIndex).getFile().getPath());
            currentPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentPlayer.start();
    }
    public void next(){
        if(mps.get(playerIndex).size()>songIndex+1){
            songIndex++;
            playSongat(playerIndex, songIndex);
        }else{
            songIndex=0;
            playSongat(playerIndex, songIndex);
        }
    }
    public void prev(){
        if(songIndex-1>=0){
            songIndex--;
            playSongat(playerIndex,songIndex);
        }else{
            songIndex=mps.get(playerIndex).size();
            playSongat(playerIndex,songIndex);
        }
    }
    public void pause(){
        currentPlayer.pause();
    }
    public void cease(){
        currentPlayer.stop();
    }
}
