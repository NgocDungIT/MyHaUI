package com.example.myhaui.Util;

public class Config {
    // database name
    public static final String DATABASE_NAME = "MyHaui.db";

    //    Table user
    public static final String TABLE_USER = "Users";
    public static final String COLUMN_USER_ID = "_id";
    public static final String COLUMN_USER_CODE = "code";
    public static final String COLUMN_USER_NAME = "full_name";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_GENDER = "gender";
    public static final String COLUMN_USER_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_USER_CLASS = "class";
    public static final String COLUMN_USER_FACULTY = "faculty";
    public static final String COLUMN_USER_ADDRESS = "address";

    //    Table friend
    public static final String TABLE_FRIEND = "Friends";
    public static final String COLUMN_FRIEND_ID = "_id";
    public static final String COLUMN_FRIEND_USERID = "user_id";
    public static final String COLUMN_FRIEND_CODE = "code";
    public static final String COLUMN_FRIEND_NAME = "full_name";
    public static final String COLUMN_FRIEND_GENDER = "gender";
    public static final String COLUMN_FRIEND_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_FRIEND_ADDRESS = "address";
    public static final String COLUMN_FRIEND_CLASS = "class";
    public static final String COLUMN_FRIEND_FACULTY = "faculty";



    //    Table Author
    public static final String TABLE_AUTHOR = "Authors";
    public static final String COLUMN_AUTHOR_ID = "_id";
    public static final String COLUMN_AUTHOR_NAME = "author_name";

    //    Table Book
    public static final String TABLE_BOOK = "Books";
    public static final String COLUMN_BOOK_ID = "_id";
    public static final String COLUMN_BOOK_NAME = "name";
    public static final String COLUMN_BOOK_IMAGE = "image";
    public static final String COLUMN_BOOK_PAGES = "pages";
    public static final String COLUMN_BOOK_LANGUAGE = "language";
    public static final String COLUMN_BOOK_QUANTITY = "quantity";

    public static final String COLUMN_BOOK_AUTHOR_ID = "author_id";
    public static final String COLUMN_BOOK_DESCRIPTION = "description";
    public static final String COLUMN_BOOK_PUBLISHED_DATE = "published_date";

    //    Order
    public static final String TABLE_ORDER = "Orders";
    public static final String COLUMN_ORDER_ID = "_id";
    public static final String COLUMN_ORDER_USER_ID = "user_id";
    public static final String COLUMN_ORDER_BOOK_ID = "book_id";
    public static final String COLUMN_ORDER_QUANTITY = "quantity";
    public static final String COLUMN_ORDER_DUE_DATE = "due_date";
    public static final String COLUMN_ORDER_DATE= "order_date";
    public static final String COLUMN_ORDER_IS_RETURNED = "is_returned";

}
