package com.example.zoz.flower1;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

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

    ViewPager pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flower_edit);

        // создаем фрагменты.
        _fragments.add(FRAGMENT_ONE, new PageFragmentFlowerEditorMain());
        _fragments.add(FRAGMENT_TWO, new PageFragmentFlowerEditorGround());
        _fragments.add(FRAGMENT_THREE, new PageFragmentFlowerEditorMain());

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
/*
    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return PageFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

    }*/

}