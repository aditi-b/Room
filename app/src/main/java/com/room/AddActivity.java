package com.room;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.room.model.MyDao;
import com.room.model.MyDatabase;
import com.room.model.User;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {


    EditText etName, etPassword, etPhone, etConfirmPassword;         // to get the content in the edit text fields
    Button btnSignUp;                                                // signup button
    String nameget, phoneget, passwordget, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnSignUp = findViewById(R.id.button3);

        etName = findViewById(R.id.editText1);
        etPhone = findViewById(R.id.editText2);
        etPassword = findViewById(R.id.editText3);
        etConfirmPassword = findViewById(R.id.editText4);

        btnSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button3:
                nameget = etName.getText().toString();
                phoneget = etPhone.getText().toString();
                passwordget = etPassword.getText().toString();
                confirmPassword = etConfirmPassword.getText().toString();

                MyDao myDao = MyDatabase.getInstance().myDao();
                User user = new User();
                user.setName(nameget);
                user.setEmail(phoneget);
                user.setPassword(passwordget);

                myDao.addUser(user);

                Toast.makeText(this, "Inserted", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}
