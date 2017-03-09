package com.example.zoz.flower1;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Zoz on 28.01.2017.
 */

public class ListOfFlower extends AppCompatActivity implements View.OnClickListener {

    BDSupport bdSupport;
    ArrayList<CatalogFlower> myOjctFlower = new ArrayList<CatalogFlower>();
    FAdapterList fAdapterList;
    String flowerBD;
    SQLiteDatabase db;
    Button buttonCreateFlower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_flwer);

        Intent intent = getIntent();

        flowerBD = intent.getStringExtra("bdname");
        bdSupport = new BDSupport(this,flowerBD,1);
        db = bdSupport.getReadableDatabase();
        buttonCreateFlower = (Button) findViewById(R.id.buttonCreateFlower);
        buttonCreateFlower.setOnClickListener(this);
        fAdapterList = new FAdapterList(this,myOjctFlower);

        Cursor c = db.query(flowerBD, null, null, null, null, null, null);



        if (c.moveToFirst()){
            int idColIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("nameFlower");
            Log.d("TAGN", "rc.getString(nameColIndex) = " + c.getString(nameColIndex));
            Log.d("TAGN", "c.getInt(idColIndex) = " + c.getInt(idColIndex));
            do{
                myOjctFlower.add(new CatalogFlower(c.getString(nameColIndex), c.getInt(idColIndex), flowerBD));
            }while(c.moveToNext());
        }
        else {c.close();}

        ListView ListOF = (ListView) findViewById(R.id.mainListOfFlower);
        ListOF.setAdapter(fAdapterList);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonCreateFlower:
                Intent intent = new Intent(this, FlowerEditor.class);
                intent.putExtra("bdname", flowerBD+"");
                startActivity(intent);


        }
    }
}
