package org.mashup.takoyaki.service;

import com.google.common.hash.Hashing;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mashup.takoyaki.SpringTestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class UserServiceTest extends SpringTestSupport {

    @Test
    public void userServiceTest() {
        ServletContext servletContext = super.wac.getServletContext();

        Assert.assertNotNull(servletContext);
        Assert.assertTrue(servletContext instanceof MockServletContext);
        Assert.assertNotNull(super.wac.getBean("userServiceImpl"));
    }

    @Test
    public void sha256Test() {
        String token = Hashing.sha256()
                .hashString(LocalDateTime.now().toString(), StandardCharsets.UTF_8)
                .toString();

        System.out.println(token);
    }

}
