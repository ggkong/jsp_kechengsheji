package com.inspur.repository;

import com.inspur.entity.Admin;

public interface AdminRepository {
    public Admin login(String username,String password);
}
