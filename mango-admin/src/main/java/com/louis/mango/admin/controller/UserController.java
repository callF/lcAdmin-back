package com.louis.mango.admin.controller;

import com.louis.mango.admin.model.User;
import com.louis.mango.admin.service.UserService;
import com.louis.mango.core.http.HttpResult;
import com.louis.mango.core.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/list")
    public HttpResult getUsers(@RequestBody Map<String, Object> map) {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum((Integer) map.get("pageNum"));
        pageRequest.setPageSize((Integer) map.get("pageSize"));
        return HttpResult.ok(userService.getUsers(pageRequest, map));
    }

    @GetMapping(value = "/del")
    public HttpResult delUser(@RequestParam Long id) {
        return HttpResult.ok(userService.delUser(id));
    }

    @GetMapping(value = "/resetPassword")
    public HttpResult resetPassword(@RequestParam Long id) {
        return HttpResult.ok(userService.resetPassword(id));
    }

    @PostMapping(value = "/add")
    public HttpResult addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping(value = "/update")
    public HttpResult updateUser(@RequestBody User user) {
        return HttpResult.ok(userService.updateUser(user));
    }
}
