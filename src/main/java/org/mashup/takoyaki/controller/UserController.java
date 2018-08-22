package org.mashup.takoyaki.controller;

import lombok.extern.slf4j.Slf4j;
import org.mashup.takoyaki.common.model.ApiResponseModel;
import org.mashup.takoyaki.dto.UpdateUserDto;
import org.mashup.takoyaki.entity.User;
import org.mashup.takoyaki.entity.value.AccessToken;
import org.mashup.takoyaki.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/v1")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/issue-token", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ApiResponseModel<AccessToken> issueToken() {
        ApiResponseModel<AccessToken> response = new ApiResponseModel<>();
        response.setCode(HttpStatus.OK.value());
        response.setMsg(HttpStatus.OK.getReasonPhrase());
        response.setResult(userService.registerUser().getAccessToken());

        return response;
    }

    @PutMapping(value = "/user-information", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void updateUserInfo(@RequestHeader("Access-Token") String accessToken,
                               @RequestBody UpdateUserDto updateUserDto,
                               HttpServletResponse response) {
        log.info("updateUserDto : {}", updateUserDto.toString());

        userService.updateUserInfo(updateUserDto, accessToken);
        response.setStatus(HttpStatus.NO_CONTENT.value());
    }

}
