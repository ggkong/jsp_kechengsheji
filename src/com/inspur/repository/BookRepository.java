package com.inspur.repository;

import com.inspur.entity.Book;

import java.util.List;

public interface BookRepository {
    public List<Book> findAll(int index, int limit);
    public int count();
}
