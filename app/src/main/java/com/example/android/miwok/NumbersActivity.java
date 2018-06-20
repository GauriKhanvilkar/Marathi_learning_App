package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class NumbersActivity extends AppCompatActivity {

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

        maudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE); //get Audio maneger

       // String[] words = {"one","two","three","four","five","six","seven","eight","nine","ten"};
        final ArrayList<Word> words = new ArrayList<Word>();//final so that we can use in setOnItemClickListener

        Word w = new Word("one","ek",R.drawable.number_one,R.raw.one);
        words.add(w); //one method for adding numbers

        words.add(new Word("two","don",R.drawable.number_two,R.raw.two));//second methog of adding words
        words.add(new Word("tree","tin",R.drawable.number_three,R.raw.three));
        words.add(new Word("four","char",R.drawable.number_four,R.raw.four));
        words.add(new Word("five","pach",R.drawable.number_five,R.raw.five));
        words.add(new Word("six","saha",R.drawable.number_six,R.raw.six));
        words.add(new Word("seven","sath",R.drawable.number_seven,R.raw.seven));
        words.add(new Word("eight","ath",R.drawable.number_eight,R.raw.eight));
        words.add(new Word("nine","nau",R.drawable.number_nine,R.raw.nine));
        words.add(new Word("ten","daha",R.drawable.number_ten,R.raw.ten));

//        LinearLayout rootView = (LinearLayout)findViewById(R.id.rootView); //normal list view but having more memory use
//
//        for(int i = 0 ; i< 10 ; i++) {
//            TextView  wordView = new TextView(this); //this is passes as input
//            ( wordView).setText(words.get(i));
//            rootView.addView(wordView);
//        }

//for less memory use ... use Array Adapter
//android predefind         simple_list_item_1
      //  ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<Word>(this, R.layout.list_item, words);

        WordAdapter adapter = new WordAdapter(this,words,R.color.category_numbers);

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

                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, w.getmAudioFile()); // or view.getContext()
                    mediaPlayer.start();


                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { //pop up the mess when song playing is done ...asyn call back ...calls when done
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            Toast.makeText(NumbersActivity.this, "I am done playing", Toast.LENGTH_SHORT).show();
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



    /*public static class numberClickListener implements View.OnClickListener{//seperate call
        @Override
        public void onClick(View view){
            Toast.makeText(view.getContext(),"open numbers list",Toast.LENGTH_SHORT).show();

        }

    }*/

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
