package com.project.memor2.global.exception;

import com.project.memor2.global.exception.error.ErrorCode;

public class ForbiddenException extends BusinessException {
    
    public static final ForbiddenException EXCEPTION = new ForbiddenException();
    
    private ForbiddenException(){
        super(ErrorCode.FORBIDDEN);
    }
    
}
