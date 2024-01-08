package com.project.insert.domain.user.exception;

import com.project.insert.global.error.Exception.ErrorCode;
import com.project.insert.global.error.Exception.InsertException;

import java.io.IOException;

public class UserNotFoundException extends InsertException {
    public static final UserNotFoundException EXCEPTION = new UserNotFoundException(ErrorCode.USER_NOT_FOUND);

    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

}
