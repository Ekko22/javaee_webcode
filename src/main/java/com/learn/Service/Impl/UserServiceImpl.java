package com.learn.Service.Impl;

import com.learn.Dao.UserDao;
import com.learn.Domain.User;
import com.learn.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author DingTian
 * @version 1.0
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public User selectOne(User user) {
        return userDao.selectOne(user.getName(),user.getPassword());
    }

    @Override
    public boolean insert(User user) {
     return userDao.insert(user) > 0;
    }

    @Override
    public boolean updateById(int id, User user) {
        return userDao.updateById(id,user) > 0;
    }

    @Override
    public List<User> selectPage(int page, int size) {
        return userDao.selectPage(page,size);
    }

    @Override
    public int count() {
        return userDao.count();
    }

    @Override
    public List<User> selectOneUser(int id) {
        return userDao.selectOneUser(id);
    }

    @Override
    public boolean deleteById(int id) {
        return userDao.deleteById(id);
    }

    @Override
    public List<User> selectByName(String name) {
        return userDao.selectByName(name);
    }
}
