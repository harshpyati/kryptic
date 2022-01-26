package com.harsh.kryptic.controller.auth;

import com.harsh.kryptic.domain.user.UserInfo;
import com.harsh.kryptic.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserInfo user){
        return userService.createUser(user);
    }
}
