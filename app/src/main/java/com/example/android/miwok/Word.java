package com.example.android.miwok;

import android.media.Image;

/**
 * Created by Gauri on 23-04-2018.
 */

public class Word {
    private String mDefaultTranslation;  //default translation

    private String mMarathiTranslation;   //marathi translation for word

    private int Object = NO_IMAGE;
    private static final int NO_IMAGE = -1;

    private int mAudioFile;

    public Word(String defaultTranslation , String marathiTranslation , int audioFile) {
        mDefaultTranslation = defaultTranslation;
        mMarathiTranslation = marathiTranslation;
        mAudioFile  = audioFile;
    }

    public Word(String defaultTranslation , String marathiTranslation, int getImage , int audioFile){
      mDefaultTranslation = defaultTranslation ;
      mMarathiTranslation = marathiTranslation ;
      Object = getImage;
      mAudioFile  = audioFile;

    }

    public String getDefaultTranslation(){

        return mDefaultTranslation;
    }

    public String getmarathiTranslation(){

        return mMarathiTranslation;
    }

    public int getObject(){
        return Object;
    }


    public boolean hasImage(){
        return Object != NO_IMAGE;
    }

    public int getmAudioFile(){
        return mAudioFile;
    }



}
