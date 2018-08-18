package org.mashup.takoyaki.common.exception;

import org.mashup.takoyaki.common.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ErrorModel> responseError(BaseException exception) {
        ErrorModel error = exception.errorModel;
        switch (error.getCode()) {
            case 400:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            case 401:
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
            case 4004:
                return ResponseEntity.status(HttpStatus.OK).body(error);
            default:
                return ResponseEntity.status(HttpStatus.OK).body(new ErrorModel(0, "정의되지 않은 exception이 발생하였습니다."));
        }
    }

}
