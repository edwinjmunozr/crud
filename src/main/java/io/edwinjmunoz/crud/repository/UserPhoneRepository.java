package io.edwinjmunoz.crud.repository;

import io.edwinjmunoz.crud.model.entity.User;
import io.edwinjmunoz.crud.model.entity.UserPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPhoneRepository extends JpaRepository<UserPhone, String> {

    List<UserPhone> findByUserId(User user);

}
