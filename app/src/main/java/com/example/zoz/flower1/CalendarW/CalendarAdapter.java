package com.example.zoz.flower1.CalendarW;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.zoz.flower1.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by Zoz on 27.07.2017.
 */

public class CalendarAdapter extends ArrayAdapter<Date>
{
    // days with events
    private HashSet<Date> eventDays;

    // for view inflation
    private LayoutInflater inflater;

    public CalendarAdapter(Context context, ArrayList<Date> days, HashSet<Date> eventDays)
    {
        super(context, R.layout.control_calendar_day, days);
        this.eventDays = eventDays;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        // day in question
        Date date = getItem(position);
        int day = date.getDate();
        int month = date.getMonth();
        int year = date.getYear();

        // today
        Date today = new Date();

        // inflate item if it does not exist yet
        if (view == null)
            view = inflater.inflate(R.layout.control_calendar_day, parent, false);

        // if this day has an event, specify event image
        view.setBackgroundResource(0);
        if (eventDays != null)
        {
            for (Date eventDate : eventDays)
            {
                //Log.d("LOG", eventDate.toString());
                if (eventDate.getDate() == day &&
                        eventDate.getMonth() == month &&
                        eventDate.getYear() == year)
                {
                    // mark this day for event
                    view.setBackgroundResource(R.drawable.reminder);
                    break;
                }
            }
        }

        // clear styling
        ((TextView)view).setTypeface(null, Typeface.NORMAL);
        ((TextView)view).setTextColor(Color.BLACK);

        if (month != today.getMonth() || year != today.getYear())
        {
            // if this day is outside current month, grey it out
            ((TextView)view).setTextColor(getContext().getResources().getColor(R.color.greyed_out));
        }
        /*
        else if (day == today.getDate())
        {
            // if it is today, set it to blue/bold
            ((TextView)view).setTypeface(null, Typeface.BOLD);
            ((TextView)view).setTextColor(getContext().getResources().getColor(R.color.today));
        }
*/
        // set text
        ((TextView)view).setText(String.valueOf(date.getDate()));

        return view;
    }
}