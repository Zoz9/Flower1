package com.example.zoz.flower1;

/**
 * Created by Zoz on 21.01.2017.
 * Сделать родетелем катологов
 */

public class CatalogFlower {

    private String name;
    private int id;
    private String bdname;


    CatalogFlower(String name,int id, String bdname){

        this.name = name;
        this.id = id;
        this.bdname = bdname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBdname() {
        return bdname;
    }

    public void setBdname(String bdname) {
        this.bdname = bdname;
    }
}
