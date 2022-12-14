package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lab1.dbroom.AppDatabase;
import com.example.lab1.dbroom.IdbUserData;
import com.example.lab1.dbroom.dbUserData;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    AppDatabase db = App.getInstance().getDatabase();
    IdbUserData userDao = db.IdbUserData();

    private EditText Email;
    private EditText Password;
    private Button LoginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = (EditText)findViewById(R.id.etEmail);
        Password = (EditText)findViewById(R.id.etPass);
        LoginBtn = (Button)findViewById(R.id.button);

        thread.start();

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check(Email.getText().toString(), Password.getText().toString());
            }
        });

    }

    private void check(String inputEmail, String inputPassword) {
        dbUserData user = new dbUserData();
        user = userDao.getByLogin(inputEmail);

        if (user != null) {
            if (Objects.equals(user.password, inputPassword)) {
                App.login = user.login;
                App.id = user.id;
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
            else {
                Password.setTextColor(Color.rgb(255, 0, 0));
            }
        }
        else {
            Email.setTextColor(Color.rgb(255, 0, 0));
        }
    }

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            getUsers();
        }
    });

    private void getUsers() {
        String[] emails = getResources().getStringArray(R.array.emails);
        String[] passwords = getResources().getStringArray(R.array.pass);

        for(int i = 0; i < emails.length; i++) {
            dbUserData user = new dbUserData();
            user.id = i;
            user.login = emails[i];
            user.password = passwords[i];

            userDao.insert(user);

            dbUserData userCheck = userDao.getByLogin(user.login);

            Log.i("Users db:",
                    String.valueOf(userCheck.id) + ": "
                            + userCheck.login + " "
                            + userCheck.password);
        }
    }
}