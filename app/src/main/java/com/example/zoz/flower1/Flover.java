package com.example.zoz.flower1;

import java.util.Date;

/**
 * Created by Zoz on 21.01.2017.
 * Подгружаем растение создаем
 */

public class Flover {
    private String nameFlower;
    private int sizeFlower;
    private String growthStageFlower; //стадия роста flowersdb
    private String manufacturerFlower; //селекционаер flowersdb
    private String collectorFlower;// колекционер flowersdb
    private String featuresFlower; //особенности flowersdb
    private Date dateWateringFlower; //дата полива
    private int periodWateringFlower; //период полива

    Flover(String _NameFlower,int _SizeFlower, String _GrowthStageFlower, String _ManufacturerFlower,String _FeaturesFlower, Date _dateWateringFlower, int _periodWateringFlower, String _collectorFlower){
        nameFlower = _NameFlower;
        sizeFlower = _SizeFlower;
        growthStageFlower = _GrowthStageFlower;
        manufacturerFlower = _ManufacturerFlower;
        featuresFlower = _FeaturesFlower;
        dateWateringFlower = _dateWateringFlower;
        periodWateringFlower = _periodWateringFlower;
        collectorFlower = _collectorFlower;
    }

    public void setNameFlower(String nameFlower) {
        this.nameFlower = nameFlower;
    }

    public String getNameFlower() {
        return nameFlower;
    }

    public int getSizeFlower() {
        return sizeFlower;
    }

    public void setSizeFlower(int sizeFlower) {
        this.sizeFlower = sizeFlower;
    }

    public String getGrowthStageFlower() {
        return growthStageFlower;
    }

    public void setGrowthStageFlower(String growthStageFlower) {
        this.growthStageFlower = growthStageFlower;
    }

    public String getManufacturerFlower() {
        return manufacturerFlower;
    }

    public void setManufacturerFlower(String manufacturerFlower) {
        this.manufacturerFlower = manufacturerFlower;
    }

    public String getFeaturesFlower() {
        return featuresFlower;
    }

    public void setFeaturesFlower(String featuresFlower) {
        this.featuresFlower = featuresFlower;
    }

    public Date getDateWateringFlower() {
        return dateWateringFlower;
    }

    public void setDateWateringFlower(Date dateWateringFlower) {
        this.dateWateringFlower = dateWateringFlower;
    }

    public int getPeriodWateringFlower() {
        return periodWateringFlower;
    }

    public void setPeriodWateringFlower(int periodWateringFlower) {
        this.periodWateringFlower = periodWateringFlower;
    }

    public String getCollectorFlower() {
        return collectorFlower;
    }

    public void setCollectorFlower(String collectorFlower) {
        this.collectorFlower = collectorFlower;
    }
}
