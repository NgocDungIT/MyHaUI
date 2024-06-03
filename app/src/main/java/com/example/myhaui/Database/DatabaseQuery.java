package com.example.myhaui.Database;

import static com.example.myhaui.Util.Config.COLUMN_AUTHOR_ID;
import static com.example.myhaui.Util.Config.COLUMN_AUTHOR_NAME;
import static com.example.myhaui.Util.Config.COLUMN_BOOK_AUTHOR_ID;
import static com.example.myhaui.Util.Config.COLUMN_BOOK_DESCRIPTION;
import static com.example.myhaui.Util.Config.COLUMN_BOOK_ID;
import static com.example.myhaui.Util.Config.COLUMN_BOOK_IMAGE;
import static com.example.myhaui.Util.Config.COLUMN_BOOK_LANGUAGE;
import static com.example.myhaui.Util.Config.COLUMN_BOOK_NAME;
import static com.example.myhaui.Util.Config.COLUMN_BOOK_PAGES;
import static com.example.myhaui.Util.Config.COLUMN_BOOK_PUBLISHED_DATE;
import static com.example.myhaui.Util.Config.COLUMN_BOOK_QUANTITY;
import static com.example.myhaui.Util.Config.COLUMN_FRIEND_ADDRESS;
import static com.example.myhaui.Util.Config.COLUMN_FRIEND_CLASS;
import static com.example.myhaui.Util.Config.COLUMN_FRIEND_CODE;
import static com.example.myhaui.Util.Config.COLUMN_FRIEND_FACULTY;
import static com.example.myhaui.Util.Config.COLUMN_FRIEND_GENDER;
import static com.example.myhaui.Util.Config.COLUMN_FRIEND_ID;
import static com.example.myhaui.Util.Config.COLUMN_FRIEND_NAME;
import static com.example.myhaui.Util.Config.COLUMN_FRIEND_PHONE_NUMBER;
import static com.example.myhaui.Util.Config.COLUMN_FRIEND_USERID;
import static com.example.myhaui.Util.Config.COLUMN_ORDER_BOOK_ID;
import static com.example.myhaui.Util.Config.COLUMN_ORDER_DATE;
import static com.example.myhaui.Util.Config.COLUMN_ORDER_DUE_DATE;
import static com.example.myhaui.Util.Config.COLUMN_ORDER_ID;
import static com.example.myhaui.Util.Config.COLUMN_ORDER_IS_RETURNED;
import static com.example.myhaui.Util.Config.COLUMN_ORDER_QUANTITY;
import static com.example.myhaui.Util.Config.COLUMN_ORDER_USER_ID;
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
import static com.example.myhaui.Util.Config.TABLE_ORDER;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

