package com.project.memor2.domain.user.domain.mapper;

import com.project.memor2.domain.auth.client.dto.request.SignUpRequest;
import com.project.memor2.domain.user.client.dto.User;
import com.project.memor2.domain.user.domain.UserEntity;
import com.project.memor2.global.annotation.Mapper;

@Mapper
public class UserMapper {

    public User toUser(UserEntity userEntity) {
        return User.builder()
                .idx(userEntity.getIdx())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .userRole(userEntity.getUserRole())
                .build();
    }

    public UserEntity toCreate(SignUpRequest request,String password) {
        return UserEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(password)
                .build();
    }

}