package com.inspur.controller;

import com.inspur.entity.Book;
import com.inspur.entity.Borrow;
import com.inspur.entity.Reader;
import com.inspur.service.BookService;
import com.inspur.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class BookServlet extends HttpServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 专业 用来 加载图书 数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method == null){
            method = "findAll";
        }
        HttpSession session = req.getSession();
        Reader reader = (Reader) session.getAttribute("reader");
        switch (method){
            case "findAll":
                String pageStr = req.getParameter("page");
                Integer page = Integer.parseInt(pageStr);
                List<Book> list = bookService.findAll(page);
                req.setAttribute("list",list);
                req.setAttribute("dataPrePage",6);
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getPages());
                req.getRequestDispatcher("index.jsp").forward(req,resp);
                break;
            case "addBorrow":
                String bookidStr = req.getParameter("bookid");
                Integer bookid = Integer.parseInt(bookidStr);
                // 添加 借书请求
                bookService.addBrrow(bookid,reader.getId());
                resp.sendRedirect(req.getContextPath()+"/book?method=findAllBorrow&page=1");
                break;
            case "findAllBorrow":
                pageStr = req.getParameter("page");
                page  = Integer.parseInt(pageStr);
                // 展示当前用户的 所有 的 借书记录
                List<Borrow> borrowList = bookService.findAllBorrowByReaderId(reader.getId(),page);
                req.setAttribute("list",borrowList);
                req.setAttribute("dataPrePage",6);
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getBorrowPages(reader.getId()));
                req.getRequestDispatcher("borrow.jsp").forward(req,resp);

        }

    }
}
