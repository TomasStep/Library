package com.books.Library;

public class Book {
    String name;
    String author;
    int releaseYear;

    public Book(String name, String author, int releaseYear)
    {
        this.name = name;
        this.author = author;
        this.releaseYear = releaseYear;
    }

    public String getName() {
        return name;
    }

    public String getAuthor(){
        return author;
    }

    public int getReleaseYear(){
        return releaseYear;
    }
}
