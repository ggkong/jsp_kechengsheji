package com.inspur.service.impl;

import com.inspur.entity.Reader;
import com.inspur.repository.AdminRepository;
import com.inspur.repository.ReaderRepository;
import com.inspur.repository.impl.AdminRepositoryImpl;
import com.inspur.repository.impl.ReaderRepositoryImpl;
import com.inspur.service.LoginService;

import java.util.Objects;

public class LoginServiceImpl implements LoginService {
    private ReaderRepository readerRepository = new ReaderRepositoryImpl();
    private AdminRepository adminRepository = new AdminRepositoryImpl();

    @Override
    public Object login(String username, String password, String type) {
        Object object = null;
        switch (type){
            case "reader":
                object = readerRepository.login(username,password);
                break;
            case "admin":
                object = adminRepository.login(username, password);
        }
        return object;
    }
}
