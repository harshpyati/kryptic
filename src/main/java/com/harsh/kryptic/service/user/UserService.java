package com.harsh.kryptic.service.user;

import com.harsh.kryptic.domain.user.UserInfo;
import org.springframework.http.ResponseEntity;

public interface UserService {
    public ResponseEntity<?> createUser(UserInfo userInfo);

    public ResponseEntity<?> authenticateUser(UserInfo userInfo);

    public ResponseEntity<?> getUserDetails(Long id);

    public ResponseEntity<?> updateUser(UserInfo userInfo, Long id);

    public void deleteUser(Long id);

    // public void tempDeleteUser(Long id);
}
