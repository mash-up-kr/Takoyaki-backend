package org.mashup.takoyaki.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorModel {

    private int code;
    private String msg;

}
