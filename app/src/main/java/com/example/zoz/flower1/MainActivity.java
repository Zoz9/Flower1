package com.example.zoz.flower1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.zoz.flower1.CalendarW.CalendarActivity;
import com.example.zoz.flower1.Services.MyServiceTest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button standart_Fl_B, mini_Fl_B, trailer_Fl_B, calendar_button_b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, MyServiceTest.class));
        standart_Fl_B = (Button) findViewById(R.id.standart_button);
        standart_Fl_B.setOnClickListener(this);

        mini_Fl_B = (Button) findViewById(R.id.mini_button);
        mini_Fl_B.setOnClickListener(this);

        trailer_Fl_B = (Button) findViewById(R.id.trailer_button);
        trailer_Fl_B.setOnClickListener(this);

        calendar_button_b = (Button) findViewById(R.id.calendar_button);
        calendar_button_b.setOnClickListener(this);
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
                intent = new Intent(this, ListOfFlower.class);
                intent.putExtra("bdname", "flowersminidb");
                startActivity(intent);
                break;

            case R.id.trailer_button:
                intent = new Intent(this, ListOfFlower.class);
                intent.putExtra("bdname", "flowerstrailerdb");
                startActivity(intent);
                break;

            case R.id.calendar_button:
                intent = new Intent(this, CalendarActivity.class);
                intent.putExtra("bdname", "wateringdb");
                startActivity(intent);

                break;

        }
    }



}
