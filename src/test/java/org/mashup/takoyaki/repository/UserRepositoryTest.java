package org.mashup.takoyaki.repository;

import org.junit.Assert;
import org.junit.Test;
import org.mashup.takoyaki.SpringTestSupport;
import org.mashup.takoyaki.entity.User;
import org.mashup.takoyaki.entity.value.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryTest extends SpringTestSupport {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUserRepositoryTest() {
        userRepository.save(mockUser());

        Assert.assertEquals("askjfaoi932nadf823hnae9832h", userRepository.findById(1L).get().getAccessToken().getToken());
    }

    private User mockUser() {
        User user = new User();
        user.setId(1L);
        user.setNickname("codingsquid");
        user.setEamil("gksxodnd007@naver.com");
        AccessToken accessToken = new AccessToken();
        accessToken.setToken("askjfaoi932nadf823hnae9832h");
        user.setAccessToken(accessToken);

        return user;
    }

}
