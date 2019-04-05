package com.room;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.room.model.MyDatabase;
import com.room.model.User;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sharedPreferences;
    int id;
    EditText etname, etphone, etpassword;
    String nameGet, phoneGet, passwordGet;
    Button btnUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        etname = findViewById(R.id.editText5);
        etphone = findViewById(R.id.editText6);
        etpassword = findViewById(R.id.editText7);
        btnUpdate = findViewById(R.id.button11);

        btnUpdate.setOnClickListener(this);

        sharedPreferences = getApplicationContext().getSharedPreferences("Register", 0);

        // getting the value of id from shared preferences
        id = sharedPreferences.getInt("ID", 0);
        User user = MyDatabase.getInstance().myDao().getUser(id);

        etname.setText(user.getName());
        etphone.setText(user.getEmail());
        etpassword.setText(user.getPassword());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button11:
                nameGet = etname.getText().toString();
                phoneGet = etphone.getText().toString();
                passwordGet = etpassword.getText().toString();

               User user1 = new User();
                user1.setId(id);
                user1.setName(nameGet);
                user1.setEmail(phoneGet);
                user1.setPassword(passwordGet);
                MyDatabase.getInstance().myDao().update(user1);


               Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                break;

        }

    }
}
