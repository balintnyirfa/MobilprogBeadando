package com.example.abjtty;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.openBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CollectionActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button openWebsiteBtn = findViewById(R.id.openWebsiteBtn);
        openWebsiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebsiteOnClick();
            }
        });
    }

    public void openWebsiteOnClick() {
        Uri url = Uri.parse("https://discogs.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, url);
        MainActivity.this.startActivity(intent);
    }
}