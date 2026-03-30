package com.health.user.controller;

import com.health.common.util.Result;
import com.health.user.entity.User;
import com.health.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<User> register(@RequestBody User user) {
        return Result.success(userService.register(user));
    }

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestParam String username, @RequestParam String password) {
        String token = userService.login(username, password);
        if (token == null) {
            return Result.error("用户名或密码错误");
        }
        User user = userService.getByUsername(username);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("user", user);
        return Result.success(map);
    }

    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable Integer id) {
        return Result.success(userService.getById(id));
    }
}