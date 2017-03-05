package com.any.action.base;

import com.any.pub.Page;
import com.any.pub.exception.CommonError;
import com.any.pub.exception.MsgError;
import com.any.pub.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * tyl Action 模版
 * Created by avaio on 2017/3/4.
 */
public class BaseAction<T> {
    public ResponseEntity get(T t) {
        throw new MsgError(CommonError.ERROR_ACTION);
    }

    public ResponseEntity get(T t, Page page) {
        throw new MsgError(CommonError.ERROR_ACTION);
    }

    public ResponseEntity get(T t, Page page, @PathVariable Integer parentId) {
        throw new MsgError(CommonError.ERROR_ACTION);
    }
}
