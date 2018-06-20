/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

       // NumbersActivity.numberClickListener clickListener = new NumbersActivity.numberClickListener();

        TextView numbers =(TextView)findViewById(R.id.numbers);
        numbers.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {

                // Start NewActivity.class
                Toast.makeText(view.getContext(),"open numbers list",Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MainActivity.this,
                        NumbersActivity.class);
                startActivity(myIntent);
            }
        });

        TextView colors =(TextView)findViewById(R.id.colors);
        colors.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {

                // Start NewActivity.class
                Toast.makeText(view.getContext(),"open colors list",Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MainActivity.this,
                        ColorActivity.class);
                startActivity(myIntent);
            }
        });

        TextView phrases =(TextView)findViewById(R.id.phrases);
        phrases.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {

                // Start NewActivity.class
                Toast.makeText(view.getContext(),"open phrases list",Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MainActivity.this,
                        PhrasesActivity.class);
                startActivity(myIntent);
            }
        });

        TextView family =(TextView)findViewById(R.id.family);
        family.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {

                // Start NewActivity.class
                Toast.makeText(view.getContext(),"open family list",Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MainActivity.this,
                        FamilyActivity.class);
                startActivity(myIntent);
            }
        });


    }

    public void openNumberActivity(View view) {
        Intent i = new Intent(this,NumbersActivity.class);
        startActivity(i);

    }
    public void openColorActivity(View view) {
        Intent i = new Intent(this,ColorActivity.class);
        startActivity(i);

    }
    public void openFamilyActivity(View view) {
        Intent i = new Intent(this,FamilyActivity.class);
        startActivity(i);

    }
    public void openPhrasesActivity(View view) {
        Intent i = new Intent(this,PhrasesActivity.class);
        startActivity(i);

    }
}
