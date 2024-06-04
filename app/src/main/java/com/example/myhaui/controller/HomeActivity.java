package com.example.myhaui.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myhaui.CustomListView.BookViewAdapter;
import com.example.myhaui.Database.DatabaseQuery;
import com.example.myhaui.R;
import com.example.myhaui.model.Author;
import com.example.myhaui.model.Book;
import com.example.myhaui.model.Friend;
import com.example.myhaui.model.Order;
import com.example.myhaui.model.User;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ImageButton btnSearch ,btnProfile;
    Button btnFriend, btnBorrow;
    EditText txtSearch;
    ListView listView;
    List<Book> listBook;
    BookViewAdapter bookViewAdapter;
    DatabaseQuery dbHelper;
    private void initView(){
        dbHelper = new DatabaseQuery(this);
        btnProfile = findViewById(R.id.home_btn_profile);
        btnSearch = findViewById(R.id.home_btn_search);
        txtSearch = findViewById(R.id.home_txt_search);
        listView = findViewById(R.id.home_btn_listview);
        btnFriend = findViewById(R.id.home_btn_friend);
        btnBorrow = findViewById(R.id.home_btn_borrow);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();

        listBook = dbHelper.getAllBooks();
        bookViewAdapter = new BookViewAdapter(this, listBook);
        listView.setAdapter(bookViewAdapter);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }

        });
//Search Book
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtSearch.getText().toString().isEmpty()){
                    Toast.makeText(HomeActivity.this, "Nhập sách cần tìm kiếm", Toast.LENGTH_SHORT).show();
                }else {
                    List<Book> searchBooks = dbHelper.searchBooks(txtSearch.getText().toString());
                    if(searchBooks.size() > 0) {

                    ArrayList<Book> results = new ArrayList<>(searchBooks) ;
                    Intent intent = new Intent(HomeActivity.this, SearchBookActivity.class);
                    intent.putExtra("searchBooks", results);
                    startActivity(intent);
                    }else {
                        Toast.makeText(HomeActivity.this, "Sach khong ton tai", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, FriendListActivity.class);
                startActivity(intent);
            }

        });

        btnBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BorrowBooksListActivity.class);
                startActivity(intent);
            }

        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy dữ liệu từ EditText
                int bookID = listBook.get(position).get_id();

                // Tạo Intent để chuyển sang SecondActivity
                Intent intent = new Intent(HomeActivity.this, BookDetailActivity.class);

                // Đưa dữ liệu vào Intent
                intent.putExtra("BOOK_ID", bookID);

                // Bắt đầu SecondActivity
                startActivity(intent);
            }
        });
