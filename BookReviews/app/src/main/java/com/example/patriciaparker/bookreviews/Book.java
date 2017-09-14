package com.example.patriciaparker.bookreviews;

/**
 * Created by patriciaparker on 4/6/17.
 */

public class Book {

    private int id;
    private String title;
    private String author;
    private String rating;
    private String image;

    public Book() {
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }


    //getters & setters

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRating() {
        return this.rating;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage(String image) {
        return image;
    }


    @Override
    public String toString() {
        return "Book [id=" + this.id + ", title=" + this.title + ", author=" + this.author
                + ", rating=" + this.rating + ", image=" + this.image + "]";


    }
}
