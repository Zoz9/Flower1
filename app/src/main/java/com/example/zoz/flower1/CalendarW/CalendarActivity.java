package com.example.zoz.flower1.CalendarW;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zoz.flower1.BDSupport;
import com.example.zoz.flower1.CatalogFlower;
import com.example.zoz.flower1.FAdapterList;
import com.example.zoz.flower1.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;


public class CalendarActivity extends ActionBarActivity
{
    SQLiteDatabase db;
    BDSupport bdSupport;
    Date dt;
    DateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    DateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
    ArrayList<CatalogFlower> myOjctFlower = new ArrayList<CatalogFlower>();
    FAdapterList fAdapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        HashSet<Date> events = new HashSet<>();
        events.add(new Date());
        //bd data
        bdSupport = new BDSupport(this,"wateringdb",1);
        db = bdSupport.getReadableDatabase();
        ContentValues values = new ContentValues();
        //values.put("nameFlower","27/08/2017");
        /*
        try {
            for(int a=10;a<20;a++) {
                dt = sdf.parse(a + ".08.2017");
                values.put("nameFlower", String.valueOf(dt));
                //db.insert("flowersstandartdb", null, values);
            }

            dt=null;


        } catch (ParseException e) {
            e.printStackTrace();
        }
        // тест загрузки дат в календапь - устарело
        //db.insert("flowersstandartdb",null,values);
        */
        //bd date ---
        DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", Locale.US);//SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.LONG, SimpleDateFormat.LONG);;
        Cursor c = db.query("wateringdb", null, null, null, null, null, null);
        fAdapterList = new FAdapterList(this,myOjctFlower);

        if (c.moveToFirst()){
            int idColIndex = c.getColumnIndex("id");
            int dateNextWateringColIndex = c.getColumnIndex("dateNextWatering");
            Log.d("TAGN", "rc.getString(nameColIndex) = " + dateNextWateringColIndex);

            do{

                try {
                    Log.d("TAGN", "c.getString(nameColIndex)) = " + c.getString(dateNextWateringColIndex));
                    //Log.d("TAGN", "sdf.parse(c.getString(nameColIndex)) = " + (Date)df.parse(c.getString(nameColIndex)));
                    String[] p = c.getString(dateNextWateringColIndex).split(" ");
                    dt = sdf2.parse(c.getString(dateNextWateringColIndex));//p[0]+" "+p[1]+" "+p[2]+" "+p[5]);
                    myOjctFlower.add(new CatalogFlower(p[0],c.getInt(idColIndex), "flowersstandartdb"));
                            //p[0]+" "+p[1]+" "+p[2]+" "+p[5], c.getInt(idColIndex), "flowersstandartdb"));
                    events.add(dt);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(dt!=null)
                    events.add(dt);
            }while(c.moveToNext());
            ListView ListOF = (ListView) findViewById(R.id.mainListOfFlower);
            ListOF.setAdapter(fAdapterList);
        }
        else {c.close();}
        //event add
        CalendarView cv = ((CalendarView)findViewById(R.id.calendar_view));
        cv.updateCalendar(events);

        // assign event handler
        cv.setEventHandler(new CalendarView.EventHandler()
        {
            @Override
            public void onDayLongPress(Date date)
            {
                // show returned day
                DateFormat df = SimpleDateFormat.getDateInstance();
                Toast.makeText(CalendarActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
            }
        });
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
}