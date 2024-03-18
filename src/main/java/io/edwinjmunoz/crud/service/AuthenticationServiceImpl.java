package io.edwinjmunoz.crud.service;

import io.edwinjmunoz.crud.model.request.LoginRequest;
import io.edwinjmunoz.crud.model.response.LoginResponse;
import io.edwinjmunoz.crud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;


    @Override
    public LoginResponse signin(LoginRequest request) throws AuthenticationException {

        String email = request.getEmail().trim().toLowerCase();
        String password = request.getPassword();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String username = authentication.getName();
        String jwt = jwtService.generateToken(user);
        userRepository.updateLastLoginByEmail(jwt, username);
        return new LoginResponse(username, jwt);
    }

}
