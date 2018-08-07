package org.mashup.takoyaki.common.model;

import lombok.Data;

@Data
public class ApiResponseModel<T> {

    private int code;
    private String msg;
    private T result;

}
