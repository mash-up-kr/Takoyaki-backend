package org.mashup.takoyaki.common.exception;

import org.mashup.takoyaki.common.model.ErrorModel;
import org.springframework.http.HttpStatus;

public class ServerErrorException extends BaseException {

    public ServerErrorException() {
        super(new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
    }
}
