package org.mashup.takoyaki.common.exception;

import org.mashup.takoyaki.common.model.ErrorModel;
import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends BaseException {

    public UnAuthorizedException() {
        super(new ErrorModel(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase()));
    }

}
