package org.mashup.takoyaki.service;

import com.google.common.hash.Hashing;
import org.mashup.takoyaki.common.exception.UserNotFoundException;
import org.mashup.takoyaki.dto.UpdateUserDto;
import org.mashup.takoyaki.entity.value.AccessToken;
import org.mashup.takoyaki.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * @author 한태웅
 * @since 2018-08-11
 */
public interface UserService {

    default AccessToken issueNewToken() {
        LocalDateTime issueTokenTime = LocalDateTime.now();
        String token = Hashing.sha256()
                .hashString(issueTokenTime.toString(), StandardCharsets.UTF_8)
                .toString();

        AccessToken accessToken = new AccessToken();
        accessToken.setToken(token);
        accessToken.setCreatedAt(issueTokenTime);

        return accessToken;
    }

    User registerUser();
    User getUserByToken(String accessToken) throws UserNotFoundException;
    void updateUserInfo(UpdateUserDto user, String token) throws UserNotFoundException;
    void uploadProfileImage(MultipartFile imageFile);

}
