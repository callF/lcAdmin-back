package com.louis.mango.admin.service;

import com.louis.mango.admin.model.User;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.PageRequest;
import com.louis.mango.core.page.PageResult;

import java.util.Map;

public interface UserService {
    User findByUserName(String username);

    PageResult getUsers(PageRequest pageRequest, Map map);

    Object delUser(Long id);

    Object resetPassword(Long id);

    HttpResult addUser(User user);

    Object updateUser(User user);
}
