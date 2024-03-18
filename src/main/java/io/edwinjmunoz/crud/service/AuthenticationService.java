package io.edwinjmunoz.crud.service;

import io.edwinjmunoz.crud.model.request.LoginRequest;
import io.edwinjmunoz.crud.model.response.LoginResponse;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationService {

    LoginResponse signin(LoginRequest request) throws AuthenticationException;

}
