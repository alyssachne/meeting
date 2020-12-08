package com.example.myapplication;

import Controller.*;
import Gateway.ControllerRW;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button loginButton;
    Button registerButton;
    EditText username;
    EditText password;
    private Intent loginIntent;
    private Intent registerIntent;
    ControllerRW crw;
    ControllerFacade uo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = (Button)findViewById(R.id.login_button);
        registerButton = (Button) findViewById(R.id.register_button);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        init();
    }

    public void init() {
        crw = new ControllerRW(this.getApplicationContext());
        if (crw.readFile()==null){
            uo = new ControllerFacade();
            crw.writeFile(uo);
        }else{
            uo = crw.readFile();
        }
    }
    @Override
    public void onClick(View v) {

        switch(v.getId()) {

                case R.id.login_button:
                    if (username.getText().toString().equalsIgnoreCase("Organizer")){
                        loginIntent = new Intent(MainActivity.this, OrganizerActivity.class);
                        startActivity(loginIntent);
//                        crw.writeFile(uo);
                        finish();
                        break;
                    }else if (username.getText().toString().equalsIgnoreCase("Speaker")){
                        loginIntent = new Intent(MainActivity.this,SpeakerActivity.class);
                        startActivity(loginIntent);
//                        crw.writeFile(uo);
                        finish();
                        break;
                    }else if (uo.login(username.getText().toString(), password.getText().toString())){
//                        username.getText().toString().equalsIgnoreCase("Attendee")
                        loginIntent = new Intent(MainActivity.this, AttendeeActivity.class);
                        startActivity(loginIntent);
//                        crw.writeFile(uo);
                        finish();
                        break;
                    }else{
                        Toast.makeText(MainActivity.this,"The username or password does not match!",Toast.LENGTH_SHORT).show();
                        break;
                    }

                case R.id.register_button:

                    registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(registerIntent);
                    finish();
                    break;

            }
    }
}