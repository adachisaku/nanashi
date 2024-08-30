package com.example.demo.UserController;

import com.example.demo.model.User;
import com.example.demo.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        logger.info("登录用户{}", user.getUsername());
        boolean isValidUser = userService.validateUser(user.getUsername(), user.getPassword());
        if (isValidUser) {
            logger.info("登录成功: {}", user.getUsername());
            return ResponseEntity.ok().body("{\"message\": \"Login success\"}");
        } else {
            logger.warn("登录失败: {}", user.getUsername());
            return ResponseEntity.badRequest().body("{\"message\": \"Login failed\"}");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        logger.info("注册用户: {}", user.getUsername());
        try {
            userService.registerUser(user);
            logger.info("注册成功: {}", user.getUsername());
            return ResponseEntity.ok().body("{\"message\": \"User registered successfully\"}");
        } catch (Exception e) {
            logger.error("注册失败: {}", user.getUsername(), e);
            return ResponseEntity.badRequest().body("{\"message\": \"Registration failed: " + e.getMessage() + "\"}");
        }
    }
}