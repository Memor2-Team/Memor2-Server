package com.project.memor2.domain.user.exception;

import com.project.memor2.domain.user.exception.error.UserError;
import com.project.memor2.global.exception.BusinessException;

public class UserExistException extends BusinessException {

    public static final UserExistException EXCEPTION = new UserExistException();

    private UserExistException(){
        super(UserError.USER_EXIST);
    }

}