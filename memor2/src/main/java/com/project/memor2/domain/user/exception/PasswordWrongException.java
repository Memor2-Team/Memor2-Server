package com.project.memor2.domain.user.exception;

import com.project.memor2.domain.user.exception.error.UserError;
import com.project.memor2.global.exception.BusinessException;

public class PasswordWrongException extends BusinessException {

    public static final PasswordWrongException EXCEPTION = new PasswordWrongException();

    private PasswordWrongException() {
        super(UserError.PASSWORD_WRONG);
    }

}