package com.project.insert.global.oauth.exception;

import com.project.insert.global.error.Exception.ErrorCode;
import com.project.insert.global.error.Exception.InsertException;

public class BsmAuthIdInvalidClientException extends InsertException {
    public static final BsmAuthIdInvalidClientException EXCEPTION = new BsmAuthIdInvalidClientException(ErrorCode.BSM_AUTH_INVALID_CLIENT);

    public BsmAuthIdInvalidClientException(ErrorCode errorCode) {
        super(errorCode);
    }
}
