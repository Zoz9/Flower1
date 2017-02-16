package com.example.zoz.flower1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zoz on 21.01.2017.
 */

public class BDSupport extends SQLiteOpenHelper {
    public BDSupport(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE flowersstandartdb (id INTEGER PRIMARY KEY, nameFlower TEXT, sizeFlower INTEGER, growthStageFlower TEXT, manufacturerFlower TEXT, collectorFlower TEXT, featuresFlower TEXT);");
        db.execSQL("CREATE TABLE flowersminidb (id INTEGER PRIMARY KEY, nameFlower TEXT, sizeFlower INTEGER, growthStageFlower TEXT, manufacturerFlower TEXT, collectorFlower TEXT, featuresFlower TEXT);");
        db.execSQL("CREATE TABLE flowerstrailerdb (id INTEGER PRIMARY KEY, nameFlower TEXT, sizeFlower INTEGER, growthStageFlower TEXT, manufacturerFlower TEXT, collectorFlower TEXT, featuresFlower TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
