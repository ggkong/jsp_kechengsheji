package com.inspur.service;

import com.inspur.entity.Book;
import com.inspur.entity.Borrow;

import java.util.List;

public interface BookService {
    public List<Book> findAll(int page);
    public  int getPages();
    public void addBrrow(Integer bookid,Integer readerid);
    public List<Borrow> findAllBorrowByReaderId(Integer id, Integer page);
    public int getBorrowPages(Integer readerid);
    public List<Borrow> findAllBorrowByState(Integer state,Integer page);
    public int getBorrowPagesByState(Integer state);
    public void handleBorrow(Integer borrowId,Integer state,Integer adminId);
}
