package com.project.memor2.global.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Configuration
@EnableWebSecurity
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SecurityConfiguration {
    //SecurityConfig 정의 annotation 클래스
}