package com.example.myhaui.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myhaui.CustomListView.BookViewAdapter;
import com.example.myhaui.R;
import com.example.myhaui.model.Book;

import java.util.ArrayList;

public class SearchBookActivity extends AppCompatActivity {
    ImageButton btnBack;
    ListView lvResult;
    BookViewAdapter bookViewAdapter;
    public void initView() {
        btnBack = findViewById(R.id.search_book_back);
        lvResult = findViewById(R.id.search_book_list_view);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();
        Intent intent = getIntent();
        ArrayList<Book> results = (ArrayList<Book>) intent.getSerializableExtra("searchBooks");
        bookViewAdapter = new BookViewAdapter(this, results);
        lvResult.setAdapter(bookViewAdapter);


        lvResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 // Lấy dữ liệu từ EditText
                int bookID = results.get(position).get_id();

                // Tạo Intent để chuyển sang SecondActivity
                Intent intent = new Intent(SearchBookActivity.this, BookDetailActivity.class);

                // Đưa dữ liệu vào Intent
                intent.putExtra("BOOK_ID", bookID);

                // Bắt đầu SecondActivity
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
}