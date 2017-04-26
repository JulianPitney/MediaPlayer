package com.example.quinton.mediaplayer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.google.gson.Gson;

import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * A manager which runs on another thread and does various server querries
 * to receive the information about songs
 */

public class LastFMResponseManager extends AsyncTask<Void, Void, Void> {
    private String apiKey = "7a80d927ad4888f37a2085e23e4d4673";
    private String songName;
    private String songArtist;
    private LastFMSearchResult lastFMSearchResult;
    private LastFMInfoResult lastFMInfoResult;
    private Song parentSong;
    private Bitmap picture = null;
    public LastFMResponseManager(String _songName){
        songName = _songName;
    }
    public LastFMResponseManager(String _songName, Song song){
        songName = _songName;
        parentSong = song;

    }
    private void getSongResults() throws IOException, URISyntaxException {
        //Used to search what song the song is ie who performed it etc formats the URL correctly
        URL searchSong = new URL("http://ws.audioscrobbler.com/2.0/?method=track.search&track="+
                formatForURL(songName)+"&api_key="+apiKey+"&format=json");
        String jsonResult = getJsonFromURL(searchSong);
        if(resultExists(jsonResult)){
            //Creates the class representing the result
            Gson gson = new Gson();
            lastFMSearchResult = gson.fromJson(jsonResult, LastFMSearchResult.class);
            //Case for no results
            if (lastFMSearchResult.results.trackmatches.track[0] != null && !lastFMSearchResult.results.trackmatches.track[0].mbid.equals("")) {
                songArtist = lastFMSearchResult.results.trackmatches.track[0].artist;
                //Used to get the find details about the song
                URL songInfo = new URL("http://ws.audioscrobbler.com/2.0/?method=track.getInfo&api_key=" +
                        apiKey + "&mbid=" + lastFMSearchResult.results.trackmatches.track[0].mbid + "&format=json");
                jsonResult = getJsonFromURL(songInfo);
                lastFMInfoResult = gson.fromJson(jsonResult, LastFMInfoResult.class);
                picture = getBitmap();
                System.out.println(songInfo.toString());
            }
        }
    }
    private String getJsonFromURL(URL url) throws IOException {
        //Gets the JSON as a string from the URL
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
        String inputLine;
        String jsonResult = "";
        while ((inputLine = in.readLine()) != null) {
            jsonResult += inputLine;
        }
        in.close();
        return jsonResult;
    }
    private boolean resultExists(String result){
        if(result.contains("\"opensearch:totalResults\":\"0\"")) return false;
        return true;
    }
    private String formatForURL(String part){
        //Removes Extension
        part = FilenameUtils.removeExtension(part);
        //Removes Bad Chars, Consider Splitting on More Types
        part = part.replace("-", "");
        part = part.replace(" ", "%20");
        return part;
    }
    private Bitmap getBitmap(){
        try {
            URL imageUrl = new URL(lastFMInfoResult.track.album.image[1].text);
            HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected Void doInBackground(Void... params) {
        try {
            getSongResults();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void result){
        parentSong.infoResult = lastFMInfoResult;
        parentSong.searchResult = lastFMSearchResult;
        parentSong.image = picture;
        parentSong.isLoaded = true;
    }
}
