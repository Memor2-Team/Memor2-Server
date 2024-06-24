package com.project.memor2.domain.post.domain.mapper;

import com.project.memor2.domain.post.client.dto.Post;
import com.project.memor2.domain.post.client.dto.request.PostEditRequest;
import com.project.memor2.domain.post.client.dto.request.PostRegisterRequest;
import com.project.memor2.domain.post.domain.PostEntity;
import com.project.memor2.global.annotation.Mapper;

@Mapper
public class PostMapper {

    public PostEntity toCreate(PostRegisterRequest request, String email) {
        return PostEntity.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(email)
                .build();
    }

    public Post toPost(PostEntity entity) {
        return Post.builder()
                .idx(entity.getIdx())
                .title(entity.getTitle())
                .content(entity.getContent())
                .author(entity.getAuthor())
                .createdDateTime(entity.getCreatedDateTime())
                .modifiedDateTime(entity.getModifiedDateTime())
                .build();
    }

    public PostEntity toEdit(Post post) {
        return PostEntity.builder()
                .idx(post.getIdx())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .createdDateTime(post.getCreatedDateTime())
                .build();
    }

}