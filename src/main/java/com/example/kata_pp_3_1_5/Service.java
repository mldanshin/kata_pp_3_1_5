package com.example.kata_pp_3_1_5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Service {
    private UserDao userDao;

    @Autowired
    public Service(UserDao userDao) {
        this.userDao = userDao;
        List<User> users = this.userDao.getList();
        this.userDao.store(new User(3L, "James", "Brown", (byte) 23));
        this.userDao.update(new User(3L, "Thomas", "Shelby", (byte) 23));
        this.userDao.delete(3);
        System.out.println(this.userDao.getCode());
    }
}
