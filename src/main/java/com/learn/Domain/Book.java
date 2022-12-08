package com.learn.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author DingTian
 * @version 1.0
 */
public class Book {
    private int id;
    private String ISBN;
    private String name;
    private String publisher;
    private String author;
    private String states;

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", ISBN=" + ISBN +
                ", name='" + name + '\'' +
                ", publisher='" + publisher + '\'' +
                ", author='" + author + '\'' +
                ", states='" + states + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("ISBN") //属性的首字母大写会导致无法找到对应，应加上这个注释
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
