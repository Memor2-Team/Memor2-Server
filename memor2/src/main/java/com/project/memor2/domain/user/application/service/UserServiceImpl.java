package com.project.memor2.domain.user.application.service;

import com.project.memor2.global.common.repository.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserSecurity userSecurity;

    @Override
    public String getUserName() {
        return userSecurity.getUser().getName();
    }

}