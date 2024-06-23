package com.project.memor2.domain.auth.client.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminSignUpRequest {

    private String name;
    private String email;
    private String password;

}