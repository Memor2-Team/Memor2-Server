package com.project.memor2.domain.user.application.service;

import com.project.memor2.domain.user.client.dto.User;
import com.project.memor2.domain.user.domain.mapper.UserMapper;
import com.project.memor2.domain.user.domain.repository.jpa.UserRepository;
import com.project.memor2.domain.user.exception.UserNotFoundException;
import com.project.memor2.global.common.repository.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserSecurity userSecurity;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User getUser() {
        return userRepository
                .findByIdx(userSecurity.getUser().getIdx())
                .map(userMapper::toUser)
                .orElseThrow(()-> UserNotFoundException.EXCEPTION);
    }


}