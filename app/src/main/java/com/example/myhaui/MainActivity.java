package com.example.myhaui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myhaui.Database.DBHelper;
import com.example.myhaui.Database.DatabaseQuery;
import com.example.myhaui.model.Author;
import com.example.myhaui.model.Book;
import com.example.myhaui.model.Friend;
import com.example.myhaui.model.Order;
import com.example.myhaui.model.User;

public class MainActivity extends AppCompatActivity {

    DBHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        myDb = new DBHelper(this);
        DatabaseQuery dbQuery = new DatabaseQuery(this);
//        dbQuery.addNewUser(new User(-1, "2021601239", "Nguyen Van Cuong", "123344", "Nam", "0928343234", "", "", ""));
//        dbQuery.addNewUser(new User(-1, "2021602133", "Nguyen Van Giang", "abc123123", "Nam", "0962800117", "", "", ""));
//        dbQuery.addNewUser(new User(-1, "2021602134", "Hoang Ngoc Dung", "abc123123", "Nam", "0962800117", "", "", ""));
//        dbQuery.addNewFriend(new Friend(-1, 1, "2021601239", "Nguyen Van Cuong", "0928343234"));
//        dbQuery.addNewFriend(new Friend(-1, 1, "2021601234", "Hoang Ngoc Dung", "0962800117"));
//        dbQuery.addNewAuthor(new Author(-1, "Vũ Thị Dương"));
//        dbQuery.addNewAuthor(new Author(-1, "Hà Mạnh Đào"));
//        dbQuery.addNewAuthor(new Author(-1, "Lê Anh Thắng"));
//        dbQuery.addNewAuthor(new Author(-1, "Phạm Thị Kim Phượng"));
//        dbQuery.addNewBook(new Book(-1, "Giáo trình Android", "android.png", 140, "Tiếng Việt", 40, 1, "abc abc", "31/5/2024"));
//        dbQuery.addNewBook(new Book(-1, "Giáo trình Mạng máy tính", "mang.png", 140, "Tiếng Việt", 40, 3, "abc abc", "31/5/2024"));
//        dbQuery.addNewBook(new Book(-1, "Giáo trình Quản trị mạng", "qr.png", 140, "Tiếng Việt", 40, 2, "abc abc", "31/5/2024"));
//        dbQuery.addNewBook(new Book(-1, "Giáo trình Phân tích hệ thống", "desg.png", 140, "Tiếng Việt", 40, 4, "abc abc", "31/5/2024"));
//        dbQuery.addNewOrder(new Order(-1, 1, 1, 1, "1/06/2024", "8/07/2024", 0))      ;
//        dbQuery.updateUser(new User(-1, "2021601239", "Nguyễn Văn Cương", "123344", "Nam", "0928343234", "DHCNTT02", "CNTT02", "Hải Dương"));
    }
}