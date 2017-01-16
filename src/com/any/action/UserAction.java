package com.any.action;

import com.any.action.base.BaseAction;
import com.any.beans.User;
import com.any.pub.ResponseEntity;
import org.springframework.stereotype.Controller;

/**
 * Created by avaio on 2016/12/23.
 */
@Controller
public class UserAction implements BaseAction<User> {
    public ResponseEntity get(User user) {
        return null;
    }

    public ResponseEntity post(User user) {
        return null;
    }

    public ResponseEntity put(User user) {
        return null;
    }

    public ResponseEntity delete(User user) {
        return null;
    }
}
