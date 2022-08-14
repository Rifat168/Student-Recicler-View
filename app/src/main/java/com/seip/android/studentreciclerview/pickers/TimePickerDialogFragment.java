package com.seip.android.studentreciclerview.pickers;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.util.TimeFormatException;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.SimpleTimeZone;

public class TimePickerDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private onTimePickListener listener;

    public TimePickerDialogFragment(TimePickerDialogFragment.onTimePickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance(Locale.getDefault());
        final int hour = calendar.get(Calendar.HOUR);
        final int minute = calendar.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(),this,hour,minute,false);
    }



    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
       // Toast.makeText(getActivity(), dayOfMonth+"/"+month+"/"+year, Toast.LENGTH_SHORT).show();
        final Calendar calendar = Calendar.getInstance();
        calendar.set(hourOfDay, minute);
        //final DateTimeFormatter
        final SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss ");
        final String selectedTime = sdf1.format(calendar.getTime());

        // Log.d("DATE_PICKER", dayOfMonth+"/"+month+"/"+year);
        listener.onTimePicked(selectedTime);
        Log.d("TIME_PICKER", selectedTime);


    }

    public interface onTimePickListener {
        void onTimePicked(String time);
    }
}