package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean isRunning;
    long pauseOffSet;

    Chronometer meter;
    Button start;
    Button stop;
    Button reset;

    public MainActivity() {
        isRunning = false;
        pauseOffSet = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finding references
        meter = findViewById(R.id.meter);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        reset = findViewById(R.id.reset);


        //Working of Buttons

        //start
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isRunning)
                    Toast.makeText(MainActivity.this, "Started", Toast.LENGTH_SHORT).show();

                if( ! isRunning ) {
                    meter.setBase(SystemClock.elapsedRealtime() - pauseOffSet);
                    meter.start();
                    isRunning = true;
                }
            }
        });

        //stop
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( ! isRunning ) {
                    Toast.makeText(MainActivity.this, "Stopped", Toast.LENGTH_SHORT).show();
                }

                if(isRunning) {
                    meter.stop();
                    pauseOffSet = SystemClock.elapsedRealtime()-meter.getBase();
                    isRunning = false;
                }
            }
        });

        //reset
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meter.setBase(SystemClock.elapsedRealtime());
                pauseOffSet = 0;
                meter.stop();
                isRunning = false;
            }
        });

    }
}