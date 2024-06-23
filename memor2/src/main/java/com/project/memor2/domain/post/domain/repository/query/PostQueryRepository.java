package com.project.memor2.domain.post.domain.repository.query;

import com.project.memor2.domain.post.client.dto.Post;
import com.project.memor2.global.common.dto.request.PageRequest;

import java.util.List;

public interface PostQueryRepository {
    List<Post> postList(PageRequest request);

    List<Post> myPostList(PageRequest request, String email);
}