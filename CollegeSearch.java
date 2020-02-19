package com.example.a2019rmcbride.collegeapphelper;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.a2019rmcbride.collegeapphelper.databinding.ActivityCollegeSearchBinding;
import com.example.a2019rmcbride.collegeapphelper.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CollegeSearch extends AppCompatActivity {

    ActivityCollegeSearchBinding binding;

    //results from the Spinners, i.e. what the user selects for their search terms
    String decisionRes = "All";
    String deadlineRes = "All";
    String platformRes = "All";
    String interviewRes = "All";
    String majorRes = "All";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_college_search);

        binding.homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

            }
        });

        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CollegeSearch.this, SearchResults.class);
                i.putExtra("decision", decisionRes);
                i.putExtra("deadline", deadlineRes );
                i.putExtra("platform", platformRes );
                i.putExtra("interview", interviewRes );
                i.putExtra("major", majorRes );
                startActivity(i);

            }
        });

        createDecision();

        createDeadline();

        createPlatform();

        createInterview();

        createMajor();

        binding.decisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                decisionRes = parentView.getItemAtPosition(position).toString();
                //Toast.makeText(CollegeSearch.this, "The  decision is " + decisionRes, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        binding.deadlineSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                deadlineRes = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        binding.platformSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                platformRes = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        binding.interviewSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                interviewRes = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        binding.majorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                majorRes = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

    }

    public void createDecision()
    {
        String[] queryCols = new String[]{"_id", Database.College.COLUMN_DECISION};

        SQLiteDatabase database = new DatabaseHelper(this).getReadableDatabase();
        Cursor cursor = database.query(
                Database.College.TABLE_NAME,     // The table to query
                queryCols,                                // The columns to return
                null,                                     // The columns for the WHERE clause
                null,                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // don't sort
        );

        Set<String> options = new HashSet<String>();

        while (cursor.moveToNext()) {
            options.add(cursor.getString(cursor.getColumnIndex(Database.College.COLUMN_DECISION)));
        }

        ArrayList<String> array = new ArrayList<>();
        array.addAll(options);
        array.add(0, "All");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.decisionSpinner.setAdapter(adapter);
    }

    public void createDeadline()
    {
        String[] queryCols = new String[]{"_id", Database.College.COLUMN_DEADLINE};

        SQLiteDatabase database = new DatabaseHelper(this).getReadableDatabase();
        Cursor cursor = database.query(
                Database.College.TABLE_NAME,     // The table to query
                queryCols,                                // The columns to return
                null,                                     // The columns for the WHERE clause
                null,                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // don't sort
        );

        Set<String> options = new HashSet<String>();

        while (cursor.moveToNext()) {
            options.add(cursor.getString(cursor.getColumnIndex(Database.College.COLUMN_DEADLINE)));
        }

        ArrayList<String> array = new ArrayList<>();
        array.addAll(options);
        array.add(0, "All");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.deadlineSpinner.setAdapter(adapter);
    }

    public void createPlatform()
    {
        String[] queryCols = new String[]{"_id", Database.College.COLUMN_PLATFORM};

        SQLiteDatabase database = new DatabaseHelper(this).getReadableDatabase();
        Cursor cursor = database.query(
                Database.College.TABLE_NAME,     // The table to query
                queryCols,                                // The columns to return
                null,                                     // The columns for the WHERE clause
                null,                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // don't sort
        );

        Set<String> options = new HashSet<String>();

        while (cursor.moveToNext()) {
            options.add(cursor.getString(cursor.getColumnIndex(Database.College.COLUMN_PLATFORM)));
        }

        ArrayList<String> array = new ArrayList<>();
        array.addAll(options);
        array.add(0, "All");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.platformSpinner.setAdapter(adapter);
    }

    public void createInterview()
    {
        String[] queryCols = new String[]{"_id", Database.College.COLUMN_INTERVIEW};

        SQLiteDatabase database = new DatabaseHelper(this).getReadableDatabase();
        Cursor cursor = database.query(
                Database.College.TABLE_NAME,     // The table to query
                queryCols,                                // The columns to return
                null,                                     // The columns for the WHERE clause
                null,                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // don't sort
        );

        Set<String> options = new HashSet<String>();

        while (cursor.moveToNext()) {
            options.add(cursor.getString(cursor.getColumnIndex(Database.College.COLUMN_INTERVIEW)));
        }

        ArrayList<String> array = new ArrayList<>();
        array.addAll(options);
        array.add(0, "All");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.interviewSpinner.setAdapter(adapter);
    }

    public void createMajor()
    {
        String[] queryCols = new String[]{"_id", Database.College.COLUMN_MAJOR};

        SQLiteDatabase database = new DatabaseHelper(this).getReadableDatabase();
        Cursor cursor = database.query(
                Database.College.TABLE_NAME,     // The table to query
                queryCols,                                // The columns to return
                null,                                     // The columns for the WHERE clause
                null,                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // don't sort
        );

        Set<String> options = new HashSet<String>();

        while (cursor.moveToNext()) {
            options.add(cursor.getString(cursor.getColumnIndex(Database.College.COLUMN_MAJOR)));
        }

        ArrayList<String> array = new ArrayList<>();
        array.addAll(options);
        array.add(0, "All");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.majorSpinner.setAdapter(adapter);
    }




}
