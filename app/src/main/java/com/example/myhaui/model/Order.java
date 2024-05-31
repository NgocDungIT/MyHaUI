package com.example.myhaui.model;

public class Order {
    private int _id;
    private int user_id;
    private int book_id;
    private int quantity;
    private String due_date;
    private String order_date;
    private int is_returned;

    public Order(int _id, int user_id, int book_id, int quantity, String due_date, String order_date, int is_returned) {
        this._id = _id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.quantity = quantity;
        this.due_date = due_date;
        this.order_date = order_date;
        this.is_returned = is_returned;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public int getIs_returned() {
        return is_returned;
    }

    public void setIs_returned(int is_returned) {
        this.is_returned = is_returned;
    }
}
