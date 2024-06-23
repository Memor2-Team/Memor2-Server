package com.project.memor2.domain.post.application.service;

import com.project.memor2.domain.post.client.dto.Post;
import com.project.memor2.domain.post.client.dto.request.PostEditRequest;
import com.project.memor2.domain.post.client.dto.request.PostRegisterRequest;
import com.project.memor2.domain.post.domain.mapper.PostMapper;
import com.project.memor2.domain.post.domain.repository.jpa.PostRepository;
import com.project.memor2.domain.post.exception.PostNotFoundException;
import com.project.memor2.domain.user.client.dto.User;
import com.project.memor2.global.common.repository.UserSecurity;
import com.project.memor2.global.exception.ForbiddenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserSecurity userSecurity;

    @Override
    public void registerPost(PostRegisterRequest postRegisterRequest){
        postRepository.save(postMapper
                .toCreate(postRegisterRequest,userSecurity.getUser().getEmail()));
    }

    @Override
    public void editPost(PostEditRequest postEditRequest){
        Post post = postRepository
                .findByIdx(postEditRequest.getIdx())
                .map(postMapper::toPost)
                .orElseThrow(()-> PostNotFoundException.EXCEPTION);
        User user = userSecurity.getUser();
        if(!post.getAuthor().equals(user.getEmail())){
            throw ForbiddenException.EXCEPTION;
        }
        post.setTitle(postEditRequest.getTitle());
        post.setContent(postEditRequest.getContent());
        postRepository.save(postMapper.toEdit(post));
    }

    @Override
    public void deletePost(Long idx){
        postRepository.deleteById(idx);
    }

}