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

public class ProfileActivity extends AppCompatActivity {
    ImageView avatar;
    ImageButton btnBack;
    TextView profileName, profileCode, profileClass, profileSex, profileAddress, profilePhone, profileKhoa;
    Button btnUpdate;
    DatabaseQuery dbHelper;

    protected void initView(){
        dbHelper = new DatabaseQuery(this);
        avatar = findViewById(R.id.profile_avt);
        profileName = findViewById(R.id.profile_name);
        profileCode = findViewById(R.id.profile_code);
        profileClass = findViewById(R.id.profile_class);
        profileKhoa = findViewById(R.id.profile_khoa);
        profilePhone = findViewById(R.id.profile_phone);
        profileSex = findViewById(R.id.profile_sex);
        profileAddress = findViewById(R.id.profile_address);
        btnUpdate = findViewById(R.id.profile_btn_update);
        btnBack = findViewById(R.id.profile_btn_back);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();
        loadData();


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void loadData(){
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("Information_User", Context.MODE_PRIVATE);
            String userCode = sharedPreferences.getString("userCode", "");

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload data when Activity is resumed
        loadData();
    }

}