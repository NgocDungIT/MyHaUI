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
import com.example.myhaui.model.Author;
import com.example.myhaui.model.Book;
import com.example.myhaui.model.Order;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BookDetailActivity extends AppCompatActivity {

    ImageView bookImg;
    TextView bookName, bookAuthor, bookPages, bookLanguage, bookPublish, bookRemaining, bookDecription, bookQuantity;
    Button btnDecrease, btnIncrease, btnBorrow;
    ImageButton btnBack;
    DatabaseQuery dbHelper;
    int quantityBorrow;
    Book book;
    Author author;

    private void initView(){
        dbHelper = new DatabaseQuery(this);
        bookImg = findViewById(R.id.book_detail_img);
        bookName = findViewById(R.id.book_detail_name);
        bookAuthor = findViewById(R.id.book_detail_author);
        bookPages = findViewById(R.id.book_detail_pages);
        bookLanguage = findViewById(R.id.book_detail_languge);
        bookPublish = findViewById(R.id.book_detail_publish);
        bookRemaining = findViewById(R.id.book_detail_remaining);
        bookDecription = findViewById(R.id.book_detail_decription);
        bookQuantity = findViewById(R.id.book_detail_quantity);
        btnDecrease = findViewById(R.id.book_detail_btn_decrease);
        btnIncrease = findViewById(R.id.book_detail_btn_increase);
        btnBorrow = findViewById(R.id.book_detail_btn_borrow);
        btnBack = findViewById(R.id.book_detail_back);

        quantityBorrow = Integer.valueOf(bookQuantity.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();

        // Nhận Intent đã gọi Activity này
        Intent intent = getIntent();

        // Nhận dữ liệu từ Intent
        int idBook = intent.getIntExtra("BOOK_ID", -1);
        book = dbHelper.getBookById(idBook);
        int idAuthor = book.getAuthor_id();
        author =  dbHelper.getAthorByID(idAuthor);

        String imageName = book.getImage().toString().trim();
//        String imageName = "mangmaytinh";
        int imageResId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        bookImg.setImageResource(imageResId);
        bookName.setText(book.getName());
        bookAuthor.setText(author.getAuthor_name());
        bookPages.setText(String.valueOf(book.getPages()));
        bookLanguage.setText(book.getLanguage());
        bookPublish.setText(book.getPublished_date());
        bookRemaining.setText(String.valueOf(book.getQuantity()));
        bookDecription.setText(book.getDescription());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++quantityBorrow;
                bookQuantity.setText(quantityBorrow+"");
            }
        });

        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantityBorrow == 1){
                    Toast.makeText(BookDetailActivity.this, "Số lượng mượn phải lớn hơn 0.", Toast.LENGTH_SHORT).show();
                    return;
                }
                --quantityBorrow;
                bookQuantity.setText(quantityBorrow+"");
            }
        });

        btnBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("Information_User", Context.MODE_PRIVATE);
                int userID = sharedPreferences.getInt("userID",  1);

                Date dateNow = new Date();
                SimpleDateFormat dateTimeFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
                String oderDate = dateTimeFormat.format(dateNow);

                if(quantityBorrow == 0){
                    Toast.makeText(BookDetailActivity.this, "Số lượng mượn phải lớn hơn 0.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(quantityBorrow > book.getQuantity()){
                    Toast.makeText(BookDetailActivity.this, "Số lượng sách không đủ để mượn.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Order order = new Order(-1, userID, book.get_id(), quantityBorrow, "Chưa trả", oderDate, 0);
                dbHelper.addNewOrder(order);

                Toast.makeText(BookDetailActivity.this, "Mượn thành công.", Toast.LENGTH_SHORT).show();

                dbHelper.updateQuantityBook(book.get_id(), book.getQuantity() - quantityBorrow);
                bookRemaining.setText((book.getQuantity() - quantityBorrow)+"");
                book = dbHelper.getBookById(book.get_id());
            }
        });
    }
}