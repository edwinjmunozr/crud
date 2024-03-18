package io.edwinjmunoz.crud.repository;

import io.edwinjmunoz.crud.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "update users set last_login=current_timestamp, token= :token where email= :email ", nativeQuery = true)
    void updateLastLoginByEmail(@Param("token") String token, @Param("email") String email);

}
