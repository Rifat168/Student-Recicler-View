package com.seip.android.studentreciclerview.pickers;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private onDatePickListener listener;

    public DatePickerDialogFragment(onDatePickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance(Locale.getDefault());
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//      Toast.makeText(getActivity(), dayOfMonth+"/"+month+"/"+year, Toast.LENGTH_SHORT).show();
        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM//yyyy");
        final String selectedDate = sdf.format(calendar.getTime());

       // Log.d("DATE_PICKER", dayOfMonth+"/"+month+"/"+year);
        listener.onDatePicked(selectedDate);
        Log.d("DATE_PICKER", selectedDate);

    }

    public interface onDatePickListener {
        void onDatePicked(String date);
    }
}
