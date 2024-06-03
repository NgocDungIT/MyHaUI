package com.example.myhaui.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myhaui.CustomListView.BorrowViewAdapter;
import com.example.myhaui.CustomListView.FriendViewAdapter;
import com.example.myhaui.Database.DBHelper;
import com.example.myhaui.Database.DatabaseQuery;
import com.example.myhaui.R;
import com.example.myhaui.model.Friend;
import com.example.myhaui.model.User;

import java.util.List;

public class FriendListActivity extends AppCompatActivity {

    ImageButton btnBack, btnSearch;
    Button btnAdd;
    EditText txtSearch;
    ListView listView;
    Dialog dialog;
    DatabaseQuery dbHelper;
    FriendViewAdapter friendArrayAdapter;
    List<Friend> friendList = null;
    int userID;

    private void initView(){
        dbHelper = new DatabaseQuery(this);
        btnBack = findViewById(R.id.friend_list_btn_back);
        btnSearch = findViewById(R.id.friend_list_btn_search);
        btnAdd = findViewById(R.id.friend_list_btn_add);
        txtSearch = findViewById(R.id.friend_list_txt_search);
        listView = findViewById(R.id.friend_list_list_view);
        dialog = new Dialog(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_friend_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();

        try {
            SharedPreferences sharedPreferences = getSharedPreferences("Information_User", Context.MODE_PRIVATE);
            userID = sharedPreferences.getInt("userID", -1);

            friendList = dbHelper.getAllFriendOfUser(userID);
            friendArrayAdapter = new FriendViewAdapter(this, friendList);
            listView.setAdapter(friendArrayAdapter);
        }catch (Exception ex){
            Log.d("Friend List:", "onCreate: " + ex);
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.dialog_layout);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

                EditText dialogCode = dialog.findViewById(R.id.dialog_txtCode);
                Button dialogBtnAdd = dialog.findViewById(R.id.dialog_btnAdd);
                Button  dialogBtnCancel = dialog.findViewById(R.id.dialog_btnCancel);


                dialogBtnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialogBtnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(dialogCode.getText().toString().isEmpty()){
                            Toast.makeText(FriendListActivity.this, "Vui lòng nhập mã sinh viên cần kết bạn.", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            User user = dbHelper.getUserByCode(dialogCode.getText().toString());
                            if(user == null){
                                Toast.makeText(FriendListActivity.this, "Không tồn tại sinh viên kết bạn. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                                return;
                            }else {
                                Friend check = dbHelper.getFriendByCode(dialogCode.getText().toString(),userID);
                                if(check != null){
                                    Toast.makeText(FriendListActivity.this, "Đã kết bạn với sinh viên này. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                                    return;
                                }else{
                                    Friend newFriend = new Friend(-1, userID, user.getCode(), user.getFullName(), user.getPhoneNumber());
                                    dbHelper.addNewFriend(newFriend);
                                    friendList.clear();
                                    friendList = dbHelper.getAllFriendOfUser(userID);
                                    friendArrayAdapter = new FriendViewAdapter(FriendListActivity.this, friendList);
                                    listView.setAdapter(friendArrayAdapter);
                                    Toast.makeText(FriendListActivity.this, "Kết bạn thành công.", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                            }
                        }
                    }
                });

                dialog.show();
            }
        });

        // Tìm Lỗi
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtSearch.getText().toString().isEmpty()){
                    friendList.clear();
                    friendList = dbHelper.getAllFriendOfUser(userID);
                    friendArrayAdapter = new FriendViewAdapter(FriendListActivity.this, friendList);
                    listView.setAdapter(friendArrayAdapter);
                    friendArrayAdapter.notifyDataSetChanged();
                }else {
                    friendList.clear();
                    friendList = dbHelper.searchFriendOfUser(userID, txtSearch.getText().toString());
                    friendArrayAdapter = new FriendViewAdapter(FriendListActivity.this, friendList);
                    listView.setAdapter(friendArrayAdapter);
                    friendArrayAdapter.notifyDataSetChanged();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy dữ liệu từ EditText
                String userCode = friendList.get(position).getCode();

                // Tạo Intent để chuyển sang SecondActivity
                Intent intent = new Intent(FriendListActivity.this, FriendDetailActivity.class);

                // Đưa dữ liệu vào Intent
                intent.putExtra("FRIEND_CODE", userCode);

                // Bắt đầu SecondActivity
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FriendListActivity.this);
                builder.setMessage("Bạn có chắc chắn xóa bạn không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dbHelper.deleteFriendByUser(userID, friendList.get(position).getCode());
                        friendList.remove(position);
                        friendArrayAdapter.notifyDataSetChanged();
                        Toast.makeText(FriendListActivity.this, "Xóa thành công.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });
    }
}