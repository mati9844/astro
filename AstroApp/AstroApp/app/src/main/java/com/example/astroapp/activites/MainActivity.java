package com.example.astroapp.activites;

import android.content.Intent;
import android.os.Bundle;

import com.example.astroapp.AstroCalculatorThread;
import com.example.astroapp.GlobalValues;
import com.example.astroapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.astroapp.ui.main.SectionsPagerAdapter;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    AstroCalculatorThread astroCalculatorThread;
    TextView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        if(dpWidth < 720){

            viewPagerInit(sectionsPagerAdapter);
        }
        else {
            if(dpHeight < dpWidth) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragmentA, new TabSun())
                        .commit();
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragmentB, new TabMoon())
                        .commit();
            }
            else{
                viewPagerInit(sectionsPagerAdapter);
            }
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        location = (TextView)findViewById(R.id.location);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });

        dispLocation();
    }

    private void viewPagerInit(SectionsPagerAdapter sectionsPagerAdapter) {
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    void startThread(){
        if(astroCalculatorThread == null){
            astroCalculatorThread = new AstroCalculatorThread();
            astroCalculatorThread.start();

            GlobalValues.longitude = 21.012230;
            GlobalValues.latitude = 52.229675;
        }
        else{
            astroCalculatorThread.isRunning = false;
            astroCalculatorThread.interrupt();
            astroCalculatorThread = new AstroCalculatorThread();
            astroCalculatorThread.start();
        }
    }

    private void dispLocation() {
        DecimalFormat df = new DecimalFormat("#.##");
        String loc = ("" + df.format(GlobalValues.latitude) +  (char) 0x00B0 + "N  " + df.format(GlobalValues.longitude) +  (char) 0x00B0 + "W");
        location.setText(loc);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        dispLocation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        astroCalculatorThread.isRunning = false;
        astroCalculatorThread.interrupt();
        astroCalculatorThread = null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        startThread();
    }
}