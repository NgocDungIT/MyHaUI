package com.example.myhaui.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myhaui.Database.DatabaseQuery;
import com.example.myhaui.R;
import com.example.myhaui.model.User;

public class FriendDetailActivity extends AppCompatActivity {
    ImageView avatar;
    ImageButton btnBack;
    TextView profileName, profileCode, profileClass, profileSex, profileAddress, profilePhone, profileKhoa;
    DatabaseQuery dbHelper;

    protected void initView(){
        dbHelper = new DatabaseQuery(this);
        avatar = findViewById(R.id.pfriend_detail_avt);
        profileName = findViewById(R.id.friend_detail_name);
        profileCode = findViewById(R.id.friend_detail_code);
        profileClass = findViewById(R.id.friend_detail_class);
        profileKhoa = findViewById(R.id.friend_detail_khoa);
        profilePhone = findViewById(R.id.friend_detail_phone);
        profileSex = findViewById(R.id.friend_detail_sex);
        profileAddress = findViewById(R.id.friend_detail_address);
        btnBack = findViewById(R.id.friend_detail_btn_back);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_friend_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();

        try {
            // Nhận Intent đã gọi Activity này
            Intent intent = getIntent();

            // Nhận dữ liệu từ Intent
            String userCode = intent.getStringExtra("FRIEND_CODE");

            User currentUser = dbHelper.getUserByCode(userCode);

            profileName.setText(currentUser.getFullName());
            profileCode.setText(currentUser.getCode());
            profileClass.setText(currentUser.getCode());
            profileKhoa.setText(currentUser.getFaculty());
            profilePhone.setText(currentUser.getPhoneNumber());
            profileSex.setText(currentUser.getGender());
            profileAddress.setText(currentUser.getAddress());
        }catch (Exception ex)
        {
            Log.d("Profile:", "onCreate: ", ex);
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}