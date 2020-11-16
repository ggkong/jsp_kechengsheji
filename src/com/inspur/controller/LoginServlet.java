package com.inspur.controller;

import com.inspur.entity.Admin;
import com.inspur.entity.Book;
import com.inspur.entity.Borrow;
import com.inspur.entity.Reader;
import com.inspur.service.BookService;
import com.inspur.service.LoginService;
import com.inspur.service.impl.BookServiceImpl;
import com.inspur.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LoginServlet extends HttpServlet {
    private LoginService loginService = new LoginServiceImpl();

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String type = req.getParameter("type");
        System.out.println(username);
        System.out.println(password);
        Object object = loginService.login(username,password,type);
        System.out.println(object);
        if (object != null){
            System.out.println("登录成功");
            HttpSession session = req.getSession();
            switch (type){
                case "reader":
                    session.setAttribute("reader",(Reader) object);
                    resp.sendRedirect(req.getContextPath()+"/book?page=1");
                    break;
                case "admin":
                    session.setAttribute("admin",(Admin) object);
                    resp.sendRedirect(req.getContextPath()+"/admin?method=findAllBorrow&page=1");
                    break;
            }
            return;
        }else {
            System.out.println("登录失败");
            resp.sendRedirect("login.jsp");
            return;
        }
    }
}

