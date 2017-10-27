package com.example.zoz.flower1.Dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.TextView;

import com.example.zoz.flower1.R;

import java.text.ParseException;
import java.util.Calendar;

public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public interface ShareDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, int year,int month,int day) throws ParseException;
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    ShareDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // определяем текущую дату
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // создаем DatePickerDialog и возвращаем его
        Dialog picker = new DatePickerDialog(getActivity(), this,
                year, month, day);
        picker.setTitle(getResources().getString(R.string.choose_date));

        return picker;
    }
    @Override
    public void onStart() {
        super.onStart();
        // добавляем кастомный текст для кнопки
        Button nButton =  ((AlertDialog) getDialog())
                .getButton(DialogInterface.BUTTON_POSITIVE);
        nButton.setText(getResources().getString(R.string.ready));

    }

    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year,
                          int month, int day) {

        //TextView tv = (TextView) getActivity().findViewById(R.id.textView);
        //tv.setText(day + "-" + month + "-" + year);

        try {
            mListener.onDialogPositiveClick(DatePicker.this,year,month,day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        getParentFragment().onActivityResult(getTargetRequestCode() , Activity.RESULT_OK , i);
    }

    //Переопределяем что б был доступ к Интерфейсу
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (ShareDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ShareDialogListener");
        }
    }
}