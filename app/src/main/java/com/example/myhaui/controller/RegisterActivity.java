package com.example.myhaui.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class RegisterActivity extends AppCompatActivity {

    EditText studentIdEditText, fullNameEditText, phoneEditText, passwordEditText, confirmPasswordEditText;
    RadioGroup rdGender;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getWidget();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckValid()) {
                    String fullName = fullNameEditText.getText().toString();
                    String code = studentIdEditText.getText().toString();
                    String phone_number = phoneEditText.getText().toString();
                    int idx = rdGender.getCheckedRadioButtonId();
                    RadioButton rd = rdGender.findViewById(idx);
                    String gender = rd.getText().toString();

                    String passwordString = passwordEditText.getText().toString();
                    User user = new User(-1, code, fullName, passwordString, gender, phone_number, "", "", "");
                    DatabaseQuery databaseQuery = new DatabaseQuery(getApplicationContext());

                    long id = databaseQuery.addNewUser(user);

                    if(id>0) {
                        user.set_id((int)id);
                        Toast.makeText(RegisterActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegisterActivity.this, LogInActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    public void getWidget() {
        studentIdEditText = findViewById(R.id.studentIdEditText);
        fullNameEditText = findViewById(R.id.fullNameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        rdGender = findViewById(R.id.rdGender);
        registerButton = findViewById(R.id.registerButton);

    }
    public boolean CheckValid() {
        String msg= "";
        if(studentIdEditText.getText().toString().isEmpty() || fullNameEditText.getText().toString().isEmpty() || phoneEditText.getText().toString().isEmpty() || passwordEditText.getText().toString().isEmpty() || confirmPasswordEditText.getText().toString().isEmpty()) {
            msg += "Nhập đầy đủ thông tin";
        }else {
            DatabaseQuery dbHelper = new DatabaseQuery(this);
            User user = dbHelper.getUserByCode(studentIdEditText.getText().toString());
            if(user != null){
                msg = "Mã sinh viên đã được đăng kí";
            }
            if(!passwordEditText.getText().toString().equalsIgnoreCase(confirmPasswordEditText.getText().toString())) {
                msg = "Mật khẩu không khớp";
            }
            if(studentIdEditText.getText().toString().length() != 10) {
                msg="Mã sinh viên phải chứa 10 kí tự";
            }

            if(phoneEditText.getText().toString().length() != 12) {
                msg="Số điện thoại phải đủ 12 số";
            }
        }
        if(!msg.isEmpty()) {
            Toast.makeText(this, msg.toString(), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
     }
}