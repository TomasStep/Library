package com.books.Library;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "book-servlet", value = "/book-servlet")
public class BookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        //Test
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "Test" + "</h1>");
        out.println("</body></html>");
    }
}
