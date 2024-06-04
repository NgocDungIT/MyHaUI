package com.example.myhaui.model;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable {
  private int _id;
  private String name;
  private String image;
  private int pages;
    private String language;
    private int quantity;
    private int author_id;
    private String description;
    private String published_date;

    public Book(int _id, String name, String image, int pages, String language, int quantity, int author_id, String description, String published_date) {
        this._id = _id;
        this.name = name;
        this.image = image;
        this.pages = pages;
        this.language = language;
        this.quantity = quantity;
        this.author_id = author_id;
        this.description = description;
        this.published_date = published_date;
    }
//uu
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }
}
