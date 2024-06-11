package com.example.myhaui.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myhaui.CustomListView.BorrowViewAdapter;
import com.example.myhaui.Database.DatabaseQuery;
import com.example.myhaui.R;
import com.example.myhaui.model.Order;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BorrowBooksListActivity extends AppCompatActivity {
    public static final String TAG = "BorrowingBooksList";
    ImageButton btnBack;
    Button btnAll, btnPaid, btnBorrowing, btnHome, btnFriend;
    ListView listView;
    DatabaseQuery dbHelper;
    BorrowViewAdapter orderArrayAdapter;
    List<Order> orderList = new ArrayList<Order>();
    List<Order> orderPaid = new ArrayList<Order>();
    List<Order> orderBorrowing = new ArrayList<Order>();

    int userID;

    private void initView() {
        dbHelper = new DatabaseQuery(this);
        btnBack = findViewById(R.id.borrow_list_btn_back);
        btnAll = findViewById(R.id.borrow_list_btn_all);
        btnPaid = findViewById(R.id.borrow_list_btn_paid);
        btnBorrowing = findViewById(R.id.borrow_list_btn_borrowing);
        listView = findViewById(R.id.borrow_list_list_view);
        btnHome = findViewById(R.id.borrows_btn_home);
        btnFriend = findViewById(R.id.borrows_btn_friend);
        dbHelper = new DatabaseQuery(this);
        loadData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_borrow_books_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();
        SharedPreferences sharedPreferences = getSharedPreferences("Information_User", Context.MODE_PRIVATE);
        userID = sharedPreferences.getInt("userID", -1);
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });

        btnPaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    orderPaid.clear();

                    for (int i = 0; i < orderList.size(); i++) {
                        if (orderList.get(i).getIs_returned() == 1) {
                            orderPaid.add(orderList.get(i));
                        }
                    }
                    orderArrayAdapter = new BorrowViewAdapter(BorrowBooksListActivity.this, orderPaid);
                    listView.setAdapter(orderArrayAdapter);
                } catch (Exception ex) {
                    Log.e(TAG, "onClick: " + ex.getMessage());
                }

            }
        });

        btnBorrowing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    orderBorrowing.clear();

                    for (int i = 0; i < orderList.size(); i++) {
                        if (orderList.get(i).getIs_returned() == 0) {
                            orderBorrowing.add(orderList.get(i));
                        }
                    }
                    orderArrayAdapter = new BorrowViewAdapter(BorrowBooksListActivity.this, orderBorrowing);
                    listView.setAdapter(orderArrayAdapter);

                } catch (Exception ex) {
                    Log.e(TAG, "onClick: " + ex.getMessage());
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BorrowBooksListActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        btnFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BorrowBooksListActivity.this, FriendListActivity.class);
                startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy dữ liệu từ EditText
                int orderID = orderList.get(position).get_id();

                // Tạo Intent để chuyển sang SecondActivity
                Intent intent = new Intent(BorrowBooksListActivity.this, BorrowDetailActivity.class);

                // Đưa dữ liệu vào Intent
                intent.putExtra("ORDER_ID", orderID);

                // Bắt đầu SecondActivity
//                startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });

    }

    public void loadData() {
        try {
            orderList.clear();
            orderList = dbHelper.getAllOrdering(userID);

            orderArrayAdapter = new BorrowViewAdapter(this, orderList);
            listView.setAdapter(orderArrayAdapter);
            orderArrayAdapter.notifyDataSetChanged();

        } catch (Exception ex) {
            Log.d("BorrowList", "loadData() returned: " + ex.getStackTrace());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // Cập nhật lại danh sách khi quay lại từ BorrowDetailActivity
                loadData();
            }
        }
    }
}