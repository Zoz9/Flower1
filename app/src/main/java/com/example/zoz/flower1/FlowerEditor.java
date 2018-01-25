package com.example.zoz.flower1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
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
import android.widget.TextView;


import com.example.zoz.flower1.Dialogs.DatePicker;
import com.example.zoz.flower1.Dialogs.NumPicke;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.example.zoz.flower1.R.id.eTnameFlower;
import static com.example.zoz.flower1.R.id.eTsizeFlower;



public class FlowerEditor extends FragmentActivity implements DatePicker.ShareDialogListener, NumPicke.ShareNumberDialogListener{

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
        _fragments.add(FRAGMENT_THREE, new PageFragmentFlowerEditorWatering());
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
        pager.setOffscreenPageLimit(3);
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

    public void onClicSaveF(View view) throws ParseException {
        PageFragmentFlowerEditorWatering fragment2 = (PageFragmentFlowerEditorWatering) _fragments.get(FRAGMENT_THREE);
        PageFragmentFlowerEditorGround fragment = (PageFragmentFlowerEditorGround) _fragments.get(FRAGMENT_TWO);
        PageFragmentFlowerEditorMain fragment1 = (PageFragmentFlowerEditorMain) _fragments.get(FRAGMENT_ONE);

        if(id_Flower!=null){
            fragment1.saveMain();
            fragment.SaveGrond(Integer.parseInt(id_Flower));
            fragment2.saveWatering(Integer.parseInt(id_Flower));
        }
        else {
            long rowID = fragment1.saveMain();
            Log.d("TAGN", "row inserted, ID = " + rowID);
            fragment.SaveGrond((int)rowID);
            fragment2.saveWatering((int)rowID);

        }

    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, int year, int month, int day) {
        PageFragmentFlowerEditorWatering fragment2 = (PageFragmentFlowerEditorWatering) _fragments.get(FRAGMENT_THREE);
        month = month+1;
        fragment2.TextSet(day+"."+month+"."+year);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    public void onNumberDialogPositiveClick(DialogInterface dialog, int num) {
        PageFragmentFlowerEditorWatering fragment2 = (PageFragmentFlowerEditorWatering) _fragments.get(FRAGMENT_THREE);
        fragment2.TextSet(Integer.toString(num));
    }

    @Override
    public void onNumberDialogNegativeClick(DialogInterface dialog, int which) {

    }
}