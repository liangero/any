package com.any.action.base;

import com.any.pub.ResponseEntity;

/**
 * BaseAction
 * Created by avaio on 2016/12/21.
 */
public interface BaseAction<T> {
    ResponseEntity get(T t);
    ResponseEntity post(T t);
    ResponseEntity put(T t);
    ResponseEntity delete(T t);
}
