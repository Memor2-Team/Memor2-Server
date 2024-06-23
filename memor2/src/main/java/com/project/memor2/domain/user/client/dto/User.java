package com.project.memor2.domain.user.client.dto;

import com.project.memor2.domain.user.domain.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long idx;
    private String name;
    private String email;
    private String password;
    private UserRole userRole;

}