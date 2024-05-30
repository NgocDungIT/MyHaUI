package com.example.myhaui.Database;

import static com.example.myhaui.Util.Config.COLUMN_BOOK_AUTHOR_ID;
import static com.example.myhaui.Util.Config.COLUMN_BOOK_DESCRIPTION;
import static com.example.myhaui.Util.Config.COLUMN_BOOK_ID;
import static com.example.myhaui.Util.Config.COLUMN_BOOK_IMAGE;
import static com.example.myhaui.Util.Config.COLUMN_BOOK_NAME;
import static com.example.myhaui.Util.Config.COLUMN_BOOK_QUANTITY;
import static com.example.myhaui.Util.Config.COLUMN_FRIEND_ADDRESS;
import static com.example.myhaui.Util.Config.COLUMN_FRIEND_CODE;
import static com.example.myhaui.Util.Config.COLUMN_FRIEND_NAME;
import static com.example.myhaui.Util.Config.COLUMN_FRIEND_CLASS;
import static com.example.myhaui.Util.Config.COLUMN_FRIEND_FACULTY;
import static com.example.myhaui.Util.Config.COLUMN_FRIEND_PHONE_NUMBER;



import static com.example.myhaui.Util.Config.COLUMN_FRIEND_GENDER;
import static com.example.myhaui.Util.Config.COLUMN_FRIEND_ID;
import static com.example.myhaui.Util.Config.COLUMN_FRIEND_NAME;
import static com.example.myhaui.Util.Config.COLUMN_FRIEND_USERID;
import static com.example.myhaui.Util.Config.COLUMN_BOOK_PUBLISHED_DATE;
import static com.example.myhaui.Util.Config.COLUMN_USER_ADDRESS;
import static com.example.myhaui.Util.Config.COLUMN_USER_CLASS;
import static com.example.myhaui.Util.Config.COLUMN_USER_CODE;
import static com.example.myhaui.Util.Config.COLUMN_USER_FACULTY;
import static com.example.myhaui.Util.Config.COLUMN_USER_GENDER;
import static com.example.myhaui.Util.Config.COLUMN_USER_NAME;
import static com.example.myhaui.Util.Config.COLUMN_USER_PASSWORD;
import static com.example.myhaui.Util.Config.COLUMN_USER_PHONE_NUMBER;
import static com.example.myhaui.Util.Config.TABLE_BOOK;
import static com.example.myhaui.Util.Config.TABLE_FRIEND;
import static com.example.myhaui.Util.Config.TABLE_USER;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myhaui.Util.Config;
import com.example.myhaui.model.User;

public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper databaseHelper;
//    All static variables;
    private static final int DATABASE_VERSION = 1;
    private DatabaseQuery databaseQuery = null;
// database name
    private static final String DATABASE_NAME = Config.DATABASE_NAME;
    //Constructor
    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        databaseQuery  = new DatabaseQuery(context);
    }
/*
* get Database
* */
    public static DBHelper getInstance(Context context) {
        if(databaseHelper == null) {
            synchronized (DBHelper.class) {
                if(databaseHelper == null) {
                    databaseHelper = new DBHelper(context);
                }
            }
        }
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + Config.TABLE_USER + "("
                + Config.COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USER_CODE + " TEXT NOT NULL UNIQUE, "
                + COLUMN_USER_NAME + " TEXT NOT NULL, "
                + COLUMN_USER_PASSWORD + " TEXT NOT NULL, "
                + COLUMN_USER_GENDER + " TEXT, "
                + COLUMN_USER_PHONE_NUMBER + " TEXT NOT NULL, "
                + COLUMN_USER_CLASS + " TEXT, "
                + COLUMN_USER_FACULTY + " TEXT, "
                + COLUMN_USER_ADDRESS + " TEXT);";

//        tạo bảng Friends
        String createFriendTable = "CREATE TABLE " + Config.TABLE_FRIEND + " ("
                + Config.COLUMN_FRIEND_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Config.COLUMN_FRIEND_USERID + " INTEGER NOT NULL, "
                + COLUMN_FRIEND_CODE + " TEXT NOT NULL, "
                + COLUMN_FRIEND_NAME + " TEXT NOT NULL, "
                + COLUMN_FRIEND_PHONE_NUMBER + " TEXT NOT NULL, "
                + "FOREIGN KEY(" + Config.COLUMN_FRIEND_USERID + ") REFERENCES " + Config.TABLE_USER + "(" + Config.COLUMN_USER_ID + "));";
        // Create Authors table
        String createAuthorTable = "CREATE TABLE " + Config.TABLE_AUTHOR + " (" +
                Config.COLUMN_AUTHOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Config.COLUMN_AUTHOR_NAME + " TEXT NOT NULL);";

        // Create Books table
        String createBookTable = "CREATE TABLE " + Config.TABLE_BOOK + " (" +
                Config.COLUMN_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Config.COLUMN_BOOK_NAME + " TEXT NOT NULL, " +
                Config.COLUMN_BOOK_IMAGE + " TEXT, " +
                Config.COLUMN_BOOK_PAGES + " INTEGER NOT NULL, " +
                Config.COLUMN_BOOK_LANGUAGE + " TEXT NOT NULL, " +
                Config.COLUMN_BOOK_QUANTITY + " INTEGER, " +
                Config.COLUMN_BOOK_AUTHOR_ID + " INTEGER, " +
                Config.COLUMN_BOOK_DESCRIPTION + " TEXT, " +
                Config.COLUMN_BOOK_PUBLISHED_DATE + " TEXT, " +
                "FOREIGN KEY(" + Config.COLUMN_BOOK_AUTHOR_ID + ") REFERENCES " + Config.TABLE_AUTHOR + "(" + Config.COLUMN_AUTHOR_ID + "));";

        // Create Orders table
        String createOrderTable = "CREATE TABLE " + Config.TABLE_ORDER + " (" +
                Config.COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Config.COLUMN_ORDER_USER_ID + " INTEGER NOT NULL, " +
                Config.COLUMN_ORDER_BOOK_ID + " INTEGER NOT NULL, " +
                Config.COLUMN_ORDER_QUANTITY + " INTEGER, " +
                Config.COLUMN_ORDER_DUE_DATE + " TEXT, " +
                Config.COLUMN_ORDER_DATE + " TEXT, " +
                Config.COLUMN_ORDER_IS_RETURNED + " BIT, " +
                "FOREIGN KEY(" + Config.COLUMN_ORDER_USER_ID + ") REFERENCES " + Config.TABLE_USER + "(" + Config.COLUMN_USER_ID + "), " +
                "FOREIGN KEY(" + Config.COLUMN_ORDER_BOOK_ID + ") REFERENCES " + Config.TABLE_BOOK + "(" + Config.COLUMN_BOOK_ID + "));";
        db.execSQL(createUserTable);
        db.execSQL(createFriendTable);
        db.execSQL(createAuthorTable);
        db.execSQL(createBookTable);
        db.execSQL(createOrderTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_FRIEND);
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_AUTHOR);
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_BOOK);
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_ORDER);
        onCreate(db);
    }


    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON;");

    }



}
