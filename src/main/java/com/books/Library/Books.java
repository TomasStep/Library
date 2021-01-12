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

        // TODO: make random?
        String b1 = "123";
        String b2 = "456";
        String b3 = "789";
        _booksDb.put(b1, new Book(
                "TestName",
                "TestAuthor",
                b1,
                1,
                1.99f));
        _booksDb.put(b2, new Book(
                "TestName",
                "TestAuthor",
                b2,
                1,
                1.99f));
        _booksDb.put(b3, new Book(
                "TestName",
                "TestAuthor",
                b3,
                1,
                1.99f));
    }

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
    // Get books
    // GET/books/
    // GET/books/id
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        if(pathInfo == null || pathInfo.equals("/")){

            Collection<Book> models = _booksDb.values();

            sendAsJson(response, models);
            return;
        }

        String[] splits = pathInfo.split("/");

        if(splits.length != 2) {

            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String bookBId = splits[1];

        if(!_booksDb.containsKey(bookBId)) {

            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        sendAsJson(response, _booksDb.get(bookBId));
        return;
    }
    // Adds new book in DB
    // POST/books
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        if(pathInfo == null || pathInfo.equals("/")){

            StringBuilder buffer = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            String payload = buffer.toString();

            Book book = _gson.fromJson(payload, Book.class);

            // if barcode isn't described, give a specific/random one
            if(book.barcode == "" || book.barcode == null) {
                // TODO: generate random barcode
                book.barcode = "999";
            }
            _booksDb.put(book.barcode, book);

            sendAsJson(response, book);
        }
        else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
    }
}
