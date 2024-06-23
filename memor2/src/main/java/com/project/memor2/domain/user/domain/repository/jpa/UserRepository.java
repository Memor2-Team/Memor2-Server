package com.project.memor2.domain.user.domain.repository.jpa;

import com.project.memor2.domain.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    UserEntity getByEmail(String email);

    Optional<UserEntity> findByIdx(Long idx);

}