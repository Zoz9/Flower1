package com.example.zoz.flower1;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.EditText;
import android.widget.ListView;

import com.example.zoz.flower1.Dialogs.*;
import com.example.zoz.flower1.Dialogs.DatePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PageFragmentFlowerEditorWatering extends Fragment implements View.OnClickListener, DatePicker.ShareDialogListener {

    TextView dateLastWatering;
    TextView datePeriodWatering;
    TextView dateLastGround;
    ListView listOfdateWatering;
    TextView TT;
    String id_Flower;
    SQLiteDatabase db;
    BDSupport bdSupport;
    ContentValues cv;
    View cvv;
    CalendarView cvv2;
    DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", Locale.US);
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.dateLastWatering:
                PickerDialog(v);
                break;
            case R.id.datePeriodWatering:
                NumPickerDialog(v);
                break;
            case R.id.dateLastGround:
                PickerDialog(v);
                break;
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_watering_edit, container, false);
        Intent intent = getActivity().getIntent();

        cvv = view.findViewById(R.id.calendar_view);
        cvv2 = (CalendarView) view.findViewById(R.id.calendar_view);
        //cvv2 = ((CalendarView) view.findViewById(R.id.calendar_view));
        //  CalendarView cvv = ((CalendarView)findViewById(R.id.calendar_view));
        // В фрагменте  View cvv = view.findViewById(R.id.calendar_view);
        dateLastWatering = (TextView) view.findViewById(R.id.dateLastWatering);
        dateLastWatering.setOnClickListener(this);
        datePeriodWatering = (TextView) view.findViewById(R.id.datePeriodWatering);
        datePeriodWatering.setOnClickListener(this);
        dateLastGround = (TextView) view.findViewById(R.id.dateLastGround);
        dateLastGround.setOnClickListener(this);
        listOfdateWatering = (ListView) view.findViewById(R.id.listOfdateWatering);
        id_Flower = intent.getStringExtra("id_Flower");
        bdSupport = new BDSupport(getActivity(),"wateringdb",1);
        db = bdSupport.getReadableDatabase();
        if(id_Flower!=null){
            Cursor c = db.query("wateringdb", null, "idFlower="+id_Flower, null, null, null, null);
            if (c.moveToFirst()) {

                dateLastWatering.setText(c.getString(c.getColumnIndex("dateLastWatering")));
                datePeriodWatering.setText(c.getString(c.getColumnIndex("datePeriodWatering")));
                dateLastGround.setText(c.getString(c.getColumnIndex("dateLastGround")));
            }
        }
        bdSupport.close();
        return view;

    }
    public void CalendarSetDay(int days){

    }

    public void PickerDialog(View v) {
        DialogFragment dateDialog = new DatePicker();
        dateDialog.show(getFragmentManager(), "datePicker");
        // в фрагменте надо юзать getFragmentManager вместо getSupportFragmentManager
        TT =  (TextView)getActivity().findViewById(v.getId());

    }
    @Override
    public void onDialogPositiveClick(DialogFragment dialog, int year, int month, int day) throws ParseException {

        //Toast.makeText(this, "Position Shared " + year + " " + month + " "+ day, Toast.LENGTH_SHORT).show();
        TextSet(day+"."+month+"."+year);
    }
    public void TextSet(String s){
        TT.setText(s);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    public String PickeDateNextWatering(String dateLastWatering, String datePeriodWatering)  {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            c.setTime(sdf.parse(dateLastWatering));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, Integer.parseInt(datePeriodWatering));
        try {
            date = sdf.parse(c.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String Dc = sdf.format(c.getTime()).toString();
        Log.d("LogN", Dc);
        return Dc;
    }


    public void saveWatering(int ids) {
        bdSupport = new BDSupport(getActivity(),"wateringdb",1);
        db = bdSupport.getReadableDatabase();
        cv = new ContentValues();
        String dateLastWateringT = dateLastWatering.getText().toString();
        cv.put("dateLastWatering", dateLastWateringT);
        String dateLastGroundT = dateLastGround.getText().toString();
        cv.put("dateLastGround", dateLastGroundT);
        String datePeriodWateringT = datePeriodWatering.getText().toString();
        cv.put("datePeriodWatering", datePeriodWateringT);
        cv.put("dateNextWatering",PickeDateNextWatering(dateLastWateringT,datePeriodWateringT));
        if(id_Flower!=null){
            db.update("wateringdb",cv,"idFlower = ?",new String[]{id_Flower});
            //fragment.SaveGrond(Integer.parseInt(id_Flower));
            Log.d("LOGN",id_Flower + dateLastWateringT+dateLastGroundT+datePeriodWateringT);
        }
        else {
            cv.put("idFlower",ids);
            long rowID = db.insert("wateringdb", null, cv);
            //Log.d("TAGN", "row inserted, ID = " + rowID);
            //fragment.SaveGrond((int)rowID);
            Log.d("LOGN",""+ids + dateLastWateringT+dateLastGroundT+datePeriodWateringT);
        }
        bdSupport.close();
    }

    public void NumPickerDialog(View v)
    {
        NumPicke numberDialog = new NumPicke();
        numberDialog.show(getFragmentManager(), "datePicker");
        // в фрагменте надо юзать getFragmentManager вместо getSupportFragmentManager
        TT =  (TextView)getActivity().findViewById(v.getId());
    }



}
