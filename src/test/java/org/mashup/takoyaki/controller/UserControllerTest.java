package org.mashup.takoyaki.controller;

import org.junit.Assert;
import org.junit.Test;
import org.mashup.takoyaki.SpringTestSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class UserControllerTest extends SpringTestSupport {

    @Test
    public void issueTokenApiTest() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/issue-token"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        Assert.assertEquals(MediaType.APPLICATION_JSON_UTF8_VALUE,
                mvcResult.getResponse()
                        .getContentType());

        Assert.assertEquals(HttpStatus.OK.value(),
                mvcResult.getResponse()
                        .getStatus());
    }

}
