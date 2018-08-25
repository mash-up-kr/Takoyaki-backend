package org.mashup.takoyaki.common.exception;

import org.mashup.takoyaki.common.model.ErrorModel;

class BaseException extends RuntimeException {

    protected ErrorModel errorModel;

    private BaseException(ErrorModel error, Throwable cause) {
        super(error.getMsg(), cause);
        this.errorModel = error;
    }

    protected BaseException(ErrorModel error) {
        this(error, null);
    }

}
