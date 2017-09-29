package com.example.zoz.flower1;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    //Создаем список вьюх которые будут создаваться
    private List<View> allEds;
    //счетчик чисто декоративный для визуального отображения edittext'ov
    private int counter = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonAddEdittext:

                counter++;

                //берем наш кастомный лейаут находим через него все наши кнопки и едит тексты, задаем нужные данные
                final View view = LayoutInflater.from(getActivity()).inflate(R.layout.custom_edittext_layout, null);
                Button deleteField = (Button) view.findViewById(R.id.buttonDelEdittext);
                EditText text = (EditText) view.findViewById(R.id.editText);
                text.setText("Some text" + counter);
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

                break;
        }
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
        //создаем вьюху View view = inflater.inflate(R.layout.fragment_flower_edit, container, false) вместо  setContentView(R.layout.fragment_flower_edit);
        linear = (LinearLayout) view.findViewById(R.id.linear);
        addButton = (Button) view.findViewById(R.id.buttonAddEdittext);
        addButton.setOnClickListener(this);
        return view;

    }
}