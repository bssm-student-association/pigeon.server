package com.project.insert.global.jwt.exception;

import com.project.insert.global.error.Exception.ErrorCode;
import com.project.insert.global.error.Exception.InsertException;

public class InvalidJwtException extends InsertException {
    public static InvalidJwtException EXCEPTION = new InvalidJwtException(ErrorCode.INVALID_TOKEN);

    public InvalidJwtException(ErrorCode errorCode) {
        super(errorCode);
    }
}
