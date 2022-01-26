package com.harsh.kryptic.service.user.impl;

import com.harsh.kryptic.domain.user.UserInfo;
import com.harsh.kryptic.exceptions.ApiArgumentException;
import com.harsh.kryptic.exceptions.AuthFailedException;
import com.harsh.kryptic.exceptions.NoRecordsFoundException;
import com.harsh.kryptic.repository.user.UserRepository;
import com.harsh.kryptic.service.user.UserService;
import com.harsh.kryptic.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

import static com.harsh.kryptic.utils.Utils.isNull;

public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<?> createUser(UserInfo userInfo) {
        userInfo.setPassword(Utils.hashPassword(userInfo.getPassword()));
        return ResponseEntity.ok(userRepository.saveAndFlush(userInfo));
    }

    @Override
    public ResponseEntity<?> authenticateUser(UserInfo userInfo) {
        if (isNull(userInfo.getUsername())){
            throw new ApiArgumentException("Enter a valid username");
        }

        if (isNull(userInfo.getPassword())){
            throw new ApiArgumentException("Enter a valid password");
        }

        UserInfo dbUser = userRepository.findByUsername(userInfo.getUsername());
        if (isNull(dbUser)){
            throw new NoRecordsFoundException("User with username " + userInfo.getUsername() + " not found");
        }

        boolean correctPassword = Utils.comparePassword(userInfo.getPassword(), dbUser.getPassword());

        if (correctPassword) {
            // validated successfully
            return ResponseEntity.ok().build();
        } else {
            throw new AuthFailedException("Invalid credentials");
        }
    }

    @Override
    public ResponseEntity<?> getUserDetails(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateUser(UserInfo userInfo, Long id) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userRepository.findByUsername(username);

        if (isNull(userInfo)) {
            throw new NoRecordsFoundException("User not found");
        }

        return new User(username,userInfo.getPassword(), Collections.emptyList());
    }
}
