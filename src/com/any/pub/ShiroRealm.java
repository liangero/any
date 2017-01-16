package com.any.pub;

import com.any.beans.User;
import com.any.service.UserService;
import com.any.util.EncryptUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.List;

/**
 * ShiroRealm 登录时权限赋予
 * Created by avaio on 2016/12/24.
 */
public class ShiroRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (token.getPrincipal() != null && token.getCredentials() != null) {
            User user = new User();
            user.setLogin(token.getPrincipal().toString());
            List<User> users = userService.get(user);
            if (!users.isEmpty()) {
                return new SimpleAuthenticationInfo(users.get(0).getLogin(), EncryptUtil.encryptByMd5(users.get(0).getPassword()), users.get(0).getName());
            }
        }
        if (token.getCredentials() == null) throw new AuthenticationException("密码不能为空");
        throw new AuthenticationException("用户不存在");
    }
}
