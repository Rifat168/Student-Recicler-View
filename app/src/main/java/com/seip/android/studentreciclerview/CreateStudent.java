package com.seip.android.studentreciclerview;

import android.annotation.SuppressLint;
import android.os.Binder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.seip.android.studentreciclerview.databinding.FragmentCreateStudentBinding;
import com.seip.android.studentreciclerview.models.Student;
import com.seip.android.studentreciclerview.pickers.DatePickerDialogFragment;
import com.seip.android.studentreciclerview.pickers.TimePickerDialogFragment;

import java.sql.Time;


public class CreateStudent extends Fragment implements DatePickerDialogFragment.onDatePickListener{

    FragmentCreateStudentBinding binding;
    private  String studentCourse, studentCourseName;

    private String type="SEIP";

    private final String[]seipCourse = {"Android App Development", "Web Design And Development", "Server Management", "Graphics Design", "Video Editing And Animation"};
    private final String[]paidCourse = {"CCNA Networking", "Flutter", "Game Development", "Adobe After Effect", "VFX"};


    ArrayAdapter<String> arrayAdapter;


    public CreateStudent() {
        // Required empty public constructor
    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentCreateStudentBinding.inflate(inflater);

        binding.buttonRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                 final RadioButton rb = group.findViewById(checkedId);
                 type  = rb.getText().toString();
                if (type.equals("SEIP")){
                    getAdapter(seipCourse);
                }else{
                    getAdapter(paidCourse);
                }
            }
        });
        getAdapter(seipCourse);
        binding.courseTypeSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                studentCourseName = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.datePicker.setOnClickListener(v -> {
            new DatePickerDialogFragment(this).show(getChildFragmentManager(), null);

        });
        binding.timePicker.setOnClickListener(v -> {
            new TimePickerDialogFragment(this).show(getChildFragmentManager(), null);


        });




        binding.saveB.setOnClickListener(v -> {
            String studneName = binding.studentNameET.getText().toString();
            Student.__setStudent(new Student(studneName, type, studentCourseName));
            Navigation.findNavController(v).popBackStack();
            Toast.makeText(getContext(), "Successfully Saved", Toast.LENGTH_SHORT).show();
        });

        return binding.getRoot();
    }

    private void getAdapter(String[] courses) {
        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, courses);
        binding.courseTypeSP.setAdapter(arrayAdapter);
    }

    @Override
    public void onDatePicked(String date) {
        binding.datePicker.setText(date);

    }
    public void onTimePicked(String time){
        binding.timePicker.setText(time);
    }
}