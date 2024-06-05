package com.example.myhaui.controller;

import android.content.Intent;
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
import com.example.myhaui.model.Author;
import com.example.myhaui.model.Book;
import com.example.myhaui.model.Order;
import com.example.myhaui.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BorrowDetailActivity extends AppCompatActivity {

    ImageView bookImg;
    TextView bookName, bookAuthor, bookOderDate, bookDueDate, bookQuantity;
    Button btnDue;
    ImageButton btnBack;
    DatabaseQuery dbHelper;

    Order order;
    Book book;


    private void initView(){
        dbHelper = new DatabaseQuery(this);
        bookImg = findViewById(R.id.borrow_detail_img);
        bookName = findViewById(R.id.borrow_detail_name);
        bookAuthor = findViewById(R.id.borrow_detail_author);
        bookOderDate = findViewById(R.id.borrow_detail_oder);
        bookDueDate = findViewById(R.id.borrow_detail_due);
        bookQuantity = findViewById(R.id.borrow_detail_quantity);
        btnBack = findViewById(R.id.borrow_detail_back);
        btnDue = findViewById(R.id.borrow_detail_btn_due);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_borrow_detail);
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
            int orderID = intent.getIntExtra("ORDER_ID", -1);

            order = dbHelper.getOrderById(orderID);

            if(order.getIs_returned() == 1){
                btnDue.setVisibility(View.GONE);
            }

            book = dbHelper.getBookById(order.getBook_id());
            Author author = dbHelper.getAthorByID(book.getAuthor_id());
            String imageName = book.getImage().toString().trim();
            int imageResId = getResources().getIdentifier(imageName, "drawable", getPackageName());
            bookImg.setImageResource(imageResId);
            bookName.setText(book.getName().toString());
            bookAuthor.setText(author.getAuthor_name().toString());
            bookQuantity.setText(String.valueOf(order.getQuantity()));
            bookDueDate.setText(order.getDue_date());
            bookOderDate.setText(order.getOrder_date());

            btnDue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Date dateNow = new Date();
                    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
                    String dueDate = dateTimeFormat.format(dateNow);

                    dbHelper.updateOrder(orderID, dueDate);
                    int totalBook = order.getQuantity() + book.getQuantity();
                    dbHelper.updateQuantityBook(book.get_id(), totalBook);

                    bookDueDate.setText(dueDate);
                    btnDue.setVisibility(View.GONE);
                    Toast.makeText(BorrowDetailActivity.this, "Trả sách thành công.", Toast.LENGTH_SHORT).show();
                }
            });

            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });



        }catch (Exception ex){
            Log.d("Borrow Detail: ", "onCreate: " + ex);
        }

    }
}