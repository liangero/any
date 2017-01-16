package com.any.service;

import java.util.List;

/**
 * Created by avaio on 2016/12/21.
 */
public interface BaseService<T> {
    List<T> get(T t);
    T post(T t);
    T put(T t);
    void delete(T t);
}
