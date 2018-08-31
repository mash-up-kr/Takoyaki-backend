package org.mashup.takoyaki.controller;

import org.junit.Assert;
import org.junit.Test;
import org.mashup.takoyaki.SpringTestSupport;
import org.mashup.takoyaki.common.model.ApiResponseModel;
import org.mashup.takoyaki.entity.User;
import org.mashup.takoyaki.entity.value.AccessToken;
import org.mashup.takoyaki.service.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest extends SpringTestSupport {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void issueTokenApiIntegrateTest() throws Exception {
        this.mockMvc.perform(get("/v1/issue-token"))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.result.token").exists())
                .andExpect(jsonPath("$.result.created_at").exists());
    }

    @Test
    public void issueTokenApiUnitTest() {
        MockitoAnnotations.initMocks(this);
        LocalDateTime testTime = LocalDateTime.now();
        givenUserService(testTime);

        ApiResponseModel<AccessToken> result = userController.issueToken();

        Assert.assertEquals(result.getCode(), HttpStatus.OK.value());
        Assert.assertEquals(result.getMsg(), HttpStatus.OK.getReasonPhrase());
        Assert.assertEquals(result.getResult().getToken(), "this is sample access-token");
        Assert.assertEquals(result.getResult().getCreatedAt(), testTime);
    }

    private void givenUserService(LocalDateTime createdAt) {
        User mockUser = new User();
        mockUser.setAccessToken(new AccessToken("this is sample access-token", createdAt));
        when(userService.registerUser()).thenReturn(mockUser);
    }

}
