package com.any.dao;

import com.any.beans.User;
import com.any.dao.base.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * Created by avaio on 2016/12/23.
 */
@Repository
public class UserDao extends BaseDao<User> {
    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
