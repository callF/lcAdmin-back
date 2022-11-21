package com.louis.mango.admin.dao;

import com.louis.mango.admin.model.SysUser;
import com.louis.mango.admin.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface UserMapper {
    User findByName(String username);

    List<User> getUsers(LinkedHashMap map);

    void delUser(Long id);
    void addUser(User user);
    void updateUser(User user);
}
