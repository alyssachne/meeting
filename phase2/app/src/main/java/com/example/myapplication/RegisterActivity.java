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
    ControllerRW crw;
    ControllerFacade uo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerButton = (Button) findViewById(R.id.register_button2);
        name = (EditText)findViewById(R.id.name);
        username = (EditText)findViewById(R.id.register_username);
        password = (EditText)findViewById(R.id.register_password);
        registerButton.setOnClickListener(this);
        init();
    }

    public void init() {
        crw = new ControllerRW(this.getApplicationContext());
        uo = crw.readFile();
    }
    @Override
    public void onClick(View v) {
        //check if the username exist
        // instead of calling different method, passing the usertype
        uo.create(name.getText().toString(),username.getText().toString(),password.getText().toString(),"Attendee");
        crw.writeFile(uo);
        registerIntent = new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(registerIntent);

    }
}