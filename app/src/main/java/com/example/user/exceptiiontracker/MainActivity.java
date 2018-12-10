package com.example.user.exceptiiontracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnFirebase, btnRaygun, btnRollbar, btnSentry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFirebase = findViewById(R.id.btn_firebase);
        btnRaygun = findViewById(R.id.btn_raygun);
        btnRollbar = findViewById(R.id.btn_rollbar);
        btnSentry = findViewById(R.id.btn_sentry);

        btnFirebase.setOnClickListener(this);
        btnRaygun.setOnClickListener(this);
        btnRollbar.setOnClickListener(this);
        btnSentry.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_firebase:
                nextActivity("firebase");
                break;
            case R.id.btn_sentry:
                nextActivity("sentry");
                break;
            case R.id.btn_rollbar:
                nextActivity("rollbar");
                break;
            case R.id.btn_raygun:
                nextActivity("raygun");
                break;
            default:
                break;
        }
    }

    private void nextActivity(String toolname) {
        Intent nextIntent = new Intent(MainActivity.this, ExceptionHandler.class);
        nextIntent.putExtra("from", toolname);
        startActivity(nextIntent);
    }
}
