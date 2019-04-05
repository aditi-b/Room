package com.room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.room.model.MyDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSignIn, btnSignUp;                    //buttons for signin and signup

    EditText etName, etPassword;
    String getName, getPassword;
    SharedPreferences sharedPreferences;            //shared preference for storing the id of the user
    SharedPreferences.Editor editor;                // editor to edit the shared preference



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn = findViewById(R.id.button);
        btnSignUp = findViewById(R.id.button2);
        etName = findViewById(R.id.editText11);
        etPassword = findViewById(R.id.editText12);

        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

        sharedPreferences = getApplicationContext().getSharedPreferences("Register", 0);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {

            case R.id.button:
                getName = etName.getText().toString();
                getPassword = etPassword.getText().toString();
                int id = MyDatabase.getInstance().myDao().getId(getName, getPassword);

                if(id <=0)
                {
                    Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show();
                    break;
                }
                else
                {
                    editor = sharedPreferences.edit();
                    editor.putInt("ID", id);
                    editor.apply();
                    Intent in = new Intent(getApplicationContext(), ViewActivity.class);
                    startActivity(in);
                    finish();
                    break;
                }


            case R.id.button2:
                Intent i = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}
