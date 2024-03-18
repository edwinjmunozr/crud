package io.edwinjmunoz.crud.service;

import io.edwinjmunoz.crud.model.request.LoginRequest;
import io.edwinjmunoz.crud.model.response.LoginResponse;
import io.edwinjmunoz.crud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;


    @Override
    public LoginResponse signin(LoginRequest request) throws AuthenticationException {
        try {
            String email = request.getEmail().trim().toLowerCase();
            String password = request.getPassword();

            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

            UserDetails user = (UserDetails) authentication.getPrincipal();
            String username = authentication.getName();
            String jwt = jwtService.generateToken(user);
            userRepository.updateLastLoginByEmail(jwt, username);
            return new LoginResponse(username, jwt);
        } catch (Exception ex) {
            log.error("Authentication error", ex);
            throw ex;
        }
    }

}
