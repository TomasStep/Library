package com.books.Library;

import java.util.HashMap;
import java.util.Map;

public class BookStorage {

    private Map<String, Book> bookMap = new HashMap<>();

    private static BookStorage instance = new BookStorage();
    public static BookStorage getInstance(){
        return instance;
    }

    private BookStorage(){
        bookMap.put("1", new Book("Test", "Test author", 1));
    }

    public Book getBook(String name){
        return bookMap.get(name);
    }

    public void putBook(Book book) {
        bookMap.put(book.getName(), book);
    }
}
