package com.inspur.service;

import com.inspur.entity.Book;

import java.util.List;

public interface BookService {
    public List<Book> findAll(int page);
    public  int getPages();
}
