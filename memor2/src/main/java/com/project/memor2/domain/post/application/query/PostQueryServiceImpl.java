package com.project.memor2.domain.post.application.query;

import com.project.memor2.domain.post.client.dto.Post;
import com.project.memor2.domain.post.domain.repository.query.PostQueryRepository;
import com.project.memor2.global.common.dto.request.PageRequest;
import com.project.memor2.global.common.repository.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostQueryServiceImpl implements PostQueryService {

    private final PostQueryRepository postQueryRepository;
    private final UserSecurity userSecurity;

    @Override
    public List<Post> postList(PageRequest pageRequest) {
        return postQueryRepository.postList(pageRequest);
    }

    @Override
    public List<Post> myPostList(PageRequest pageRequest) {
        return postQueryRepository.myPostList(pageRequest, userSecurity.getUser().getEmail());
    }

}