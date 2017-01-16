package com.any.service;

import com.any.beans.User;
import com.any.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by avaio on 2016/12/23.
 */
@Service
public class UserService implements BaseService<User> {
    @Resource
    private UserDao userDao;

    public List<User> get(User user) {
        return userDao.get(user);
    }

    public User post(User user) {
        return userDao.post(user);
    }

    public User put(User user) {
        return userDao.post(user);
    }

    public void delete(User user) {
        userDao.delete(user);
    }
}
