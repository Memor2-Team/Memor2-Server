package com.project.memor2.domain.user.exception;

import com.project.memor2.domain.user.exception.error.UserError;
import com.project.memor2.global.exception.BusinessException;

public class UserNotFoundException extends BusinessException {

    public static final UserNotFoundException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException(){
        super(UserError.USER_NOT_FOUND);
    }

}