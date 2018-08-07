package org.mashup.takoyaki.service;

import lombok.extern.slf4j.Slf4j;
import org.mashup.takoyaki.dto.TokenDto;
import org.mashup.takoyaki.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //TODO 해싱알고리즘 이용하여 토큰 발급을 해야합니다.(ex. sha256, md5...) 토큰 발급과 동시에 유저 테이블에 정보 등록 필수
    @Override
    public TokenDto issueNewToken() {
        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken("9172ahsfag3718dhfua38");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = "2018-08-07 21:13:00";

        tokenDto.setCreatedAt(LocalDateTime.parse(dateTime, formatter).toString());

        return tokenDto;
    }
}
