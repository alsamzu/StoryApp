package com.example.zulfa.storyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zulfa.storyapp.R;
import com.example.zulfa.storyapp.model.Page;
import com.example.zulfa.storyapp.model.Story;

import java.util.Stack;

public class StoryActivity extends AppCompatActivity {
    public static final String TAG = StoryActivity.class.getSimpleName();
    private String name;
    private Story story;
    private ImageView storyImageView;
    private TextView storyTextView;
    private Button choice1Button;
    private Button choice2Button;
    private Stack<Integer> pagestack = new Stack<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);


        storyImageView = (ImageView) findViewById(R.id.storyImageView);
        storyTextView = (TextView) findViewById(R.id.storyTextView);
        choice1Button = (Button) findViewById(R.id.choice1Button);
        choice2Button = (Button) findViewById(R.id.choice2Button);


        Intent intent = getIntent();
        name =  intent.getStringExtra(getString(R.string.key_name));
       if (name == null || name.isEmpty()){
           name = "Bestie";
       }
        Log.d(TAG,name);

       story = new Story();
       loadPage(0);

    }

    private void loadPage(int pageNumber) {

        pagestack.push(pageNumber);
        final Page  page = story.getPage(pageNumber);
        Drawable image = ContextCompat.getDrawable(this,page.getImageId());
        storyImageView.setImageDrawable(image);

        String pageText = getString(page.getTextId());
        pageText = String.format(pageText,name);

        //ADD NAME IF PLACEHOLDER IS INCLUDED WONT ADD IF NOT
        storyTextView.setText(pageText);

        if (page.isFinalPage()){

            choice1Button.setVisibility(View.INVISIBLE);
            choice2Button.setText(getString(R.string.play_again_button_text));
            choice2Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //finish();
                    loadPage(0);
                }
            });
        }
        else {
            loadButtons(page);

        }



    }

    private void loadButtons(final Page page) {
        choice1Button.setVisibility(View.VISIBLE);
        choice1Button.setText(page.getChoice1().getTextId());

        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nextPage = page.getChoice1().getNextPage();
                loadPage(nextPage);
            }
        });

        choice2Button.setVisibility(View.VISIBLE);
        choice2Button.setText(page.getChoice2().getTextId());

        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nextPage = page.getChoice2().getNextPage();
                loadPage(nextPage);
            }
        });
    }

    @Override
    public void onBackPressed() {
        pagestack.pop();
        if (pagestack.isEmpty()){

            super.onBackPressed();
        }
        else{
            loadPage(pagestack.pop());
        }

    }
}
