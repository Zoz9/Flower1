package com.example.zoz.flower1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button standart_Fl_B, mini_Fl_B, trailer_Fl_B;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        standart_Fl_B = (Button) findViewById(R.id.standart_button);
        standart_Fl_B.setOnClickListener(this);

        mini_Fl_B = (Button) findViewById(R.id.mini_button);
        mini_Fl_B.setOnClickListener(this);

        trailer_Fl_B = (Button) findViewById(R.id.trailer_button);
        trailer_Fl_B.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.standart_button:
            Intent intent = new Intent(this, ListOfFlower.class);
            intent.putExtra("bdname", "flowersstandartdb");
            startActivity(intent);
                break;

            case R.id.mini_button:
                intent = new Intent(this, FlowerEditor.class);
                intent.putExtra("bdname", "flowersstandartdb");
                startActivity(intent);
                break;

        }
    }


}
