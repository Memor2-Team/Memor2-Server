package com.project.memor2.domain.post.exception;

import com.project.memor2.domain.post.exception.error.PostError;
import com.project.memor2.global.exception.BusinessException;

public class PostExistException extends BusinessException {

    public static final PostExistException EXCEPTION = new PostExistException();

    private PostExistException() {
        super(PostError.POST_EXIST);
    }

}