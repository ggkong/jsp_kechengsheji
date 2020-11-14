package com.inspur.controller;

import com.inspur.entity.Book;
import com.inspur.service.BookService;
import com.inspur.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends HttpServlet {

    private BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        Integer page = Integer.parseInt(pageStr);
        List<Book> list = bookService.findAll(page);
        req.setAttribute("list",list);
        req.setAttribute("dataPrePage",6);
        req.setAttribute("currentPage",page);
        req.setAttribute("pages",bookService.getPages());
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }
}
