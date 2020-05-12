package dev.ruanvictor.sleepystorie.model;

import java.io.Serializable;

public class Book implements Serializable {

    private String title;
    private String author;
    private String description;
    private int cover;

    public Book(String title, String author, String description, int cover) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return "Author: ".concat(author);
    }

    public String getDescription() {
        return description;
    }

    public int getCover() {
        return cover;
    }
}