import com.example.myhaui.Util.Config;
import com.example.myhaui.model.Author;
import com.example.myhaui.model.Book;
import com.example.myhaui.model.Friend;
import com.example.myhaui.model.Order;
import com.example.myhaui.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DatabaseQuery {
    private static final String TAG = "DatabaseQuery";
    private Context context;

    public DatabaseQuery(Context context) {
        this.context = context;
//        initData()
    }

    /// Init data
    //Safe Mode
    private Integer safeGetInteger(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        if (columnIndex != -1 && !cursor.isNull(columnIndex)) {
            return cursor.getInt(columnIndex);
        }
        return null;
    }

    private String safeGetString(Cursor cursor, String columnName) {
        int columnIndex = cursor.getColumnIndex(columnName);
        if (columnIndex != -1 && !cursor.isNull(columnIndex)) {
            return cursor.getString(columnIndex);
        }
        return null;
    }

    /*
     * Query User
     *  - Create User
     *  - GetAllUser
     *  - GetUserByCode
     *  - Search User >>>>
     *  - Update User
     * */
//   Create User
    public long addNewUser(User user) {
        long id = -1;
        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(Config.COLUMN_USER_CODE, user.getCode());
        content.put(Config.COLUMN_USER_NAME, user.getFullName());
        content.put(Config.COLUMN_USER_PASSWORD, user.getPassword());
        content.put(Config.COLUMN_USER_PHONE_NUMBER, user.getPhoneNumber());
        content.put(Config.COLUMN_USER_GENDER, user.getGender());
        content.put(Config.COLUMN_USER_ADDRESS, user.getAddress());

        try {
            id = db.insertOrThrow(Config.TABLE_USER, null, content);

        } catch (SQLiteException e) {
            Log.d(TAG, "getAllUsers: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            db.close();
        }


        return id;
    }

    //    Get All User
    public List<User> getAllUsers() {
        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {
            cursor = db.query(Config.TABLE_USER, null, null, null, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    List<User> users = new ArrayList<>();
                    do {
                        int id = safeGetInteger(cursor, Config.COLUMN_USER_ID);
                        String code = safeGetString(cursor, COLUMN_USER_CODE);
                        String fullName = safeGetString(cursor, COLUMN_USER_NAME);
                        String password = safeGetString(cursor, COLUMN_USER_PASSWORD);
                        String gender = safeGetString(cursor, COLUMN_USER_GENDER);
                        String phoneNumber = safeGetString(cursor, COLUMN_USER_PHONE_NUMBER);
                        String className = safeGetString(cursor, COLUMN_USER_CLASS);
                        String faculty = safeGetString(cursor, COLUMN_USER_FACULTY);
                        String address = safeGetString(cursor, COLUMN_USER_ADDRESS);
                        users.add(new User(id, code, fullName, password, gender, phoneNumber, className, faculty, address));
                    } while (cursor.moveToNext());
                    Toast.makeText(context, users.get(0).getFullName(), Toast.LENGTH_SHORT).show();
                    return users;
                }
            }
        } catch (Exception ex) {

            Toast.makeText(context, "Có lỗi xảy ra khi đọc dữ liệu", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return Collections.emptyList();
    }

    //    get User By Code
    public User getUserByCode(String code) {
        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        User user = null;
        try {
            cursor = db.query(Config.TABLE_USER, null, COLUMN_USER_CODE + " = ? ", new String[]{String.valueOf(code)}, null, null, null);
            if (cursor.moveToFirst()) {
                int id = safeGetInteger(cursor, Config.COLUMN_USER_ID);
                String userCode = safeGetString(cursor, COLUMN_USER_CODE);
                String fullName = safeGetString(cursor, COLUMN_USER_NAME);
                String password = safeGetString(cursor, COLUMN_USER_PASSWORD);
                String gender = safeGetString(cursor, COLUMN_USER_GENDER);
                String phoneNumber = safeGetString(cursor, COLUMN_USER_PHONE_NUMBER);
                String className = safeGetString(cursor, COLUMN_USER_CLASS);
                String faculty = safeGetString(cursor, COLUMN_USER_FACULTY);
                String address = safeGetString(cursor, COLUMN_USER_ADDRESS);
                user = new User(id, userCode, fullName, password, gender, phoneNumber, className, faculty, address);
            }
        } catch (Exception ex) {
            Toast.makeText(context, "Có lỗi xảy ra khi đọc dữ liệu", Toast.LENGTH_SHORT).show();

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return user;
    }

//Search User <abc>


    //Update User
    public long updateUser(User user) {
        long rowCount = 0;
        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(Config.COLUMN_USER_NAME, user.getFullName());
        content.put(Config.COLUMN_USER_PASSWORD, user.getPassword());
        content.put(Config.COLUMN_USER_PHONE_NUMBER, user.getPhoneNumber());
        content.put(COLUMN_USER_CLASS, user.getClassName());
        content.put(COLUMN_USER_FACULTY, user.getFaculty());
        content.put(Config.COLUMN_USER_ADDRESS, user.getAddress());
        try {
            rowCount = db.update(Config.TABLE_USER, content, Config.COLUMN_USER_ID + " = ? ", new String[]{String.valueOf(user.get_id())});
            Toast.makeText(context, "Sua thanh cong", Toast.LENGTH_SHORT).show();
        } catch (SQLiteException ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }
        return rowCount;
    }

    /* Friends
     * add Friend
     * get All Friend Of User
     * get Friend By Code of User
     * search Friend
     * delete Friend
     * */
//    AddFriend
    public long addNewFriend(Friend friend) {
        long id = -1;
        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(COLUMN_FRIEND_USERID, friend.getUserId());
        content.put(COLUMN_FRIEND_CODE, friend.getCode());
        content.put(Config.COLUMN_FRIEND_NAME, friend.getFullName());
        content.put(Config.COLUMN_FRIEND_PHONE_NUMBER, friend.getPhoneNumber());
        try {
            id = db.insertOrThrow(TABLE_FRIEND, null, content);
            Toast.makeText(context, "Thanh cong", Toast.LENGTH_SHORT).show();
        } catch (SQLiteException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            db.close();
        }
        return id;
    }

    //    get All Friend
    public List<Friend> getAllFriendOfUser(int user_id) {
        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_FRIEND, null, COLUMN_FRIEND_USERID + " = ? ", new String[]{String.valueOf(user_id)}, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    List<Friend> friends = new ArrayList<>();
                    do {
                        int id = safeGetInteger(cursor, Config.COLUMN_USER_ID);
                        int userId = safeGetInteger(cursor, COLUMN_FRIEND_USERID);
                        String code = safeGetString(cursor, COLUMN_FRIEND_CODE);
                        String fullName = safeGetString(cursor, COLUMN_FRIEND_NAME);
                        String phoneNumber = safeGetString(cursor, COLUMN_FRIEND_PHONE_NUMBER);
                        friends.add(new Friend(id, userId, code, fullName, phoneNumber));
                    } while (cursor.moveToNext());
//                    Toast.makeText(context, friends.get(0).getFullName(), Toast.LENGTH_SHORT).show();
                    return friends;
                }
            }
        } catch (Exception ex) {
            Toast.makeText(context, "Có lỗi xảy ra khi đọc dữ liệu", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return Collections.emptyList();
    }

    // get friend by code
    public Friend getFriendByCode(String code, int user_id) {
        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        Friend friend = null;
        try {
            cursor = db.query(TABLE_FRIEND, null, COLUMN_FRIEND_CODE + " = ? ", new String[]{String.valueOf(code)}, null, null, null);
            if (cursor.moveToFirst()) {
                int id = safeGetInteger(cursor, Config.COLUMN_FRIEND_ID);
                String friendCode = safeGetString(cursor, COLUMN_FRIEND_CODE);
                String fullName = safeGetString(cursor, COLUMN_FRIEND_NAME);
                String phoneNumber = safeGetString(cursor, COLUMN_FRIEND_PHONE_NUMBER);
                friend = new Friend(id, user_id, friendCode, fullName, phoneNumber);
            }
        } catch (Exception ex) {
            Toast.makeText(context, "Có lỗi xảy ra khi đọc dữ liệu", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return friend;
    }

    //    Search Friend
    public List<Friend> searchFriendOfUser(int user_id, String name) {
        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_FRIEND, null, COLUMN_FRIEND_USERID + " = ? AND " + COLUMN_FRIEND_NAME + " = ?", new String[]{String.valueOf(user_id), "%" + name + "%"}, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    List<Friend> friends = new ArrayList<>();
                    do {
                        int id = safeGetInteger(cursor, Config.COLUMN_USER_ID);
                        int userId = safeGetInteger(cursor, COLUMN_FRIEND_USERID);
                        String code = safeGetString(cursor, COLUMN_FRIEND_CODE);
                        String fullName = safeGetString(cursor, COLUMN_FRIEND_NAME);
                        String phoneNumber = safeGetString(cursor, COLUMN_FRIEND_PHONE_NUMBER);
                        friends.add(new Friend(id, userId, code, fullName, phoneNumber));
                    } while (cursor.moveToNext());
//                    Toast.makeText(context, friends.get(0).getFullName(), Toast.LENGTH_SHORT).show();
                    return friends;
                }
            }
        } catch (Exception ex) {
            Toast.makeText(context, "Có lỗi xảy ra khi đọc dữ liệu", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return Collections.emptyList();
    }

    // public delete Friend By User
    public boolean deleteFriendByUser(int userId, String friendCode) {
        boolean deleteFriend = false;
        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        try {
            //for "1" delete() method returns number of deleted rows
            //if you don't want row count just use delete(TABLE_NAME, null, null)
            db.delete(TABLE_FRIEND, COLUMN_FRIEND_USERID + " = ? AND " + COLUMN_FRIEND_CODE + " = ?", new String[]{String.valueOf(userId), String.valueOf(friendCode)});

            long count = DatabaseUtils.queryNumEntries(db, TABLE_FRIEND);

            if (count == 0) deleteFriend = true;

        } catch (SQLiteException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }

        return deleteFriend;
    }

    /*
     * Authors table
     * create Author
     * getAllAuthors
     * */
    // create  Author
    public long addNewAuthor(Author author) {
        long id = -1;
        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(Config.COLUMN_AUTHOR_NAME, author.getAuthor_name());

        try {
            id = db.insertOrThrow(Config.TABLE_AUTHOR, null, content);

        } catch (SQLiteException e) {
            Log.d(TAG, "add author: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            db.close();
        }
        return id;
    }

    //    Get all author
    public List<Author> getAllAuthors() {
        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {
            cursor = db.query(Config.TABLE_AUTHOR, null, null, null, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    List<Author> authors = new ArrayList<>();
                    do {
                        int id = safeGetInteger(cursor, Config.COLUMN_AUTHOR_ID);
                        String author_name = safeGetString(cursor, COLUMN_AUTHOR_NAME);
                        authors.add(new Author(id, author_name));
                    } while (cursor.moveToNext());
                    Toast.makeText(context, authors.get(0).getAuthor_name(), Toast.LENGTH_SHORT).show();
                    return authors;
                }
            }
        } catch (Exception ex) {

            Toast.makeText(context, "Có lỗi xảy ra khi đọc dữ liệu", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return Collections.emptyList();
    }

    //    get Author By ID
    public Author getAthorByID(int id) {
        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        Author author = null;
        try {
            cursor = db.query(Config.TABLE_AUTHOR, null, COLUMN_AUTHOR_ID + " = ? ", new String[]{String.valueOf(id)}, null, null, null);
            if (cursor.moveToFirst()) {
                int idAuthor = safeGetInteger(cursor, COLUMN_AUTHOR_ID);
                String userCode = safeGetString(cursor, COLUMN_AUTHOR_NAME);

                author = new Author(idAuthor, userCode);
            }
        } catch (Exception ex) {
            Toast.makeText(context, "Có lỗi xảy ra khi đọc dữ liệu", Toast.LENGTH_SHORT).show();

        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return author;
    }

    /*
     * Books Table
     * create book
     * get all book
     * get all book by author_id
     * get book by id
     * update quantity b0ok
     *
     * */
//    create new book
    public long addNewBook(Book book) {
        long id = -1;
        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(Config.COLUMN_BOOK_NAME, book.getName());
        content.put(Config.COLUMN_BOOK_IMAGE, book.getImage());
        content.put(COLUMN_BOOK_PAGES, book.getPages());
        content.put(Config.COLUMN_BOOK_LANGUAGE, book.getLanguage());
        content.put(Config.COLUMN_BOOK_QUANTITY, book.getQuantity());
        content.put(Config.COLUMN_BOOK_AUTHOR_ID, book.getAuthor_id());
        content.put(Config.COLUMN_BOOK_DESCRIPTION, book.getDescription());
        content.put(Config.COLUMN_BOOK_PUBLISHED_DATE, book.getPublished_date().toString());


        try {
            id = db.insertOrThrow(Config.TABLE_BOOK, null, content);

        } catch (SQLiteException e) {
            Log.d(TAG, "create new book: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            db.close();
        }


        return id;
    }

    //get all book
    public List<Book> getAllBooks() {
        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {
            cursor = db.query(Config.TABLE_BOOK, null, null, null, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    List<Book> books = new ArrayList<>();
                    do {
                        int id = safeGetInteger(cursor, Config.COLUMN_BOOK_ID);
                        String name = safeGetString(cursor, COLUMN_BOOK_NAME);
                        String image = safeGetString(cursor, COLUMN_BOOK_IMAGE);
                        int pages = safeGetInteger(cursor, COLUMN_BOOK_PAGES);
                        String language = safeGetString(cursor, COLUMN_BOOK_LANGUAGE);
                        int quantity = safeGetInteger(cursor, COLUMN_BOOK_QUANTITY);
                        int author_id = safeGetInteger(cursor, COLUMN_BOOK_AUTHOR_ID);
                        String description = safeGetString(cursor, COLUMN_BOOK_DESCRIPTION);
                        String published_date = safeGetString(cursor, COLUMN_BOOK_PUBLISHED_DATE);

                        books.add(new Book(id, name, image, pages, language, quantity, author_id, description, published_date));
                    } while (cursor.moveToNext());
                    Toast.makeText(context, books.get(0).getName(), Toast.LENGTH_SHORT).show();
                    return books;
                }
            }
        } catch (Exception ex) {

            Toast.makeText(context, "Có lỗi xảy ra khi đọc dữ liệu", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return Collections.emptyList();
    }

    //    get all book by author
    public List<Book> getAllBooksByAuthor(int authorId) {
        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {
            cursor = db.query(Config.TABLE_BOOK, null, COLUMN_BOOK_AUTHOR_ID + " = ?", new String[]{String.valueOf(authorId)}, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    List<Book> books = new ArrayList<>();
                    do {
                        int id = safeGetInteger(cursor, Config.COLUMN_BOOK_ID);
                        String name = safeGetString(cursor, COLUMN_BOOK_NAME);
                        String image = safeGetString(cursor, COLUMN_BOOK_IMAGE);
                        int pages = safeGetInteger(cursor, COLUMN_BOOK_PAGES);
                        String language = safeGetString(cursor, COLUMN_BOOK_LANGUAGE);
                        int quantity = safeGetInteger(cursor, COLUMN_BOOK_QUANTITY);
                        int author_id = safeGetInteger(cursor, COLUMN_BOOK_AUTHOR_ID);
                        String description = safeGetString(cursor, COLUMN_BOOK_DESCRIPTION);
                        String published_date = safeGetString(cursor, COLUMN_BOOK_PUBLISHED_DATE);

                        books.add(new Book(id, name, image, pages, language, quantity, author_id, description, published_date));
                    } while (cursor.moveToNext());
                    Toast.makeText(context, books.get(0).getName(), Toast.LENGTH_SHORT).show();
                    return books;
                }
            }
        } catch (Exception ex) {

            Toast.makeText(context, "Có lỗi xảy ra khi đọc dữ liệu", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return Collections.emptyList();
    }

    // Get book by id
    public Book getBookById(int book_id) {
        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        Book book = null;
        try {
            cursor = db.query(TABLE_BOOK, null, COLUMN_BOOK_ID + " = ? ", new String[]{String.valueOf(book_id)}, null, null, null);
            if (cursor.moveToFirst()) {
                int id = safeGetInteger(cursor, COLUMN_BOOK_ID);
                String name = safeGetString(cursor, COLUMN_BOOK_NAME);
                String image = safeGetString(cursor, COLUMN_BOOK_IMAGE);
                int pages = safeGetInteger(cursor, COLUMN_BOOK_PAGES);
                String language = safeGetString(cursor, COLUMN_BOOK_LANGUAGE);
                int quantity = safeGetInteger(cursor, COLUMN_BOOK_QUANTITY);
                int author_id = safeGetInteger(cursor, COLUMN_BOOK_AUTHOR_ID);
                String description = safeGetString(cursor, COLUMN_BOOK_DESCRIPTION);
                String published_date = safeGetString(cursor, COLUMN_BOOK_PUBLISHED_DATE);
                book = new Book(id, name, image, pages, language, quantity, author_id, description, published_date);
            }
        } catch (Exception ex) {
            Toast.makeText(context, "Có lỗi xảy ra khi đọc dữ liệu", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return book;
    }

    //Update User
    public long updateQuantityBook(int id, int quantityNew) {
        long rowCount = 0;
        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(COLUMN_BOOK_QUANTITY, quantityNew);

        try {
            rowCount = db.update(TABLE_BOOK, content, COLUMN_BOOK_ID + " = ? ", new String[]{String.valueOf(id)});

        } catch (SQLiteException ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }
        return rowCount;
    }

    /*
     * Order table
     * create order
     * update order
     * get order by id
     * get ordering by user
     * get returned book by user
     * */
//    create new order
    public long addNewOrder(Order order) {
        long id = -1;
        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(Config.COLUMN_ORDER_USER_ID, order.getUser_id());
        content.put(Config.COLUMN_ORDER_BOOK_ID, order.getBook_id());
        content.put(Config.COLUMN_ORDER_QUANTITY, order.getQuantity());
        content.put(Config.COLUMN_ORDER_DATE, order.getOrder_date());
        content.put(Config.COLUMN_ORDER_DUE_DATE, order.getDue_date());
        content.put(Config.COLUMN_ORDER_IS_RETURNED, order.getIs_returned());
        try {
            id = db.insertOrThrow(Config.TABLE_ORDER, null, content);

        } catch (SQLiteException e) {
            Log.d(TAG, "create new book: " + e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            db.close();
        }


        return id;
    }

    //get all order by user
    public List<Order> getAllOrderByUser(int userId) {
        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {
            cursor = db.query(Config.TABLE_ORDER, null, COLUMN_ORDER_USER_ID + " = ?", new String[]{String.valueOf(userId)}, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    List<Order> orders = new ArrayList<>();
                    do {
                        int id = safeGetInteger(cursor, Config.COLUMN_ORDER_ID);
                        int user_id = safeGetInteger(cursor, COLUMN_ORDER_USER_ID);
                        int book_id = safeGetInteger(cursor, COLUMN_ORDER_BOOK_ID);
                        int quantity = safeGetInteger(cursor, COLUMN_ORDER_QUANTITY);
                        String due_date = safeGetString(cursor, COLUMN_ORDER_DUE_DATE);
                        String order_date = safeGetString(cursor, COLUMN_ORDER_DATE);

                        int is_returned = safeGetInteger(cursor, COLUMN_ORDER_IS_RETURNED);

                        orders.add(new Order(id, user_id, book_id, quantity, due_date, order_date, is_returned));
                    } while (cursor.moveToNext());

                    return orders;
                }
            }
        } catch (Exception ex) {

            Toast.makeText(context, "Có lỗi xảy ra khi đọc dữ liệu", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return Collections.emptyList();
    }

    //    get ordering by user
    public List<Order> getAllOrdering(int userId, int returned) {
        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        try {
            cursor = db.query(Config.TABLE_ORDER, null, COLUMN_ORDER_USER_ID + " = ? AND " + COLUMN_ORDER_IS_RETURNED + " = ? ", new String[]{String.valueOf(userId), String.valueOf(returned)}, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    List<Order> orders = new ArrayList<>();
                    do {
                        int id = safeGetInteger(cursor, Config.COLUMN_ORDER_ID);
                        int user_id = safeGetInteger(cursor, COLUMN_ORDER_USER_ID);
                        int book_id = safeGetInteger(cursor, COLUMN_ORDER_BOOK_ID);
                        int quantity = safeGetInteger(cursor, COLUMN_ORDER_QUANTITY);
                        String due_date = safeGetString(cursor, COLUMN_ORDER_DUE_DATE);
                        String order_date = safeGetString(cursor, COLUMN_ORDER_DATE);

                        int is_returned = safeGetInteger(cursor, COLUMN_ORDER_IS_RETURNED);

                        orders.add(new Order(id, user_id, book_id, quantity, due_date, order_date, is_returned));
                    } while (cursor.moveToNext());

                    return orders;
                }
            }
        } catch (Exception ex) {

            Toast.makeText(context, "Có lỗi xảy ra khi đọc dữ liệu", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return Collections.emptyList();
    }

    // Get order by id
    public Order getOrderById(int order_id) {
        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = null;
        Order order = null;
        try {
            cursor = db.query(TABLE_ORDER, null, COLUMN_ORDER_ID + " = ? ", new String[]{String.valueOf(order_id)}, null, null, null);
            if (cursor.moveToFirst()) {
                int id = safeGetInteger(cursor, Config.COLUMN_ORDER_ID);
                int user_id = safeGetInteger(cursor, COLUMN_ORDER_USER_ID);
                int book_id = safeGetInteger(cursor, COLUMN_ORDER_BOOK_ID);
                int quantity = safeGetInteger(cursor, COLUMN_ORDER_QUANTITY);
                String due_date = safeGetString(cursor, COLUMN_ORDER_DUE_DATE);
                String order_date = safeGetString(cursor, COLUMN_ORDER_DATE);

                int is_returned = safeGetInteger(cursor, COLUMN_ORDER_IS_RETURNED);
                order = new Order(id, user_id, book_id, quantity, due_date, order_date, is_returned);
            }
        } catch (Exception ex) {
            Toast.makeText(context, "Có lỗi xảy ra khi đọc dữ liệu", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return order;
    }

    // update order due date
    public long updateOrder(int id, String dueDate) {
        long rowCount = 0;
        DBHelper databaseHelper = DBHelper.getInstance(context);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(COLUMN_ORDER_DUE_DATE, dueDate);
        content.put(COLUMN_ORDER_IS_RETURNED, "1");

        try {
            rowCount = db.update(TABLE_ORDER, content, COLUMN_ORDER_ID + " = ? ", new String[]{String.valueOf(id)});

        } catch (SQLiteException ex) {
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            db.close();
        }
        return rowCount;
    }

}
