package com.ypx.service;

import com.ypx.entity.T_User;
import com.ypx.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookDao bd;
    public void addUser(T_User u){
        this.bd.add(u);
    }

    @Override
    public String toString() {
        return this.bd.toString();
    }
}
