package com.example.a2019rmcbride.collegeapphelper;

import android.content.ContentValues;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.a2019rmcbride.collegeapphelper.databinding.ActivityIndividualCollegePageBinding;

import java.util.ArrayList;


public class IndividualCollegePage extends AppCompatActivity {

    ActivityIndividualCollegePageBinding binding;
    ArrayList<String> schoolData;
    String idNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_individual_college_page);

        binding.homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(CollegeAdder.this, MainActivity.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

            }
        });

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });

        Intent intent = getIntent();

        schoolData = intent.getStringArrayListExtra("schoolData");

        binding.schoolName.setText(schoolData.get(0));
        binding.schoolDecision.setText(schoolData.get(1));
        binding.schoolDeadline.setText(schoolData.get(2));
        binding.schoolPlatform.setText(schoolData.get(3));
        binding.schoolInterview.setText(schoolData.get(4));
        binding.schoolMajor.setText(schoolData.get(5));

        idNumber = intent.getStringExtra("id");

        //Toast.makeText(this, idNumber + "", Toast.LENGTH_SHORT).show();

    }

    public void saveData() {

        String name = binding.schoolName.getText().toString();
        String decision = binding.schoolDecision.getText().toString();
        String deadline = binding.schoolDeadline.getText().toString();
        String platform = binding.schoolPlatform.getText().toString();
        String interview = binding.schoolInterview.getText().toString();
        String major = binding.schoolMajor.getText().toString();

        DatabaseHelper db = new DatabaseHelper(IndividualCollegePage.this);
        db.updateName(idNumber, name, schoolData.get(0));
        db.updateDecision(idNumber, decision, schoolData.get(1));
        db.updateDeadline(idNumber, deadline, schoolData.get(2));
        db.updatePlatform(idNumber, platform, schoolData.get(3));
        db.updateInterview(idNumber, interview, schoolData.get(4));
        db.updateMajor(idNumber, major, schoolData.get(5));
    }

    public void deleteData() {
        DatabaseHelper db = new DatabaseHelper(IndividualCollegePage.this);
        db.deleteEntry(idNumber);
    }

}
