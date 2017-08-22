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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;

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
    ListView ListOF;

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
        ListOF = (ListView) findViewById(R.id.mainListOfFlower);
        ListOF.setAdapter(fAdapterList);

        //выбор необходимого пункта
        ListOF.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String id_Flower =((TextView) view.findViewById(R.id.tvIdFl)).getText().toString();
                Log.d("LOGN", "itemClick: position = " + position + ", id = "
                        + id_Flower);
                Intent intent = new Intent(getApplicationContext(), FlowerEditor.class);
                intent.putExtra("bdname", flowerBD+"");
                intent.putExtra("id_Flower", id_Flower+"");
                startActivity(intent);


            }
        });

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
