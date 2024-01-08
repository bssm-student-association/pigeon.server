package com.project.insert.global.jwt.exception;

import com.project.insert.global.error.Exception.ErrorCode;
import com.project.insert.global.error.Exception.InsertException;

public class RefreshTokenExpiredException extends InsertException {
    public final static RefreshTokenExpiredException EXCEPTION = new RefreshTokenExpiredException(ErrorCode.REFRESH_TOKEN_EXPIRED);

    public RefreshTokenExpiredException(ErrorCode errorCode) {
        super(errorCode);
    }
}
