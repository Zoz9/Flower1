package com.example.zoz.flower1;

import java.util.Date;

/**
 * Created by Zoz on 21.01.2017.
 */

public class GroundForFlover {

    private Date dateFertilizer; //
    private String quantityFertilizer; //
    private String Fertilizer; // Удобрение

    public Date getDateFertilizer() {
        return dateFertilizer;
    }

    public void setDateFertilizer(Date dateFertilizer) {
        this.dateFertilizer = dateFertilizer;
    }

    public String getQuantityFertilizer() {
        return quantityFertilizer;
    }

    public void setQuantityFertilizer(String quantityFertilizer) {
        this.quantityFertilizer = quantityFertilizer;
    }

    public String getFertilizer() {
        return Fertilizer;
    }

    public void setFertilizer(String fertilizer) {
        Fertilizer = fertilizer;
    }
}
