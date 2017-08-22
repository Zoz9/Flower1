package com.example.zoz.flower1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Zoz on 21.01.2017.
 */

public class FlowerEditor extends Activity implements View.OnClickListener {

    SQLiteDatabase db;
    String flowerBD;
    String id_Flower;
    BDSupport bdSupport;
    EditText eTnameFlower;
    EditText eTsizeFlower;
    EditText eTmanufacturerFlower;
    EditText eTcollectorFlowerFlower;
    EditText eTfeaturesFlower;
    String[] data = {"Листик", "Детка", "Стартер", "Взрослое растение", "Не выбрано"};
    ContentValues cv;
    Spinner spinner;
    String growthStageFlower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flower_edit);

        Intent intent = getIntent();

        flowerBD = intent.getStringExtra("bdname");
        id_Flower = intent.getStringExtra("id_Flower");
        Log.d("LOGN", " id = " + id_Flower);
        bdSupport = new BDSupport(this,flowerBD,1);
        db = bdSupport.getReadableDatabase();

        Button butSaveFl;
        butSaveFl = (Button)findViewById(R.id.butSaveFl);
        butSaveFl.setOnClickListener(this);

        Button drop;
        drop = (Button) findViewById(R.id.drop);
        drop.setOnClickListener(this);

        eTnameFlower = (EditText) findViewById(R.id.eTnameFlower);
        eTsizeFlower = (EditText) findViewById(R.id.eTsizeFlower);
        eTmanufacturerFlower = (EditText) findViewById(R.id.eTmanufacturerFlower);
        eTcollectorFlowerFlower = (EditText) findViewById(R.id.eTcollectorFlowerFlower);
        eTfeaturesFlower = (EditText) findViewById(R.id.eTfeaturesFlower);


        // адаптер выпадающего меню
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = (Spinner) findViewById(R.id.lWgrowthStageFlower);
        spinner.setAdapter(adapter);
        // заголовок
        spinner.setPrompt("Title");
        // выделяем элемент
        spinner.setSelection(4);
        // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        if(id_Flower!=null){
            Cursor c = db.query(flowerBD, null, "id="+id_Flower, null, null, null, null);
            if (c.moveToFirst()) {
                Log.d("LOGN", " id = " + c.getString(c.getColumnIndex("nameFlower")));
                eTnameFlower.setText(c.getString(c.getColumnIndex("nameFlower")));
                eTsizeFlower.setText(c.getString(c.getColumnIndex("sizeFlower")));
                eTmanufacturerFlower.setText(c.getString(c.getColumnIndex("manufacturerFlower")));
                eTcollectorFlowerFlower.setText(c.getString(c.getColumnIndex("collectorFlower")));
                eTfeaturesFlower.setText(c.getString(c.getColumnIndex("featuresFlower")));
                spinner.setSelection(c.getInt(c.getColumnIndex("growthStageFlower")));
            }
        }

        bdSupport.close();

    }


    @Override
    public void onClick(View v) {
        db = bdSupport.getReadableDatabase();
        switch (v.getId()) {
            case R.id.butSaveFl:
                cv = new ContentValues();
//nameFlower TEXT, sizeFlower INTEGER, growthStageFlower TEXT, manufacturerFlower TEXT, collectorFlower TEXT, featuresFlower TEXT);"
                String nameFlower = eTnameFlower.getText().toString();
                cv.put("nameFlower", nameFlower);
                int sizeFlower = Integer.parseInt(eTsizeFlower.getText().toString());
                cv.put("sizeFlower", sizeFlower);
                String growthStageFlower = spinner.getSelectedItemPosition()+"";
                cv.put("growthStageFlower", growthStageFlower);
                String manufacturerFlower = eTmanufacturerFlower.getText().toString();
                cv.put("manufacturerFlower", manufacturerFlower);
                String collectorFlower = eTcollectorFlowerFlower.getText().toString();
                cv.put("collectorFlower", collectorFlower);
                String featuresFlower = eTfeaturesFlower.getText().toString();
                cv.put("featuresFlower", featuresFlower);

                if(id_Flower!=null){
                    db.update(flowerBD,cv,"id = ?",new String[]{id_Flower});
                }
                else {
                    long rowID = db.insert(flowerBD, null, cv);
                    Log.d("TAGN", "row inserted, ID = " + rowID);
                }
                break;

            case R.id.drop:

                db.execSQL("DROP TABLE IF EXISTS flowersstandartdb");
                //db.delete("flowersstandartdb", null, null);
                db.execSQL("CREATE TABLE flowersstandartdb (id INTEGER PRIMARY KEY autoincrement, nameFlower TEXT, sizeFlower INTEGER, growthStageFlower TEXT, manufacturerFlower TEXT, collectorFlower TEXT, featuresFlower TEXT);");
                break;

        }
        bdSupport.close();
    }
}
