package com.example.quinton.mediaplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlaylistsGridAdapter extends BaseAdapter {
    private Context mContext;
    private int layoutResourceID;
    private ArrayList<? extends MediaObject> data;
    private ArrayList<Playlist> playlists;
    private int pageNum;

    public PlaylistsGridAdapter(Context c, int resource, ArrayList<? extends MediaObject> data, int inputPageNum, ArrayList<Playlist> inputPlaylists) {
        this.mContext = c;
        this.layoutResourceID = resource;
        this.data = data;
        playlists = inputPlaylists;
        pageNum = inputPageNum;
    }

    public int getCount() {
        return playlists.size();
    }

    public Playlist getItem(int position) {
        return playlists.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }



    // create a new ImageView for each item referenced by the Adapter.
    //
    // Here we're implementing the Android "holder" pattern. It's explained below
    // but basically we just inflate 1 view and then update it's data over and over,
    // as apposed to inflating a new view for every item, which is really expensive.
    // This is pretty cool because we can have a list of 1000s of items and it will
    // still be smooth.
    public View getView(int position, View convertView, ViewGroup parent) {

        View gridItem = convertView;
        PlaceHolder holder = null;

        // TODO Write switch statement that inflates view differently based on inputPageNum

        // If we haven't inflated a View yet, inflate one.
        if (gridItem == null)
        {
            //Create a new view
            LayoutInflater inflater = LayoutInflater.from(mContext);
            // Inflating a view is expensive, and is the operation we are trying to minimize.
            // (Possibly because the XML is loaded from disk?)
            gridItem = inflater.inflate(layoutResourceID,parent,false);

            holder = new PlaceHolder();

            holder.gridItemText = (TextView) gridItem.findViewById(R.id.grid_item_text);
            holder.gridItemImage = (ImageView) gridItem.findViewById(R.id.grid_item_image);

            // Sets holder as our View's tag (Just means the View now contains a reference to holder)
            gridItem.setTag(holder);
        }
        else
        {
            // If we HAVE inflated a View, get the tag for our already inflated View.
            holder = (PlaceHolder) gridItem.getTag();
        }


        //Getting data from the data array
        Playlist currentPlaylist = playlists.get(position);
        String playlistName = currentPlaylist.name;

        holder.media = currentPlaylist.songs;
        // Update the data in holder to reflect that of currentMediaObject
        holder.gridItemText.setText(playlistName);

        if (currentPlaylist.songs.get(0).image == null)
        {
            holder.gridItemImage.setImageResource(R.drawable.default_art);
        }
        else
        {
            holder.gridItemImage.setImageBitmap(currentPlaylist.songs.get(0).image);
        }
        //Set gridItem image to scale properly within it's parent view
        holder.gridItemImage.setScaleType(ImageView.ScaleType.FIT_XY);


        // Return view with updated data.
        return gridItem;
    }



}
