package io.edwinjmunoz.crud.service;

import io.edwinjmunoz.crud.model.dto.UserDTO;
import io.edwinjmunoz.crud.model.request.CreateUserRequest;
import io.edwinjmunoz.crud.model.response.CreateUserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {

    CreateUserResponse createUser(CreateUserRequest request);

    Optional<UserDTO> findUserById(String userId);

    Optional<UserDTO> findUserByEmail(String email);

    List<UserDTO> getAllUsers();

}
