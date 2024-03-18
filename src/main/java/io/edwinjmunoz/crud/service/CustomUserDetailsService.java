package io.edwinjmunoz.crud.service;

import io.edwinjmunoz.crud.exceptions.ErrorMessage;
import io.edwinjmunoz.crud.model.entity.User;
import io.edwinjmunoz.crud.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findByEmail(email.toLowerCase());
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(ErrorMessage.INVALID_USERNAME_PASSWORD);
        }
        User realUser = userOptional.get();

        List<String> roles = new ArrayList<>();
        roles.add("USER");
        return org.springframework.security.core.userdetails.User.builder()
                .username(realUser.getEmail())
                .password(realUser.getPassword())
                .roles(roles.toArray(new String[0]))
                .build();
    }

}
