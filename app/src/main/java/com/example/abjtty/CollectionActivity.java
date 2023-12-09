package com.example.abjtty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        collectionRvAdapter = new CollectionRvAdapter(collectionArrayList, CollectionActivity.this, this);
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

        Button exitButton = findViewById(R.id.exitBtn);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CollectionActivity.this, "You pressed EXIT!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRecordClick(int position) {
        //DELETE
        db = collectionOpenHelper.getWritableDatabase();
        /*Cursor cursor = db.query(
                false,
                CollectionContract.TABLE_NAME
                );
        while (cursor.moveToNext()){

        }*/
        Toast.makeText(this, String.valueOf(CollectionContract.CollectionEntry._ID), Toast.LENGTH_SHORT).show(); //_id értéket ad vissza ez nem jó megoldás xd
        long delete = db.delete(CollectionContract.TABLE_NAME, "_id="+ CollectionContract.CollectionEntry._ID, null);
        if (delete != -1) {
            Toast.makeText(this, "ROW DELETED!", Toast.LENGTH_SHORT).show();
            collectionArrayList.remove(position);
        }
        /*String selection = CollectionContract.CollectionEntry._ID + "=?";
        String[] selectionArgs = new String[] {  };
        int deleted = db.delete(
                CollectionContract.TABLE_NAME,
                selection,
                selectionArgs);
        //Toast.makeText(this, "Deleted: " + deleted, Toast.LENGTH_SHORT).show();*/
        Intent intent = new Intent(CollectionActivity.this, MainActivity.class);
        CollectionActivity.this.startActivity(intent);
    }
}