//        dbHelper.addNewUser(new User(-1, "2021601239", "Nguyễn Văn Cương", "123344", "Nam", "0928343234", "", "", ""));
//        dbHelper.addNewUser(new User(-1, "2021602133", "Nguyễn Văn Giang", "abc123123", "Nam", "0962800117", "", "", ""));
//        dbHelper.addNewUser(new User(-1, "2021602134", "Nguyễn An Hậu", "abc123123", "Nam", "0962800117", "", "", ""));
//        dbHelper.addNewUser(new User(-1, "2021602135", "Lê Trọng Hiệp", "abc123123", "Nam", "0962800117", "", "", ""));
//        dbHelper.addNewUser(new User(-1, "2021602136", "Hoàng Ngọc Dũng", "abc123123", "Nam", "0962800117", "", "", ""));
//        dbHelper.addNewUser(new User(-1, "2021602137", "Nguyễn Khắc Hoàng", "abc123123", "Nam", "0962800117", "", "", ""));
//
//
//        dbHelper.addNewFriend(new Friend(-1, 1, "2021601239", "Nguyen Van Cuong", "0928343234"));
//        dbHelper.addNewFriend(new Friend(-1, 1, "2021601234", "Hoang Ngoc Dung", "0962800117"));
//        dbHelper.addNewAuthor(new Author(-1, "Vũ Thị Dương"));
//        dbHelper.addNewAuthor(new Author(-1, "Hà Mạnh Đào"));
//        dbHelper.addNewAuthor(new Author(-1, "Lê Anh Thắng"));
//        dbHelper.addNewAuthor(new Author(-1, "Phạm Thị Kim Phượng"));
//        dbHelper.addNewBook(new Book(-1, "Android", "android", 140, "Tiếng Việt", 40, 1, "Đĩa cứng cần được định dạng (format) trước khi sử dụng. Có hai mức định dạng đĩa cứng: định dạng mức thấp (lower level format) và định dạng mức cao (high level format). Định dạng mức thấp là quá trình gán địa chỉ cho các cung vật lý trên đĩa và có thể được thực hiện bởi các chức năng của BIOS. Hiện nay, hầu hết các ổ đĩa cứng đều đã được định dạng mức thấp khi xuất xưởng. Sau khi được định", "31/5/2024"));
//        dbHelper.addNewBook(new Book(-1, "Mạng máy tính", "mangmaytinh", 140, "Tiếng Việt", 40, 3, "Đĩa cứng cần được định dạng (format) trước khi sử dụng. Có hai mức định dạng đĩa cứng: định dạng mức thấp (lower level format) và định dạng mức cao (high level format). Định dạng mức thấp là quá trình gán địa chỉ cho các cung vật lý trên đĩa và có thể được thực hiện bởi các chức năng của BIOS. Hiện nay, hầu hết các ổ đĩa cứng đều đã được định dạng mức thấp khi xuất xưởng. Sau khi được định", "31/5/2024"));
//        dbHelper.addNewBook(new Book(-1, "Quản trị mạng", "quantrimang", 140, "Tiếng Việt", 40, 2, "Đĩa cứng cần được định dạng (format) trước khi sử dụng. Có hai mức định dạng đĩa cứng: định dạng mức thấp (lower level format) và định dạng mức cao (high level format). Định dạng mức thấp là quá trình gán địa chỉ cho các cung vật lý trên đĩa và có thể được thực hiện bởi các chức năng của BIOS. Hiện nay, hầu hết các ổ đĩa cứng đều đã được định dạng mức thấp khi xuất xưởng. Sau khi được định", "31/5/2024"));
//        dbHelper.addNewBook(new Book(-1, "Phân tích hệ thống", "phantichhethong", 140, "Tiếng Việt", 40, 4, "Đĩa cứng cần được định dạng (format) trước khi sử dụng. Có hai mức định dạng đĩa cứng: định dạng mức thấp (lower level format) và định dạng mức cao (high level format). Định dạng mức thấp là quá trình gán địa chỉ cho các cung vật lý trên đĩa và có thể được thực hiện bởi các chức năng của BIOS. Hiện nay, hầu hết các ổ đĩa cứng đều đã được định dạng mức thấp khi xuất xưởng. Sau khi được định", "31/5/2024"));
//        dbHelper.addNewBook(new Book(-1, "Hệ cơ sở dữ liệu", "database", 140, "Tiếng Việt", 40, 1, "Đĩa cứng cần được định dạng (format) trước khi sử dụng. Có hai mức định dạng đĩa cứng: định dạng mức thấp (lower level format) và định dạng mức cao (high level format). Định dạng mức thấp là quá trình gán địa chỉ cho các cung vật lý trên đĩa và có thể được thực hiện bởi các chức năng của BIOS. Hiện nay, hầu hết các ổ đĩa cứng đều đã được định dạng mức thấp khi xuất xưởng. Sau khi được định", "31/5/2024"));
//        dbHelper.addNewBook(new Book(-1, "Lập trình Java nâng cao", "java", 140, "Tiếng Việt", 40, 2, "Đĩa cứng cần được định dạng (format) trước khi sử dụng. Có hai mức định dạng đĩa cứng: định dạng mức thấp (lower level format) và định dạng mức cao (high level format). Định dạng mức thấp là quá trình gán địa chỉ cho các cung vật lý trên đĩa và có thể được thực hiện bởi các chức năng của BIOS. Hiện nay, hầu hết các ổ đĩa cứng đều đã được định dạng mức thấp khi xuất xưởng. Sau khi được định", "31/5/2024"));
//        dbHelper.addNewBook(new Book(-1, "Lập trình C++", "c", 140, "Tiếng Việt", 40, 3, "Đĩa cứng cần được định dạng (format) trước khi sử dụng. Có hai mức định dạng đĩa cứng: định dạng mức thấp (lower level format) và định dạng mức cao (high level format). Định dạng mức thấp là quá trình gán địa chỉ cho các cung vật lý trên đĩa và có thể được thực hiện bởi các chức năng của BIOS. Hiện nay, hầu hết các ổ đĩa cứng đều đã được định dạng mức thấp khi xuất xưởng. Sau khi được định", "31/5/2024"));
//        dbHelper.addNewBook(new Book(-1, "Lập trình Web", "web", 140, "Tiếng Việt", 40, 2, "Đĩa cứng cần được định dạng (format) trước khi sử dụng. Có hai mức định dạng đĩa cứng: định dạng mức thấp (lower level format) và định dạng mức cao (high level format). Định dạng mức thấp là quá trình gán địa chỉ cho các cung vật lý trên đĩa và có thể được thực hiện bởi các chức năng của BIOS. Hiện nay, hầu hết các ổ đĩa cứng đều đã được định dạng mức thấp khi xuất xưởng. Sau khi được định", "31/5/2024"));
//        dbHelper.addNewBook(new Book(-1, "Lập trình hướng đối tượng", "oop", 140, "Tiếng Việt", 40, 2, "Đĩa cứng cần được định dạng (format) trước khi sử dụng. Có hai mức định dạng đĩa cứng: định dạng mức thấp (lower level format) và định dạng mức cao (high level format). Định dạng mức thấp là quá trình gán địa chỉ cho các cung vật lý trên đĩa và có thể được thực hiện bởi các chức năng của BIOS. Hiện nay, hầu hết các ổ đĩa cứng đều đã được định dạng mức thấp khi xuất xưởng. Sau khi được định", "31/5/2024"));
//        dbHelper.addNewBook(new Book(-1, "An toàn bảo mật thông tin", "atbm", 140, "Tiếng Việt", 40, 1, "Đĩa cứng cần được định dạng (format) trước khi sử dụng. Có hai mức định dạng đĩa cứng: định dạng mức thấp (lower level format) và định dạng mức cao (high level format). Định dạng mức thấp là quá trình gán địa chỉ cho các cung vật lý trên đĩa và có thể được thực hiện bởi các chức năng của BIOS. Hiện nay, hầu hết các ổ đĩa cứng đều đã được định dạng mức thấp khi xuất xưởng. Sau khi được định", "31/5/2024"));
//        dbHelper.addNewBook(new Book(-1, "Kiến trúc máy tính và hệ điều hành", "ktmt", 140, "Tiếng Việt", 40, 4, "Đĩa cứng cần được định dạng (format) trước khi sử dụng. Có hai mức định dạng đĩa cứng: định dạng mức thấp (lower level format) và định dạng mức cao (high level format). Định dạng mức thấp là quá trình gán địa chỉ cho các cung vật lý trên đĩa và có thể được thực hiện bởi các chức năng của BIOS. Hiện nay, hầu hết các ổ đĩa cứng đều đã được định dạng mức thấp khi xuất xưởng. Sau khi được định", "31/5/2024"));
//
//        dbHelper.addNewOrder(new Order(-1, 1, 1, 1, "1/06/2024", "8/07/2024", 0));
    }
}