package com.inspur.controller;

import com.inspur.entity.Admin;
import com.inspur.entity.Borrow;
import com.inspur.service.BookService;
import com.inspur.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AdminServlet extends HttpServlet {
    private BookService bookService = new BookServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method == null){
            method = "findAllBorrow";
        }
        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        switch (method){
            case "findAllBorrow":
                String pageStr = req.getParameter("page");
                Integer page = Integer.parseInt(pageStr);
                List<Borrow> borrowList = bookService.findAllBorrowByState(0,page);
                req.setAttribute("list",borrowList);
                req.setAttribute("dataPrePage",6);
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getBorrowPagesByState(0));
                req.getRequestDispatcher("admin.jsp").forward(req,resp);
                break;
            case "handle":
                String idStr = req.getParameter("id");
                String stateStr = req.getParameter("state");
                Integer id = Integer.parseInt(idStr);
                Integer state = Integer.parseInt(stateStr);
                bookService.handleBorrow(id,state,admin.getId());
                if (state == 1 || state == 2){
                    resp.sendRedirect(req.getContextPath()+"/admin?page=1");
                }else {
                    resp.sendRedirect(req.getContextPath()+"/admin?method=getBorrowed&page=1");
                }
                break;
            case "getBorrowed":
                pageStr = req.getParameter("page");
                page = Integer.parseInt(pageStr);
                borrowList = bookService.findAllBorrowByState(1,page);
                req.setAttribute("list",borrowList);
                req.setAttribute("dataPrePage",6);
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getBorrowPagesByState(1));
                req.getRequestDispatcher("return.jsp").forward(req,resp);
                break;
        }

    }

}
