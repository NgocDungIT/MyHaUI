package com.example.myhaui.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myhaui.Database.DatabaseQuery;
import com.example.myhaui.R;
import com.example.myhaui.model.User;

public class EditProfileActivity extends AppCompatActivity {
    ImageButton btnBack;
    Button btnConfirmEdit;
    EditText editFullName, editCode, editPassword, editPhoneNumber, editClass, editFaculty, editAddress;
    RadioGroup rEditGender;
    RadioButton erNam, erNu;
    DatabaseQuery dbHelper;
    int idUser;

    private void initView() {
        dbHelper = new DatabaseQuery(this);
        btnBack = findViewById(R.id.edit_profile_btn_back);
        btnConfirmEdit = findViewById(R.id.btn_confirm_edit);
        editFullName = findViewById(R.id.txt_edit_fullName);
        editCode = findViewById(R.id.txt_edit_Code);
        editPassword = findViewById(R.id.txt_edit_Password);
        editPhoneNumber = findViewById(R.id.txt_edit_Phonenumber);
        editClass = findViewById(R.id.txt_edit_Class);
        editFaculty = findViewById(R.id.txt_edit_Faculty);
        editAddress = findViewById(R.id.txt_edit_Address);
        rEditGender = findViewById(R.id.rdGender);
        erNam = findViewById(R.id.erNam);
        erNu = findViewById(R.id.erNu);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();

        try {
            SharedPreferences sharedPreferences = getSharedPreferences("Information_User", Context.MODE_PRIVATE);
            String userCode = sharedPreferences.getString("userCode", "");

            User currentUser = dbHelper.getUserByCode(userCode);
            idUser = currentUser.get_id();
            editFullName.setText(currentUser.getFullName());
            editCode.setText(currentUser.getCode());
            editPassword.setText(currentUser.getPassword());
            editPhoneNumber.setText(currentUser.getPhoneNumber());
            editClass.setText(currentUser.getClassName());
            editFaculty.setText(currentUser.getFaculty());
            editAddress.setText(currentUser.getAddress());
            if(currentUser.getGender().equals(erNam.getText().toString())) {
                erNam.setChecked(true);
            }else {
                erNu.setChecked(true);
            }
        } catch (Exception ex) {
            Log.d("Profile:", "onCreate: ", ex);
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

        btnConfirmEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idxRad = rEditGender.getCheckedRadioButtonId();
                RadioButton rad = findViewById(idxRad);
                String gender = rad.getText().toString();
                if(CheckValid()){
                    User editedUser = new User(idUser, editCode.getText().toString(), editFullName.getText().toString(), editPassword.getText().toString(),gender ,  editPhoneNumber.getText().toString(), editClass.getText().toString(), editFaculty.getText().toString(), editAddress.getText().toString());
                    long rowCount = dbHelper.updateUser(editedUser);
                    if(rowCount > 0) {
                        Toast.makeText(EditProfileActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public boolean CheckValid() {
        String msg= "";
        if(editPhoneNumber.getText().toString().isEmpty() || editClass.getText().toString().isEmpty() || editFaculty.getText().toString().isEmpty() || editAddress.getText().toString().isEmpty() || editPassword.getText().toString().isEmpty()) {
            msg += "Nhập đầy đủ thông tin";
        }else {
            if(editPhoneNumber.getText().toString().length() != 10) {
                msg="Số điện thoại phải đủ 10 số";
            }else if(editPassword.getText().toString().length() < 7) {
                msg="Mật khẩu phải trên 6 kí tự. ";
            }
        }
        if(!msg.isEmpty()) {
            Toast.makeText(this, msg.toString(), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}