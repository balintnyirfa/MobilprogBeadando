package com.example.abjtty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class CollectionActivity extends AppCompatActivity implements CollectionRvAdapter.OnRecordListener {
    //List<RecordCollection> collectionList = new ArrayList<RecordCollection>();

    private ArrayList<RecordCollection> collectionArrayList;
    private CollectionOpenHelper collectionOpenHelper;
    private CollectionRvAdapter collectionRvAdapter;
    private RecyclerView collectionRv;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        collectionArrayList = new ArrayList<>();
        collectionOpenHelper = new CollectionOpenHelper(this);
        collectionArrayList = collectionOpenHelper.ReadCollection();
        collectionRvAdapter = new CollectionRvAdapter(collectionArrayList, (Context) CollectionActivity.this, (CollectionRvAdapter.OnRecordListener) this);
        collectionRv = findViewById(R.id.listView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CollectionActivity.this, RecyclerView.VERTICAL, false);
        collectionRv.setLayoutManager(linearLayoutManager);
        collectionRv.setAdapter(collectionRvAdapter);

        Button addButton = findViewById(R.id.addBtn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CollectionActivity.this, ExpandCollection.class);
                CollectionActivity.this.startActivity(intent);
            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button updateBtn = findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Update
                db = collectionOpenHelper.getWritableDatabase();
                String newArtist = "NYIRFA BALINT";
                String newTitle = "MOBILPROG";
                String newFormat = "ABJTTY";
                int newValue = 5;
                ContentValues values = new ContentValues();
                values.put(CollectionContract.CollectionEntry.COLUMN_NAME_ARTIST, newArtist);
                values.put(CollectionContract.CollectionEntry.COLUMN_NAME_TITLE, newTitle);
                values.put(CollectionContract.CollectionEntry.COLUMN_NAME_FORMAT, newFormat);
                values.put(CollectionContract.CollectionEntry.COLUMN_NAME_VALUE, newValue);

                String selection = CollectionContract.CollectionEntry._ID + " LIKE ? ";
                String[] selectionArgs = new String[]{ String.valueOf(collectionArrayList.size()) };

                int updated = db.update(
                        CollectionContract.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);
            }
        });
    }

    @Override
    public void onRecordClick(int position) {
        //DELETE
        db = collectionOpenHelper.getWritableDatabase();
        //Toast.makeText(this, String.valueOf(collectionArrayList.get(position).getId()), Toast.LENGTH_SHORT).show(); //_id értéket ad vissza ez nem jó megoldás xd
        long delete = db.delete(CollectionContract.TABLE_NAME, "_id="+ collectionArrayList.get(position).getId() , null);
        //Toast.makeText(this, String.valueOf(delete), Toast.LENGTH_SHORT).show();
        if (delete != -1) {
            Toast.makeText(this, "ROW DELETED!", Toast.LENGTH_SHORT).show();
            collectionArrayList.remove(position);
        }
        Intent intent = new Intent(CollectionActivity.this, MainActivity.class);
        CollectionActivity.this.startActivity(intent);
    }
}