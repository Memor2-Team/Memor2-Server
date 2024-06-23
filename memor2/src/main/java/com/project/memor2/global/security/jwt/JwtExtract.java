package com.project.memor2.global.security.jwt;

import com.project.memor2.domain.user.client.dto.User;
import com.project.memor2.domain.user.domain.mapper.UserMapper;
import com.project.memor2.domain.user.domain.repository.jpa.UserRepository;
import com.project.memor2.domain.user.exception.UserNotFoundException;
import com.project.memor2.global.annotation.Jwt;
import com.project.memor2.global.security.auth.CustomUserDetails;
import com.project.memor2.global.security.jwt.enums.JwtType;
import com.project.memor2.global.security.jwt.exception.TokenTypeException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;

@Jwt
@RequiredArgsConstructor
public class JwtExtract {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Authentication getAuthentication(final String token) {
        final Jws<Claims> claims = jwtProvider.getClaims(token);

        if (isWrongType(claims, JwtType.ACCESS)) {
            throw TokenTypeException.EXCEPTION;
        }

        User user = userRepository.findByEmail(claims
                        .getBody()
                        .getSubject())
                .map(userMapper::toUser)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        final CustomUserDetails details = new CustomUserDetails(user);

        return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
    }

    public String extractTokenFromRequest(HttpServletRequest request) {
        return extractToken(request.getHeader(HttpHeaders.AUTHORIZATION));
    }

    public String extractToken(final String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return token;
    }

    public boolean isWrongType(final Jws<Claims> claims, final JwtType jwtType) {
        return !(claims.getHeader().get(Header.JWT_TYPE).equals(jwtType.toString()));
    }

}