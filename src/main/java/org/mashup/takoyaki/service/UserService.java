package org.mashup.takoyaki.service;

import org.mashup.takoyaki.dto.TokenDto;

public interface UserService {

    TokenDto issueNewToken();

}
