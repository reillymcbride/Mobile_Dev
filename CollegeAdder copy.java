package com.example.a2019rmcbride.collegeapphelper;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.a2019rmcbride.collegeapphelper.databinding.ActivityCollegeAdderBinding;

public class CollegeAdder extends AppCompatActivity {

    ActivityCollegeAdderBinding binding;
    String interviewStatus = "No";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_college_adder);


        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_college_adder);


        binding.interview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interviewStatus = "Yes";
            }
        });
        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToDB();
            }
         });
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


    }

    private void saveToDB() {
        SQLiteDatabase db = new DatabaseHelper(this).getWritableDatabase();

        /*ContentValues values = new ContentValues();
        values.put(Database.College.COLUMN_NAME, binding.collegeName.getText().toString());
        values.put(Database.College.COLUMN_DEADLINE, binding.collegeDeadline.getText().toString());
        values.put(Database.College.COLUMN_MAJOR, binding.intendedMajor.getText().toString()); */

        //long newRowId = db.insert(Database.College.TABLE_NAME, null, values);

        String name = binding.collegeName.getText().toString();
        String decision = binding.decisionPlan.getText().toString();
        String deadline = binding.collegeDeadline.getText().toString();
        String platform = binding.platform.getText().toString();
        String interview = interviewStatus;
        String major = binding.intendedMajor.getText().toString();


        String[] args = {name, decision, deadline, platform, interview, major};
        db.execSQL("INSERT INTO colleges (name, decisionPlan, applicationDeadline, applicationPlatform, interview, intendedMajor) VALUES (?, ?, ?, ?, ?, ?)", args);

        //Toast.makeText(this, "The new Row Id is " + newRowId, Toast.LENGTH_LONG).show();
    }

}



