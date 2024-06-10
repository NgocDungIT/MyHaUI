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
//        myDb = new DBHelper(this);
//        DatabaseQuery dbQuery = new DatabaseQuery(this);
//        dbQuery.addNewUser(new User(-1, "2021601239", "Nguyễn Văn Cương", "123344", "Nam", "0928343234", "", "", ""));
//        dbQuery.addNewUser(new User(-1, "2021602133", "Nguyễn Văn Giang", "abc123123", "Nam", "0962800117", "", "", ""));
//        dbQuery.addNewUser(new User(-1, "2021602134", "Nguyễn An Hậu", "abc123123", "Nam", "0962800117", "", "", ""));
//        dbQuery.addNewUser(new User(-1, "2021602135", "Lê Trọng Hiệp", "abc123123", "Nam", "0962800117", "", "", ""));
//        dbQuery.addNewUser(new User(-1, "2021602136", "Hoàng Ngọc Dũng", "abc123123", "Nam", "0962800117", "", "", ""));
//        dbQuery.addNewUser(new User(-1, "2021602137", "Nguyễn Khắc Hoàng", "abc123123", "Nam", "0962800117", "", "", ""));
//
//
//        dbQuery.addNewFriend(new Friend(-1, 1, "2021601239", "Nguyen Van Cuong", "0928343234"));
//        dbQuery.addNewFriend(new Friend(-1, 1, "2021601234", "Hoang Ngoc Dung", "0962800117"));
//        dbQuery.addNewAuthor(new Author(-1, "Vũ Thị Dương"));
//        dbQuery.addNewAuthor(new Author(-1, "Hà Mạnh Đào"));
//        dbQuery.addNewAuthor(new Author(-1, "Lê Anh Thắng"));
//        dbQuery.addNewAuthor(new Author(-1, "Phạm Thị Kim Phượng"));
//        dbQuery.addNewBook(new Book(-1, "Android", "android", 140, "Tiếng Việt", 40, 1, "Đĩa cứng cần được định dạng (format) trước khi sử dụng. Có hai mức định dạng đĩa cứng: định dạng mức thấp (lower level format) và định dạng mức cao (high level format). Định dạng mức thấp là quá trình gán địa chỉ cho các cung vật lý trên đĩa và có thể được thực hiện bởi các chức năng của BIOS. Hiện nay, hầu hết các ổ đĩa cứng đều đã được định dạng mức thấp khi xuất xưởng. Sau khi được định", "31/5/2024"));
//        dbQuery.addNewBook(new Book(-1, "Mạng máy tính", "mangmaytinh", 140, "Tiếng Việt", 40, 3, "Đĩa cứng cần được định dạng (format) trước khi sử dụng. Có hai mức định dạng đĩa cứng: định dạng mức thấp (lower level format) và định dạng mức cao (high level format). Định dạng mức thấp là quá trình gán địa chỉ cho các cung vật lý trên đĩa và có thể được thực hiện bởi các chức năng của BIOS. Hiện nay, hầu hết các ổ đĩa cứng đều đã được định dạng mức thấp khi xuất xưởng. Sau khi được định", "31/5/2024"));
//        dbQuery.addNewBook(new Book(-1, "Quản trị mạng", "quantrimang", 140, "Tiếng Việt", 40, 2, "Đĩa cứng cần được định dạng (format) trước khi sử dụng. Có hai mức định dạng đĩa cứng: định dạng mức thấp (lower level format) và định dạng mức cao (high level format). Định dạng mức thấp là quá trình gán địa chỉ cho các cung vật lý trên đĩa và có thể được thực hiện bởi các chức năng của BIOS. Hiện nay, hầu hết các ổ đĩa cứng đều đã được định dạng mức thấp khi xuất xưởng. Sau khi được định", "31/5/2024"));
//        dbQuery.addNewBook(new Book(-1, "Phân tích hệ thống", "phantichhethong", 140, "Tiếng Việt", 40, 4, "Đĩa cứng cần được định dạng (format) trước khi sử dụng. Có hai mức định dạng đĩa cứng: định dạng mức thấp (lower level format) và định dạng mức cao (high level format). Định dạng mức thấp là quá trình gán địa chỉ cho các cung vật lý trên đĩa và có thể được thực hiện bởi các chức năng của BIOS. Hiện nay, hầu hết các ổ đĩa cứng đều đã được định dạng mức thấp khi xuất xưởng. Sau khi được định", "31/5/2024"));
//        dbQuery.addNewBook(new Book(-1, "Hệ cơ sở dữ liệu", "database", 140, "Tiếng Việt", 40, 1, "Đĩa cứng cần được định dạng (format) trước khi sử dụng. Có hai mức định dạng đĩa cứng: định dạng mức thấp (lower level format) và định dạng mức cao (high level format). Định dạng mức thấp là quá trình gán địa chỉ cho các cung vật lý trên đĩa và có thể được thực hiện bởi các chức năng của BIOS. Hiện nay, hầu hết các ổ đĩa cứng đều đã được định dạng mức thấp khi xuất xưởng. Sau khi được định", "31/5/2024"));
//        dbQuery.addNewBook(new Book(-1, "Lập trình Java nâng cao", "java", 140, "Tiếng Việt", 40, 2, "Đĩa cứng cần được định dạng (format) trước khi sử dụng. Có hai mức định dạng đĩa cứng: định dạng mức thấp (lower level format) và định dạng mức cao (high level format). Định dạng mức thấp là quá trình gán địa chỉ cho các cung vật lý trên đĩa và có thể được thực hiện bởi các chức năng của BIOS. Hiện nay, hầu hết các ổ đĩa cứng đều đã được định dạng mức thấp khi xuất xưởng. Sau khi được định", "31/5/2024"));
//        dbQuery.addNewBook(new Book(-1, "Lập trình C++", "c", 140, "Tiếng Việt", 40, 3, "Đĩa cứng cần được định dạng (format) trước khi sử dụng. Có hai mức định dạng đĩa cứng: định dạng mức thấp (lower level format) và định dạng mức cao (high level format). Định dạng mức thấp là quá trình gán địa chỉ cho các cung vật lý trên đĩa và có thể được thực hiện bởi các chức năng của BIOS. Hiện nay, hầu hết các ổ đĩa cứng đều đã được định dạng mức thấp khi xuất xưởng. Sau khi được định", "31/5/2024"));
//        dbQuery.addNewBook(new Book(-1, "Lập trình Web", "web", 140, "Tiếng Việt", 40, 2, "Đĩa cứng cần được định dạng (format) trước khi sử dụng. Có hai mức định dạng đĩa cứng: định dạng mức thấp (lower level format) và định dạng mức cao (high level format). Định dạng mức thấp là quá trình gán địa chỉ cho các cung vật lý trên đĩa và có thể được thực hiện bởi các chức năng của BIOS. Hiện nay, hầu hết các ổ đĩa cứng đều đã được định dạng mức thấp khi xuất xưởng. Sau khi được định", "31/5/2024"));
//        dbQuery.addNewBook(new Book(-1, "Lập trình hướng đối tượng", "oop", 140, "Tiếng Việt", 40, 2, "Đĩa cứng cần được định dạng (format) trước khi sử dụng. Có hai mức định dạng đĩa cứng: định dạng mức thấp (lower level format) và định dạng mức cao (high level format). Định dạng mức thấp là quá trình gán địa chỉ cho các cung vật lý trên đĩa và có thể được thực hiện bởi các chức năng của BIOS. Hiện nay, hầu hết các ổ đĩa cứng đều đã được định dạng mức thấp khi xuất xưởng. Sau khi được định", "31/5/2024"));
//        dbQuery.addNewBook(new Book(-1, "An toàn bảo mật thông tin", "atbm", 140, "Tiếng Việt", 40, 1, "Đĩa cứng cần được định dạng (format) trước khi sử dụng. Có hai mức định dạng đĩa cứng: định dạng mức thấp (lower level format) và định dạng mức cao (high level format). Định dạng mức thấp là quá trình gán địa chỉ cho các cung vật lý trên đĩa và có thể được thực hiện bởi các chức năng của BIOS. Hiện nay, hầu hết các ổ đĩa cứng đều đã được định dạng mức thấp khi xuất xưởng. Sau khi được định", "31/5/2024"));
//        dbQuery.addNewBook(new Book(-1, "Kiến trúc máy tính và hệ điều hành", "ktmt", 140, "Tiếng Việt", 40, 4, "Đĩa cứng cần được định dạng (format) trước khi sử dụng. Có hai mức định dạng đĩa cứng: định dạng mức thấp (lower level format) và định dạng mức cao (high level format). Định dạng mức thấp là quá trình gán địa chỉ cho các cung vật lý trên đĩa và có thể được thực hiện bởi các chức năng của BIOS. Hiện nay, hầu hết các ổ đĩa cứng đều đã được định dạng mức thấp khi xuất xưởng. Sau khi được định", "31/5/2024"));
//
//        dbQuery.addNewOrder(new Order(-1, 1, 1, 1, "1/06/2024", "8/07/2024", 0))      ;
//        dbQuery.updateUser(new User(-1, "2021601239", "Nguyễn Văn Cương", "123344", "Nam", "0928343234", "DHCNTT02", "CNTT02", "Hải Dương"));
    }
}