package com.example.quinton.mediaplayer;

import android.graphics.Bitmap;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

/**
 * Created by jp183 on 2017-03-16.
 */

public class MediaObject {


    String fileName;
    String fullPath;
    String mediaType; // Maybe make a struct for this later (or whatever the java version of struct is)
    Bitmap image = null;
    File media;


    MediaObject(File _media)
    {
        this.media= _media;
        findData();
    }
    private void findData(){
        fileName = media.getName();
        fullPath = media.getPath();
        mediaType = getType();
    }
    private String getType(){
        String extension = FilenameUtils.getExtension(media.getPath());
        return extension;
    }
    public File getFile(){
        return media;
    }
}
