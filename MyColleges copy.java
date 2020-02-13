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
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.a2019rmcbride.collegeapphelper.databinding.ActivityMyCollegesBinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MyColleges extends AppCompatActivity implements RecyclerViewCursorAdapter.ItemClickListener {

    ActivityMyCollegesBinding binding;
    RecyclerViewCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_my_colleges);

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

        Log.d("OOOOOOOOOOOOOOOOOOOOOOO",  "hello");
        readFromDB();

    }

    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(this,""+position , Toast.LENGTH_SHORT).show();
        getSchool(position);
        //Intent i = new Intent(this, PersonActivity.class);
        //startActivity(i);
    }


    private void readFromDB() {
        //String deadline = "Nov 1";

        SQLiteDatabase database = new DatabaseHelper(this).getReadableDatabase();

        String[] projection = {
                Database.College._ID,
                Database.College.COLUMN_NAME,
                Database.College.COLUMN_DEADLINE,
                Database.College.COLUMN_MAJOR
        };


        //String selection = Database.College.COLUMN_DEADLINE + " = ?";

        //String[] selectionArgs = { deadline };

        Cursor cursor = database.query(
                Database.College.TABLE_NAME,     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // don't sort
        );


        Log.d("ooooooooooooooooooo", "The total cursor count is " + cursor.getCount());
        binding.collegeRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewCursorAdapter(this, cursor);
        adapter.setClickListener(this);
        binding.collegeRecycler.setAdapter(adapter);
    }

    //open up the school that was clicked at a certain position
    private void getSchool(int pos){
        String[] queryCols = new String[]{"_id",
                Database.College.COLUMN_NAME,
                Database.College.COLUMN_DECISION,
                Database.College.COLUMN_DEADLINE,
                Database.College.COLUMN_PLATFORM,
                Database.College.COLUMN_INTERVIEW,
                Database.College.COLUMN_MAJOR};

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

        ArrayList<ArrayList> allSchools = new ArrayList<>();
        ArrayList<String> idNumbers = new ArrayList<String>();

        while (cursor.moveToNext()) {
            ArrayList<String> schoolItems = new ArrayList<String>();

            idNumbers.add(cursor.getString(cursor.getColumnIndex(Database.College._ID)));
            schoolItems.add(cursor.getString(cursor.getColumnIndex(Database.College.COLUMN_NAME)));
            schoolItems.add(cursor.getString(cursor.getColumnIndex(Database.College.COLUMN_DECISION)));
            schoolItems.add(cursor.getString(cursor.getColumnIndex(Database.College.COLUMN_DEADLINE)));
            schoolItems.add(cursor.getString(cursor.getColumnIndex(Database.College.COLUMN_PLATFORM)));
            schoolItems.add(cursor.getString(cursor.getColumnIndex(Database.College.COLUMN_INTERVIEW)));
            schoolItems.add(cursor.getString(cursor.getColumnIndex(Database.College.COLUMN_MAJOR)));

            allSchools.add(schoolItems);
        }

        ArrayList<String> school = allSchools.get(pos);

        String name = school.get(0);

        //Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

        Intent i = new Intent(MyColleges.this, IndividualCollegePage.class);
        i.putExtra("schoolData", school);
        i.putExtra("id", idNumbers.get(pos));
        startActivity(i);


    }

}
