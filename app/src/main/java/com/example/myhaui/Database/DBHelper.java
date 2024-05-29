package com.example.myhaui.Database;

import static com.example.myhaui.Util.Config.COLUMN_BOOK_AUTHOR_ID;
import static com.example.myhaui.Util.Config.COLUMN_BOOK_CATEGORY_ID;
import static com.example.myhaui.Util.Config.COLUMN_BOOK_DESCRIPTION;
import static com.example.myhaui.Util.Config.COLUMN_BOOK_ID;
import static com.example.myhaui.Util.Config.COLUMN_BOOK_IMAGE;
import static com.example.myhaui.Util.Config.COLUMN_BOOK_NAME;
import static com.example.myhaui.Util.Config.COLUMN_BOOK_QUANTITY;
import static com.example.myhaui.Util.Config.COLUMN_CATEGORY_ID;
import static com.example.myhaui.Util.Config.COLUMN_CATEGORY_NAME;
import static com.example.myhaui.Util.Config.COLUMN_FRIEND_ADDRESS;
import static com.example.myhaui.Util.Config.COLUMN_FRIEND_CODE;
import static com.example.myhaui.Util.Config.COLUMN_FRIEND_GENDER;
import static com.example.myhaui.Util.Config.COLUMN_FRIEND_ID;
import static com.example.myhaui.Util.Config.COLUMN_FRIEND_NAME;
import static com.example.myhaui.Util.Config.COLUMN_FRIEND_USERID;
import static com.example.myhaui.Util.Config.COLUMN_PUBLISHED_DATE;
import static com.example.myhaui.Util.Config.COLUMN_USER_ADDRESS;
import static com.example.myhaui.Util.Config.COLUMN_USER_CODE;
import static com.example.myhaui.Util.Config.COLUMN_USER_CREATED_AT;
import static com.example.myhaui.Util.Config.COLUMN_USER_GENDER;
import static com.example.myhaui.Util.Config.COLUMN_USER_ID;
import static com.example.myhaui.Util.Config.COLUMN_USER_NAME;
import static com.example.myhaui.Util.Config.COLUMN_USER_PASSWORD;
import static com.example.myhaui.Util.Config.COLUMN_USER_UPDATED_AT;
import static com.example.myhaui.Util.Config.TABLE_BOOK;
import static com.example.myhaui.Util.Config.TABLE_CATEGORY;
import static com.example.myhaui.Util.Config.TABLE_FRIEND;
import static com.example.myhaui.Util.Config.TABLE_USER;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myhaui.Util.Config;

public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper databaseHelper;
//    All static variables;
    private static final int DATABASE_VERSION = 1;

// database name
    private static final String DATABASE_NAME = Config.DATABASE_NAME;
    //Constructor
    private DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addNewUser(String userId, String fullName, String password, String code, String gender, String address, String createdAt, String updatedAt) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_USER_ID, userId);
        values.put(COLUMN_USER_NAME, fullName);
        values.put(COLUMN_USER_PASSWORD, password);
        values.put(COLUMN_USER_CODE, code);
        values.put(COLUMN_USER_GENDER, gender);
        values.put(COLUMN_USER_ADDRESS, address);
        values.put(COLUMN_USER_CREATED_AT, createdAt);
        values.put(COLUMN_USER_UPDATED_AT, updatedAt);

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void addNewFriend(String friendId, String userId, String fullName, String code, String gender, String address) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_FRIEND_ID, friendId);
        values.put(COLUMN_FRIEND_USERID, userId);
        values.put(COLUMN_FRIEND_NAME, fullName);
        values.put(COLUMN_FRIEND_CODE, code);
        values.put(COLUMN_FRIEND_GENDER, gender);
        values.put(COLUMN_FRIEND_ADDRESS, address);

        db.insert(TABLE_FRIEND, null, values);
        db.close();
    }

    public void addNewCategory(String categoryId, String categoryName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_CATEGORY_ID, categoryId);
        values.put(COLUMN_CATEGORY_NAME, categoryName);

        db.insert(TABLE_CATEGORY, null, values);
        db.close();
    }

    public void addNewBook(String bookId, String categoryId, String name, String image, String quantity, String authorId, String description, String publishedDate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_BOOK_ID, bookId);
        values.put(COLUMN_BOOK_CATEGORY_ID, categoryId);
        values.put(COLUMN_BOOK_NAME, name);
        values.put(COLUMN_BOOK_IMAGE, image);
        values.put(COLUMN_BOOK_QUANTITY, quantity);
        values.put(COLUMN_BOOK_AUTHOR_ID, authorId);
        values.put(COLUMN_BOOK_DESCRIPTION, description);
        values.put(COLUMN_PUBLISHED_DATE, publishedDate);

        db.insert(TABLE_BOOK, null, values);
        db.close();
    }
}
