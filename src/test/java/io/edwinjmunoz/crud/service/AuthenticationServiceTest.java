package io.edwinjmunoz.crud.service;

import io.edwinjmunoz.crud.CrudUtilTest;
import io.edwinjmunoz.crud.model.entity.User;
import io.edwinjmunoz.crud.model.request.LoginRequest;
import io.edwinjmunoz.crud.model.response.LoginResponse;
import io.edwinjmunoz.crud.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class AuthenticationServiceTest {

    Logger log = LoggerFactory.getLogger(AuthenticationServiceTest.class);

    @MockBean
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Test
    void testLoginOnSuccess() {
        try {
            User mockUser = CrudUtilTest.mockUser();
            mockUser.setPassword("$2a$10$k68S1.LYOHD8fG38UlfCKO51pvpC2yt/QIH8hxUHE7iVfTudxAqB6");

            Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(mockUser));

            LoginRequest request = LoginRequest.builder()
                    .email(mockUser.getEmail())
                    .password("Colombia123#")
                    .build();

            LoginResponse response = authenticationService.signin(request);
            Assertions.assertNotNull(response);
            Assertions.assertNotNull(response.getEmail());
            Assertions.assertNotNull(response.getToken());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            Assertions.fail();
        }
    }


    @Test
    void testLoginOnError() {
        try {
            User mockUser = CrudUtilTest.mockUser();
            Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(mockUser));

            LoginRequest request = LoginRequest.builder()
                    .email(mockUser.getEmail())
                    .password("Colombia123#")
                    .build();

            authenticationService.signin(request);
            Assertions.fail();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

}
