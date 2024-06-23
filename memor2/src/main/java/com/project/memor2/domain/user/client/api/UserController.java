package com.project.memor2.domain.user.client.api;

import com.project.memor2.domain.user.application.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "유저", description = "유저 API")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    @Operation(summary = "유저 이름 조회", description = "현재 로그인한 유저 이름을 조회합니다.")
    public String getUserName(){
        return userService.getUserName();
    }

}