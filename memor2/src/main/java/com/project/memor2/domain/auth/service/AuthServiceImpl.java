package com.project.memor2.domain.auth.service;

import com.project.memor2.domain.auth.client.dto.request.SignInRequest;
import com.project.memor2.domain.auth.client.dto.request.SignUpRequest;
import com.project.memor2.domain.auth.client.dto.response.JsonWebTokenResponse;
import com.project.memor2.domain.auth.client.dto.response.RefreshTokenResponse;
import com.project.memor2.domain.user.client.dto.User;
import com.project.memor2.domain.user.domain.enums.UserRole;
import com.project.memor2.domain.user.domain.mapper.UserMapper;
import com.project.memor2.domain.user.domain.repository.jpa.UserRepository;
import com.project.memor2.domain.user.exception.PasswordWrongException;
import com.project.memor2.domain.user.exception.UserExistException;
import com.project.memor2.domain.user.exception.UserNotFoundException;
import com.project.memor2.global.security.jwt.JwtExtract;
import com.project.memor2.global.security.jwt.JwtProvider;
import com.project.memor2.global.security.jwt.enums.JwtType;
import com.project.memor2.global.security.jwt.exception.TokenTypeException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;
    private final JwtExtract jwtExtract;
//    private final FirebaseNotificationService firebaseNotificationService;

    @Override
    public void signUp(SignUpRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()){
            throw UserExistException.EXCEPTION;
        }
        userRepository.save(userMapper.toCreate(
                request,
                encoder.encode(request.getPassword()))
        );
    }

    @Override
    public JsonWebTokenResponse signIn(SignInRequest request) {
        User user = userRepository
                .findByEmail(request.getEmail())
                .map(userMapper::toUser)
                .orElseThrow(()-> UserNotFoundException.EXCEPTION);
        if (!encoder.matches(request.getPassword(), user.getPassword()))
            throw PasswordWrongException.EXCEPTION;
        return JsonWebTokenResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(request.getEmail(),user.getUserRole()))
                .refreshToken(jwtProvider.generateRefreshToken(request.getEmail(), user.getUserRole()))
                .build();
    }

    @Override
    public RefreshTokenResponse refresh(String token) {
        Jws<Claims> claims = jwtProvider.getClaims(jwtExtract.extractToken(token));
        if (jwtExtract.isWrongType(claims, JwtType.REFRESH)) {
            throw TokenTypeException.EXCEPTION;
        }
        return RefreshTokenResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(claims.getBody().getSubject(),
                        (UserRole) claims.getHeader().get("authority"))).build();
    }

}