package com.example.zulfa.storyapp.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zulfa.storyapp.R;

public class MainActivity extends AppCompatActivity {
    private Button startbutton;
    private EditText startext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startext = (EditText) findViewById(R.id.startText);
        startbutton = (Button) findViewById(R.id.startButton);
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = startext.getText().toString();
               startStory(name);
            }
        });
    }



    private void startStory(String name) {
        Intent intent = new Intent(this,StoryActivity.class);
        Resources resources = getResources();
        String key = resources.getString(R.string.key_name);
        intent.putExtra(key,name);
        startActivity(intent);

    }
}
