package com.example.nancy.meetminus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button SignUp;
    private Button SignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Locate the button in activity_main.xml
        SignUp = (Button) findViewById(R.id.SignUp);
        SignIn = (Button) findViewById(R.id.SignIn);

        // Capture button clicks
        SignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        SignupActivity.class);
                startActivity(myIntent);
            }
        });

        // Capture button clicks
        SignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        MapsActivityCurrentPlace.class);
                startActivity(myIntent);
            }
        });
    }



}
