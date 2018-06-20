package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gauri on 29-04-2018.
 */

public class WordAdapter extends ArrayAdapter<Word>{
    private int mColorID;

    public WordAdapter(@NonNull Activity context, ArrayList<Word> words, int colorID) {
        super(context, 0,words);
        mColorID = colorID;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Word currentPosition = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_textView);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        defaultTextView.setText(currentPosition.getDefaultTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView marathiTextView = (TextView) listItemView.findViewById(R.id.marathi_textView);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        marathiTextView.setText(currentPosition.getmarathiTranslation());

        ImageView setImage = (ImageView) listItemView.findViewById(R.id.ImageView);
        if(currentPosition.hasImage()) {
            setImage.setImageResource(currentPosition.getObject());
            setImage.setVisibility(View.VISIBLE);
        }
        else{
            setImage.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(),mColorID);
        textContainer.setBackgroundColor(color);


//        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
//        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);
//        // Get the image resource ID from the current AndroidFlavor object and
//        // set the image to iconView
//        iconView.setImageResource(currentAndroidFlavor.getImageResourceId());

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
