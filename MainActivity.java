package com.example.a2019rmcbride.collegeapphelper;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.a2019rmcbride.collegeapphelper.databinding.ActivityCollegeAdderBinding;
import com.example.a2019rmcbride.collegeapphelper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_main);
        binding.addCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (MainActivity.this, CollegeAdder.class);
                startActivity(i);
            }
        });
        binding.seeColleges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (MainActivity.this, MyColleges.class);
                startActivity(i);
            }
        });
        binding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (MainActivity.this, CollegeSearch.class);
                startActivity(i);
            }
        });


    }
}
