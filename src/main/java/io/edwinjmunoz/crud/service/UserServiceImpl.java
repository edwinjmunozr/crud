package io.edwinjmunoz.crud.service;

import io.edwinjmunoz.crud.model.entities.User;
import io.edwinjmunoz.crud.model.request.CreateUserRequest;
import io.edwinjmunoz.crud.model.response.CreateUserResponse;
import io.edwinjmunoz.crud.util.UUIDUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {

        Date now = new Date();
        CreateUserResponse response = new CreateUserResponse();
        response.setCreated(now);
        response.setModified(now);
        response.setLastLogin(now);
        return response;
    }

    @Override
    public Optional<User> findUserById(String userId) {

        if (userId.equalsIgnoreCase("edwin")) {
            User user = new User();
            user.setId(userId);
            user.setEmail("email@dominio.com");
            user.setCreatedAt(new Date());
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        for (int cont = 1; cont <= 20; cont++) {
            User user = new User();
            user.setId(UUIDUtil.newUUID());
            user.setEmail(cont + "email@dominio.com");
            user.setCreatedAt(new Date());
            result.add(user);
        }
        return result;
    }

}
