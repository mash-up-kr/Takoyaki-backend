package org.mashup.takoyaki.common.exception;

import org.mashup.takoyaki.common.model.ErrorModel;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException() {
        super(new ErrorModel(4004, "해당 유저가 존재하지 않습니다."));
    }

}
