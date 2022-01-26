package com.harsh.kryptic.repository.user;

import com.harsh.kryptic.domain.user.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUsername(String username);
}
