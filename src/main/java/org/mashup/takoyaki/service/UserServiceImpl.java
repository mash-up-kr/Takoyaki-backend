package org.mashup.takoyaki.service;

import org.mashup.takoyaki.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //TODO 해싱알고리즘 이용하여 토큰 발급을 해야합니다.(ex. sha256, md5...) 토큰 발급과 동시에 유저 테이블에 정보 등록 필수
    @Override
    public String issueToken() {
        return "test-token-21937&^23178924jahf9";
    }
}
