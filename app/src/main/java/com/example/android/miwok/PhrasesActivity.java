package com.example.android.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private AudioManager maudioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListerner = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);

            }else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){//resume playback
                mediaPlayer.start();
            }else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){//stop playback and cleanUp
                releaseMediaPlayer();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<Word>();

        Word w = new Word("How are you?","tu kasa ahes?", R.raw.howareyou);
        words.add(w); //one method for adding numbers

        words.add(new Word("GoodMorning","subh sakal", R.raw.goodmorning));//second methog of adding words
        words.add(new Word("GoodNight","subh ratri", R.raw.goodnight));//second methog of adding words
        words.add(new Word("My Name Is.....","maze nav ahe.....", R.raw.mynameis));//second methog of adding words


        WordAdapter adapter = new WordAdapter(this,words,R.color.category_phrases);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posion, long id) {
                Toast.makeText(view.getContext(),"play",Toast.LENGTH_SHORT).show();
                releaseMediaPlayer();
                Word w = words.get(posion);

                int result = maudioManager.requestAudioFocus(mOnAudioFocusChangeListerner,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {//AUTOFOCUS IS GRANTED


                    mediaPlayer = MediaPlayer.create(PhrasesActivity.this, w.getmAudioFile()); // or view.getContext()
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { //pop up the mess when song playing is done ...asyn call back ...calls when done
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Toast.makeText(PhrasesActivity.this, "I am done playing", Toast.LENGTH_SHORT).show();
                            releaseMediaPlayer();

                        }
                    });
                }
            }
        });//listerner for list view



    }

    @Override
    protected void onStop(){ //so that to free the resorces when the app is closed ...so the song is closed imediatly
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
            maudioManager.abandonAudioFocus(mOnAudioFocusChangeListerner); //aband the audio focus once compltet
        }
    }
}
