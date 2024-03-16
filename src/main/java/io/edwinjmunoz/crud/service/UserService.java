package io.edwinjmunoz.crud.service;

import io.edwinjmunoz.crud.model.entities.User;
import io.edwinjmunoz.crud.model.request.CreateUserRequest;
import io.edwinjmunoz.crud.model.response.CreateUserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {

    CreateUserResponse createUser(CreateUserRequest request);

    Optional<User> findUserById(String userId);

    List<User> getAllUsers();
}
