package com.example.zoz.flower1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Zoz on 21.01.2017.
 */

public class FlowerEditor extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flower_edit);

        Intent intent = getIntent();

    }


    @Override
    public void onClick(View v) {

    }
}
