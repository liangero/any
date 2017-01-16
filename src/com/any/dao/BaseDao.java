package com.any.dao;

import org.springframework.orm.hibernate5.HibernateTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by avaio on 2016/12/21.
 */

public class BaseDao<T> {
    @Resource
    private HibernateTemplate hibernateTemplate;

    public List<T> get(T t) {
        return hibernateTemplate.findByExample(t);
    }

    public T post(T t) {
        hibernateTemplate.save(t);
        return t;
    }

    public T put(T t) {
        hibernateTemplate.update(t);
        return t;
    }

    public void delete (T t){
        hibernateTemplate.delete(t);
    }
}
