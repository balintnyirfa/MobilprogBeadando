package com.example.abjtty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExpandCollection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_collection);

        CollectionOpenHelper collectionOpenHelper = new CollectionOpenHelper(this);
        SQLiteDatabase db = collectionOpenHelper.getWritableDatabase();

        Button addToCollection = findViewById(R.id.addToCollectionBtn);
        Button back = findViewById(R.id.backBtn);

        EditText artist = findViewById(R.id.artistText);
        EditText title = findViewById(R.id.titleText);
        EditText format = findViewById(R.id.formatText);
        EditText value = findViewById(R.id.valueNumber);

        addToCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Adatbázis bővítése (CREATE)
                ContentValues values = new ContentValues();
                values.put(CollectionContract.CollectionEntry.COLUMN_NAME_ARTIST, artist.getText().toString());
                values.put(CollectionContract.CollectionEntry.COLUMN_NAME_TITLE, title.getText().toString());
                values.put(CollectionContract.CollectionEntry.COLUMN_NAME_FORMAT, format.getText().toString());
                values.put(CollectionContract.CollectionEntry.COLUMN_NAME_VALUE, value.getText().toString());
                db.insert(CollectionContract.TABLE_NAME, null, values);
                db.close();
                Toast.makeText(ExpandCollection.this, "Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ExpandCollection.this, CollectionActivity.class);
                ExpandCollection.this.startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}