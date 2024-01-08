package com.project.insert.domain.user.exception;

import com.project.insert.global.error.Exception.ErrorCode;
import com.project.insert.global.error.Exception.InsertException;

public class UserNotLoginException extends InsertException {
    public static final UserNotFoundException EXCEPTION = new UserNotFoundException(ErrorCode.USER_NOT_LOGIN);

    public UserNotLoginException(ErrorCode errorCode) {
        super(errorCode);
    }
}
