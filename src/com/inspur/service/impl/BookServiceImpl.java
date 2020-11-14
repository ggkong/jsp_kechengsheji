package com.inspur.service.impl;

import com.inspur.entity.Book;
import com.inspur.repository.BookRepository;
import com.inspur.repository.impl.BookerRepositoryImpl;
import com.inspur.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookRepository bookRepository = new BookerRepositoryImpl();
    private final int LIMIT = 6;

    @Override
    public List<Book> findAll(int page) {
        int index = (page - 1) * LIMIT;
        return bookRepository.findAll(index,LIMIT);
    }

    @Override
    public int getPages() {
        int count = bookRepository.count();
        if (count % LIMIT == 0){
            return count/LIMIT;
        }else {
            return count/LIMIT + 1;
        }
    }
}
