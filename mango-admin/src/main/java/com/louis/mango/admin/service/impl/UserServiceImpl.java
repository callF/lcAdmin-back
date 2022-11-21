package com.louis.mango.admin.service.impl;

import com.louis.mango.admin.constant.SysConstants;
import com.louis.mango.admin.dao.UserMapper;
import com.louis.mango.admin.model.User;
import com.louis.mango.admin.service.UserService;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.MybatisPageHelper;
import com.louis.mango.core.page.PageRequest;
import com.louis.mango.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        return userMapper.findByName(username);
    }

    @Override
    public PageResult getUsers(PageRequest pageRequest, Map map) {
        PageResult pageResult = null;
        return MybatisPageHelper.findPage(pageRequest, userMapper, "getUsers", map);
    }

    @Override
    public Object delUser(Long id) {
        userMapper.delUser(id);
        return null;
    }

    @Override
    public Object resetPassword(Long id) {
        User user = new User();
        user.setId(id);
        user.setSalt(SysConstants.DEFAULT_SALT);
        user.setPassword(SysConstants.DEFAULT_PASSWORD);
        updateUser(user);
        return null;
    }

    @Override
    public HttpResult addUser(User user) {
        User user1 = findByUserName(user.getUsername());
        if (user1 == null) {
            user.setPassword(SysConstants.DEFAULT_PASSWORD);
            user.setSalt(SysConstants.DEFAULT_SALT);
            userMapper.addUser(user);
            return HttpResult.ok(null);
        } else {
            return HttpResult.error("账号重复");
        }
    }

    @Override
    public Object updateUser(User user) {
        userMapper.updateUser(user);
        return null;
    }
}
