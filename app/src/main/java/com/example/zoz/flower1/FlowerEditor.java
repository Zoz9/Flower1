package com.example.zoz.flower1;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Zoz on 21.01.2017.
 */

public class FlowerEditor extends Activity implements View.OnClickListener {

    SQLiteDatabase db;
    String flowerBD;
    BDSupport bdSupport;
    EditText eTnameFlower;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flower_edit);

        Intent intent = getIntent();

        String flowerBD = intent.getStringExtra("bdname");
        bdSupport = new BDSupport(this,flowerBD,1);
        db = bdSupport.getReadableDatabase();

        Button butSaveFl;
        butSaveFl = (Button)findViewById(R.id.butSaveFl);
        butSaveFl.setOnClickListener(this);


        eTnameFlower = (EditText) findViewById(R.id.eTnameFlower);



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.butSaveFl:
                String nameFl = eTnameFlower.getText().toString();
                Log.d("wppppppp",nameFl);


        }
    }
}
