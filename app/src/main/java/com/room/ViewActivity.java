package com.room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.room.model.MyDatabase;
import com.room.model.User;

import java.util.List;

public class ViewActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sharedPreferences;
    TextView tvName, tvPhone, tvPassword;
    int id;                                             // int to store the value of id
    SharedPreferences.Editor editor;
    Button btnEdit, btnLogout, btnDelete;               // button for edit, logout and delete


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        tvName = findViewById(R.id.textView7);
        tvPhone = findViewById(R.id.textView8);
        tvPassword = findViewById(R.id.textView9);
        btnEdit = findViewById(R.id.button5);
        btnLogout = findViewById(R.id.button4);
        btnDelete = findViewById(R.id.button6);

        btnEdit.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        btnDelete.setOnClickListener(this);


        sharedPreferences = getApplicationContext().getSharedPreferences("Register", 0);
        id = sharedPreferences.getInt("ID", 0);

        User user = MyDatabase.getInstance().myDao().getUser(id);

        tvName.setText(user.getName());
        tvPhone.setText(user.getEmail());
        tvPassword.setText(user.getPassword());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button4:                        // to clear the shared preference on log out
                editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
                break;

            case R.id.button6:                       // to delete a user
                User user = new User();
                user.setId(id);
                MyDatabase.getInstance().myDao().delete(user);
                editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Toast.makeText(this, "Deleted successfully", Toast.LENGTH_SHORT).show();
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
                finish();
                break;

            case R.id.button5:                       //  to move to EditActivity
                Intent in = new Intent(getApplicationContext(), EditActivity.class);
                startActivity(in);
                finish();
                break;

        }
    }
}
