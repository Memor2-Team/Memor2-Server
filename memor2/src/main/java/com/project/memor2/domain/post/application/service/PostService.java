package com.project.memor2.domain.post.application.service;

import com.project.memor2.domain.post.client.dto.Post;
import com.project.memor2.domain.post.client.dto.request.PostEditRequest;
import com.project.memor2.domain.post.client.dto.request.PostRegisterRequest;

public interface PostService {
    void registerPost(PostRegisterRequest postRegisterRequest);

    void editPost(PostEditRequest postEditRequest);

    void deletePost(Long idx);

    Post findPostByIdx(Long idx);
}