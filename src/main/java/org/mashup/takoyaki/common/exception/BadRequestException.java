package org.mashup.takoyaki.common.exception;

import org.mashup.takoyaki.common.model.ErrorModel;
import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException {

    public BadRequestException() {
        super(new ErrorModel(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase()));
    }

}
