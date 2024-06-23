package com.project.memor2.domain.post.domain.repository.jpa;

import com.project.memor2.domain.post.domain.PostEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    Optional<PostEntity> findByIdx(Long idx);

    @Transactional(rollbackOn = Exception.class)
    void deleteByIdx(Long idx);

}