package com.cvt.user.service;

import com.cvt.user.dao.UserDao;
import com.cvt.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    public User create(User user){
        return userDao.save(user);
    }
    public List<User> findAll(){
        return userDao.findAll();
    }
    public User findOne(final Long id){
        return userDao.findById(id).orElse(null);
    }
    public User findByEmail(final String email){
        return userDao.findByEmail(email).orElse(null);
    }
    public void delete(final Long id){
        userDao.deleteById(id);
    }
}
