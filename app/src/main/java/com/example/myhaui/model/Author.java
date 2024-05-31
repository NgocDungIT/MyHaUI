package com.example.myhaui.model;

public class Author {
    private int _id;
    private String author_name;

    public Author(int _id, String author_name) {
        this._id = _id;
        this.author_name = author_name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }
}
