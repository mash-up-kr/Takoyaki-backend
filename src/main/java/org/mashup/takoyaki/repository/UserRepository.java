package org.mashup.takoyaki.repository;

import org.mashup.takoyaki.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author 한태웅
 * @since 2018-08-11
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByAccessToken_Token(String token);

}
