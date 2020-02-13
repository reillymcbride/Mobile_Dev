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

import com.example.a2019rmcbride.collegeapphelper.databinding.ActivitySearchResultsBinding;

import java.util.ArrayList;

public class SearchResults extends AppCompatActivity {

    ActivitySearchResultsBinding binding;

    //search terms the user entered in the search screen
    String decisionPref = "All";
    String deadlinePref = "All";
    String platformPref = "All";
    String interviewPref = "All";
    String majorPref = "All";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_search_results);

        Intent intent = getIntent();

        decisionPref = intent.getStringExtra("decision");
        deadlinePref = intent.getStringExtra("deadline");
        platformPref = intent.getStringExtra("platform");
        interviewPref = intent.getStringExtra("interview");
        majorPref = intent.getStringExtra("major");


        binding.homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

            }
        });

        readFromDB(decisionPref, deadlinePref, platformPref, interviewPref, majorPref);

    }

    private void readFromDB(String decision, String deadline, String platform, String interview, String major) {

        SQLiteDatabase database = new DatabaseHelper(this).getReadableDatabase();

        String[] projection = {
                Database.College._ID,
                Database.College.COLUMN_NAME,
                Database.College.COLUMN_DEADLINE,
                Database.College.COLUMN_MAJOR
        };

        //String selection = Database.College.COLUMN_DEADLINE + " = ?";

        String selection = "";

        ArrayList<String> sel = new ArrayList<String>();

        if (!decision.equals("All")){
            sel.add(decision);
            selection = selection + Database.College.COLUMN_DECISION + " = ?";
        }
        if (!deadline.equals("All")){
            sel.add(deadline);
            if(!selection.equals(""))
                selection = selection + " AND ";
            selection = selection + Database.College.COLUMN_DEADLINE + " = ?";
        }
        if (!platform.equals("All")){
            sel.add(platform);
            if(!selection.equals(""))
                selection = selection + " AND ";
            selection = selection + Database.College.COLUMN_PLATFORM + " = ?";
        }
        if (!interview.equals("All")){
            sel.add(interview);
            if(!selection.equals(""))
                selection = selection + " AND ";
            selection = selection + Database.College.COLUMN_INTERVIEW + " = ?";
        }
        if (!major.equals("All")){
            sel.add(major);
            if(!selection.equals(""))
                selection = selection + " AND ";
            selection = selection + Database.College.COLUMN_MAJOR + " = ?";
        }

        //String[] selectionArgs = { deadline };

        selection = selection + "";
        String[] selectionArgs = sel.toArray(new String[0]);


        Cursor cursor = database.query(
                Database.College.TABLE_NAME,     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // don't sort
        );


        //Log.d("ooooooooooooooooooo", "The total cursor count is " + cursor.getCount());
        binding.collegeRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.collegeRecycler.setAdapter(new RecyclerViewCursorAdapter(this, cursor));
    }
}
