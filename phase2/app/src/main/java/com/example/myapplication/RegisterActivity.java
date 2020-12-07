package com.example.myapplication;

import Controller.ControllerFacade;
import Gateway.ControllerRW;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Button registerButton;
    EditText name;
    EditText username;
    EditText password;
    private Intent registerIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerButton = (Button) findViewById(R.id.register_button2);
        name = (EditText)findViewById(R.id.name);
        username = (EditText)findViewById(R.id.register_username);
        password = (EditText)findViewById(R.id.register_password);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        ControllerRW crw = new ControllerRW(this.getApplicationContext());
        ControllerFacade uo = null;
        if (crw.readFile()!=null){
            uo = crw.readFile();
        }else{
            uo = new ControllerFacade();
        }
        //check if the username exist
        uo.createAttendee(name.getText().toString(),username.getText().toString(),password.getText().toString());
        crw.writeFile(uo);
        registerIntent = new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(registerIntent);

    }
}