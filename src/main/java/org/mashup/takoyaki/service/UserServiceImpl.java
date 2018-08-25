package org.mashup.takoyaki.service;

import lombok.extern.slf4j.Slf4j;
import org.mashup.takoyaki.common.exception.UserNotFoundException;
import org.mashup.takoyaki.dto.UpdateUserDto;
import org.mashup.takoyaki.entity.User;
import org.mashup.takoyaki.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 한태웅
 * @since 2018-08-11
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public User registerUser() {
        User user = new User();
        user.setAccessToken(issueNewToken());
        userRepository.save(user);
        log.info("등록된 유저 정보 : {}", user.toString());
        return user;
    }

    @Override
    public User getUserByToken(String accessToken) throws UserNotFoundException {
        return userRepository.findByAccessToken_Token(accessToken).orElseThrow(UserNotFoundException::new);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public void updateUserInfo(UpdateUserDto updateUserDto, String token) throws UserNotFoundException {
        User user = userRepository.findByAccessToken_Token(token)
                .orElseThrow(UserNotFoundException::new);

        user.setEamil(updateUserDto.getEmail());
        if (!user.getNickname().equals(updateUserDto.getNickname())) {
            user.setNickname(updateUserDto.getNickname());
        }

        userRepository.save(user);
    }

    @Override
    public void uploadProfileImage(MultipartFile imageFile) {
    }
}
