package org.mashup.takoyaki.controller;

import lombok.extern.slf4j.Slf4j;
import org.mashup.takoyaki.common.model.ApiResponseModel;
import org.mashup.takoyaki.dto.TokenDto;
import org.mashup.takoyaki.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/issue/token", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ApiResponseModel<TokenDto> issueToken() {
        ApiResponseModel<TokenDto> response = new ApiResponseModel<>();
        response.setCode(HttpStatus.OK.value());
        response.setMsg(HttpStatus.OK.toString());
        response.setResult(userService.issueNewToken());

        return response;
    }

}
