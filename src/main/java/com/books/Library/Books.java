package com.books.Library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet(name = "Books", value="/books/*")
public class Books extends HttpServlet {

    class Book {
        public String name;
        public String author;
        public int quantity;
        public float price;

        public String barcode;


        public Book(String name, String author, String barcode, int quantity, float price) {
            this.name = name;
            this.author = author;
            this.quantity = quantity;
            this.price = price;

            this.barcode = barcode;
        }
    }

    private Gson _gson = null;

    private HashMap<Object, Book> _booksDb = new HashMap<>();

    // Adds some default models to our db
    public Books() {
        super();

        _gson = new Gson();

    //a utility method to send object
    //as JSON response
    private void sendAsJson(
            HttpServletResponse response,
            Object obj) throws IOException {

        response.setContentType("application/json");

        String res = _gson.toJson(obj);

        PrintWriter out = response.getWriter();

        out.print(res);
        out.flush();
    }
}
