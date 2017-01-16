package com.any.action.base;

import com.any.pub.ResponseEntity;

/**
 * ChildAction
 * Created by avaio on 2017/1/12.
 */
public interface ChildAction<T> {
    ResponseEntity get(T t, Integer parentId);
    ResponseEntity post(T t, Integer parentId);
    ResponseEntity put(T t, Integer parentId);
    ResponseEntity delete(T t, Integer parentId);

}
