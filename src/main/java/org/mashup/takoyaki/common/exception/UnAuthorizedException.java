package org.mashup.takoyaki.common.exception;

import org.mashup.takoyaki.common.model.ErrorModel;

public class UnAuthorizedException extends BaseException {

    protected UnAuthorizedException(ErrorModel error) {
        super(error);
    }

}
