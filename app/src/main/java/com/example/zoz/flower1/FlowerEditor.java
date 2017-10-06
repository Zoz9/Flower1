package com.example.zoz.flower1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.List;

import static com.example.zoz.flower1.R.id.eTnameFlower;
import static com.example.zoz.flower1.R.id.eTsizeFlower;

/**
 * Created by Zoz on 21.01.2017.
 */

public class FlowerEditor extends FragmentActivity{

    static final String TAG = "myLogs";
    static final int PAGE_COUNT = 3;

    /** идентификатор первого фрагмента. */
    public static final int FRAGMENT_ONE = 0;
    /** идентификатор третего. */
    public static final int FRAGMENT_THREE = 2;
    /** идентификатор второго. */
    public static final int FRAGMENT_TWO = 1;
    /** количество фрагментов. */
    public static final int FRAGMENTS = 3;
    /** адаптер фрагментов. */
    private FragmentPagerAdapter _fragmentPagerAdapter;
    /** список фрагментов для отображения. */
    private final List<Fragment> _fragments = new ArrayList<Fragment>();
    /** сам ViewPager который будет все это отображать. */
    private ViewPager _viewPager;
    SQLiteDatabase db;
    ViewPager pager;
    String flowerBD;
    String id_Flower;
    BDSupport bdSupport;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flower_edit);

        // создаем фрагменты.
        _fragments.add(FRAGMENT_ONE, new PageFragmentFlowerEditorMain());
        _fragments.add(FRAGMENT_TWO, new PageFragmentFlowerEditorGround());
        _fragments.add(FRAGMENT_THREE, new PageFragmentFlowerEditorMain());
        Intent intent = getIntent();
        // в фрагменте для интента надо юзать getActivity()

        flowerBD = intent.getStringExtra("bdname");
        id_Flower = intent.getStringExtra("id_Flower");

        pager = (ViewPager) findViewById(R.id.pager);
        _fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return FRAGMENTS;
            }


            @Override
            public Fragment getItem(int position) {
                return _fragments.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "Title " + position;
                // заголовки страниц
            }
        };
        pager.setAdapter(_fragmentPagerAdapter);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected, position = " + position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void onClicSaveF(View view) {
        bdSupport = new BDSupport(this,flowerBD,1);
        db = bdSupport.getReadableDatabase();
        ContentValues cv = new ContentValues();
        EditText eTnameFlower = (EditText) findViewById(R.id.eTnameFlower);
        EditText eTsizeFlower = (EditText) findViewById(R.id.eTsizeFlower);
        EditText eTmanufacturerFlower = (EditText) findViewById(R.id.eTmanufacturerFlower);
        EditText eTcollectorFlowerFlower = (EditText) findViewById(R.id.eTcollectorFlowerFlower);
        EditText eTfeaturesFlower = (EditText) findViewById(R.id.eTfeaturesFlower);
        Spinner spinner = (Spinner) findViewById(R.id.lWgrowthStageFlower);

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

        PageFragmentFlowerEditorGround fragment = (PageFragmentFlowerEditorGround) _fragments.get(FRAGMENT_TWO);

        if(id_Flower!=null){
            db.update(flowerBD,cv,"id = ?",new String[]{id_Flower});
            fragment.SaveGrond(Integer.parseInt(id_Flower));
        }
        else {
            long rowID = db.insert(flowerBD, null, cv);
            Log.d("TAGN", "row inserted, ID = " + rowID);
            fragment.SaveGrond((int)rowID);

        }
        bdSupport.close();
    }
}