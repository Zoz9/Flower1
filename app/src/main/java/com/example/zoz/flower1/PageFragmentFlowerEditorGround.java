package com.example.zoz.flower1;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class PageFragmentFlowerEditorGround extends Fragment implements View.OnClickListener {
    private Context context;
    Button addButton;
    Button delButton;
    //находим наш linear который у нас под кнопкой add edittext в activity_main.xml
    LinearLayout linear;
    String id_Flower;
    SQLiteDatabase db;
    BDSupport bdSupport;
    ContentValues cv;

    //Создаем список вьюх которые будут создаваться
    private List<View> allEds;
    //счетчик чисто декоративный для визуального отображения edittext'ov
    private int counter = 0;

/*
    FragmentListener mListener;
// доступ кметоду активити через фрагмент
    public interface FragmentListener {
        public void setAllEdsMain(List<View> allEdsMain);
    }
*/

    public void SaveGrond(int ids){
        //--
        db = bdSupport.getReadableDatabase();
        //преобразуем наш ArrayList в просто String Array
        String [] items = new String[allEds.size()];
        //запускаем чтение всех елементов этого списка и запись в массив
        cv = new ContentValues();
        String iemText = "";
        for(int i=0; i < allEds.size(); i++) {
            items[i] = ((EditText) allEds.get(i).findViewById(R.id.editText)).getText().toString();
            Log.e("LOGN", ((EditText) allEds.get(i).findViewById(R.id.editText)).getText().toString() + " " + ids);
            iemText = iemText + "@" + ((EditText) allEds.get(i).findViewById(R.id.editText)).getText().toString();
        }
        cv.put("grounFlower",iemText);
        if(id_Flower!=null){

            db.update("grounddb",cv,"idFlower = ?",new String[]{id_Flower});
            //fragment.SaveGrond(Integer.parseInt(id_Flower));
            Log.d("LOGN",id_Flower + iemText);
        }
        else {
            cv.put("idFlower",ids);
              long rowID = db.insert("grounddb", null, cv);
            //Log.d("TAGN", "row inserted, ID = " + rowID);
            //fragment.SaveGrond((int)rowID);
            Log.d("LOGN",""+ids + iemText);
        }

        bdSupport.close();
        //--
        Log.d("LOGN", " test ok");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonAddEdittext:
                newEdit();
                break;
        }
    }

    private void newEdit(){
        newEdit(" ");
    }
    private void newEdit(String someText){
        counter++;

        //берем наш кастомный лейаут находим через него все наши кнопки и едит тексты, задаем нужные данные
        final View view = LayoutInflater.from(getActivity()).inflate(R.layout.custom_edittext_layout, null);
        Button deleteField = (Button) view.findViewById(R.id.buttonDelEdittext);
        EditText text = (EditText) view.findViewById(R.id.editText);
        text.setText(someText);
        //добавляем все что создаем в массив

        allEds.add(view);

        //добавляем елементы в linearlayout
        linear.addView(view);
        //-----------------------------------
        deleteField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //получаем родительский view и удаляем его
                    ((LinearLayout) view.getParent()).removeView(view);
                    //удаляем эту же запись из массива что бы не оставалось мертвых записей
                    allEds.remove(view);

                } catch(IndexOutOfBoundsException ex) {
                    ex.printStackTrace();
                }
            }
        });
        //-------------------------------------
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d("TAGN", "onPause()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //инициализировали наш массив с edittext.aьи
        allEds = new ArrayList<View>();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_ground_edit, container, false);
        Intent intent = getActivity().getIntent();
        //создаем вьюху View view = inflater.inflate(R.layout.fragment_flower_edit, container, false) вместо  setContentView(R.layout.fragment_flower_edit);
        linear = (LinearLayout) view.findViewById(R.id.linear);
        addButton = (Button) view.findViewById(R.id.buttonAddEdittext);
        addButton.setOnClickListener(this);
        id_Flower = intent.getStringExtra("id_Flower");
        bdSupport = new BDSupport(getActivity(),"grounddb",1);
        db = bdSupport.getReadableDatabase();
        if(id_Flower!=null){
            Cursor c = db.query("grounddb", null, "idFlower="+id_Flower, null, null, null, null);
            if (c.moveToFirst()) {
                Log.d("LOGN", " grounFlower = " + c.getString(c.getColumnIndex("grounFlower")));
                String s = c.getString(c.getColumnIndex("grounFlower"));

                for(String ss:s.split("@")){
                    Log.d("LOGN",ss.replaceAll("@",""));
                    if(!ss.equals(" ")&&!ss.equals("")){
                    newEdit(ss.replaceAll("@",""));
                    }
                }
            }
        }
        bdSupport.close();
        return view;

    }


}