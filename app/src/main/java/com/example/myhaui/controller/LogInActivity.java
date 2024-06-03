package com.example.myhaui.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myhaui.Database.DBHelper;
import com.example.myhaui.Database.DatabaseQuery;
import com.example.myhaui.R;
import com.example.myhaui.model.User;

public class LogInActivity extends AppCompatActivity {

    EditText txtCode, txtPass;
    Button btnLogIn, btnRegister;
    DatabaseQuery dbHelper;

    public void initView(){
        dbHelper = new DatabaseQuery(this);
        txtCode = findViewById(R.id.txt_logIn_Code);
        txtPass = findViewById(R.id.txt_logIn_Pass);
        btnLogIn = findViewById(R.id.btn_logIn);
        btnRegister = findViewById(R.id.btn_register);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, RegisterActivity.class);
                    startActivity(intent);
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtCode.getText().equals("") || txtPass.getText().equals(""))
                    Toast.makeText(LogInActivity.this, "Vui lòng nhập tài khoản và mật khẩu.", Toast.LENGTH_SHORT).show();
                else {
                    User user = dbHelper.getUserByCode(txtCode.getText().toString());

                    if(user == null) {
                        Toast.makeText(LogInActivity.this, "Mã sinh viên không đúng.", Toast.LENGTH_SHORT).show();
                    }else if(!user.getPassword().toString().equals(txtPass.getText().toString())){
                        Toast.makeText(LogInActivity.this, "Mật khẩu không chính xác.", Toast.LENGTH_SHORT).show();
                    }else {
                        SharedPreferences sharedPreferences = getSharedPreferences("Information_User", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putInt("userID", user.get_id());
                        editor.putString("userCode", user.getCode());
                        editor.apply();

                        Intent intent2= new Intent(LogInActivity.this, HomeActivity.class);
                        startActivity(intent2);
                    }
                }
            }
        });


    }
}