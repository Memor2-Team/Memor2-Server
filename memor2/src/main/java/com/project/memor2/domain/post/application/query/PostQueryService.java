package com.project.memor2.domain.post.application.query;

import com.project.memor2.domain.post.client.dto.Post;
import com.project.memor2.global.common.dto.request.PageRequest;

import java.util.List;

public interface PostQueryService {
    List<Post> postList(PageRequest pageRequest);

    List<Post> myPostList(PageRequest pageRequest);
}