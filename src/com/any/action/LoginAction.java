package com.any.action;

import com.any.action.base.BaseAction;
import com.any.pojo.Login;
import com.any.pub.ResponseEntity;
import com.any.util.EncryptUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * LoginAction
 * Created by avaio on 2016/12/24.
 */
@RequestMapping("login")
@Controller
public class LoginAction extends BaseAction<Login> {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity get(Login login) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(login.getLogin(), EncryptUtil.encryptByMd5(login.getPassword()));
        currentUser.login(token);
        return ResponseEntity.createOkResult(login);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity post(Login login) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(login.getLogin(), EncryptUtil.encryptByMd5(login.getPassword()));
        currentUser.login(token);
        return ResponseEntity.createOkResult(login);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity put(Login login) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(login.getLogin(), EncryptUtil.encryptByMd5(login.getPassword()));
        currentUser.login(token);
        return ResponseEntity.createOkResult(login);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity delete(Login login) {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return ResponseEntity.createOkResult("");
    }


}
