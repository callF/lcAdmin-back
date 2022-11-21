package com.louis.mango.admin.controller;

import com.louis.mango.admin.model.User;
import com.louis.mango.admin.security.JwtAuthenticatioToken;
import com.louis.mango.admin.service.UserService;
import com.louis.mango.admin.util.PasswordUtils;
import com.louis.mango.admin.util.SecurityUtils;
import com.louis.mango.admin.vo.LoginBean;
import com.louis.mango.core.http.HttpResult;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("auth")
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("login")
    public HttpResult login(@RequestBody LoginBean loginBean, HttpServletRequest request) throws IOException {
        String password = loginBean.getPassword();
        String username = loginBean.getUsername();
        String decodePassword = new String(Base64.decodeBase64(password));
        User user = userService.findByUserName(username);
        if (user == null) {
            return HttpResult.error("账号不存在");
        }
        if (!PasswordUtils.matches(user.getSalt(), decodePassword, user.getPassword())) {
            return HttpResult.error("密码错误");
        }
        JwtAuthenticatioToken token = SecurityUtils.login(request, username, decodePassword, authenticationManager);
        return HttpResult.ok(token);
    }
}
