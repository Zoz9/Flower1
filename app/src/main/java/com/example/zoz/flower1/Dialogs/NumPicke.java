package com.example.zoz.flower1.Dialogs;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.NumberPicker;

import com.example.zoz.flower1.R;

import java.text.ParseException;
import java.util.Calendar;

/**
 * Created by User on 03.01.2018.
 */

public class NumPicke extends DialogFragment  {

    private NumberPicker.OnValueChangeListener valueChangeListener;

    public interface ShareNumberDialogListener {
        public void onNumberDialogPositiveClick(DialogInterface dialog, int num);
        public void onNumberDialogNegativeClick(DialogInterface dialog, int which);
    }

    ShareNumberDialogListener nListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final NumberPicker numberPicker = new NumberPicker(getActivity());

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(60);



        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose Value");
        builder.setMessage("Choose a number :");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nListener.onNumberDialogPositiveClick(
                        (DialogInterface) dialog,
                        numberPicker.getValue());
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setView(numberPicker);
        return builder.create();
    }

    public NumberPicker.OnValueChangeListener getValueChangeListener() {
        return valueChangeListener;
    }

    public void setValueChangeListener(NumberPicker.OnValueChangeListener valueChangeListener) {
        this.valueChangeListener = valueChangeListener;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            nListener = (ShareNumberDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ShareDialogListener");
        }
    }

}
