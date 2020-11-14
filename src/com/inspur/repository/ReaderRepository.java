package com.inspur.repository;

import com.inspur.entity.Reader;

public interface ReaderRepository {
    public Reader login(String username,String password);
}
