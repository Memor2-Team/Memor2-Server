package com.project.memor2.domain.auth.client.api;

import com.project.memor2.domain.auth.client.dto.request.RefreshTokenRequest;
import com.project.memor2.domain.auth.client.dto.request.SignInRequest;
import com.project.memor2.domain.auth.client.dto.request.SignUpRequest;
import com.project.memor2.domain.auth.client.dto.response.JsonWebTokenResponse;
import com.project.memor2.domain.auth.client.dto.response.RefreshTokenResponse;
import com.project.memor2.domain.auth.service.AuthService;
import com.project.memor2.global.common.dto.response.BaseResponse;
import com.project.memor2.global.common.dto.response.BaseResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "인증", description = "인증 API")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    @Operation(summary = "유저 회원가입", description = "유저 회원가입")
    public BaseResponse signUp(@Validated @RequestBody SignUpRequest signUpRequest){
        authService.signUp(signUpRequest);
        return BaseResponse.created("회원가입이 완료되었습니다.");
    }

    @PostMapping("/sign-in")
    @Operation(summary = "로그인", description = "로그인")
    public BaseResponseData<JsonWebTokenResponse> signIn(@Validated @RequestBody SignInRequest signInRequest){
        return BaseResponseData.ok(
                "로그인 성공",
                authService.signIn(signInRequest));
    }

    @PostMapping("/refresh")
    @Operation(summary = "토큰 재발급", description = "access 토큰을 재발급 합니다")
    public BaseResponseData<RefreshTokenResponse> refresh(RefreshTokenRequest request){
        return BaseResponseData.ok(
                "토큰 재발급 성공",
                authService.refresh(request.getRefreshToken()));
    }

}