package com.project.memor2.domain.post.exception;

import com.project.memor2.domain.post.exception.error.PostError;
import com.project.memor2.global.exception.BusinessException;

public class PostNotFoundException extends BusinessException {

    public static final PostNotFoundException EXCEPTION = new PostNotFoundException();

    private PostNotFoundException() {
        super(PostError.POST_NOT_FOUND);
    }

}