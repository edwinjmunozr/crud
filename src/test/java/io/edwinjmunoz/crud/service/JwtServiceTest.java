package io.edwinjmunoz.crud.service;

import io.edwinjmunoz.crud.model.entity.User;
import io.edwinjmunoz.crud.util.UUIDUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtServiceTest {


    @Autowired
    JwtService jwtService;

    @Test
    void testOkGenerateToken() {
        try {
            String email = "jhonsmith@yourdomain.com";
            User mockUser = new User();
            mockUser.setId(UUIDUtil.newUUID());
            mockUser.setName("Jhon Smith");
            mockUser.setEmail(email);
            mockUser.setPassword("myPassword123#");

            String token = jwtService.generateToken(mockUser);
            Assertions.assertNotNull(token);
            Assertions.assertTrue(jwtService.isTokenValid(token, mockUser));
            Assertions.assertFalse(jwtService.isTokenExpired(token));
            Assertions.assertEquals(jwtService.extractUserName(token), email);

        } catch (Exception ex) {
            Assertions.fail();
        }
    }

    @Test
    void testInvalidToken() {
        try {
            String email = "jhonsmith@yourdomain.com";
            User mockUser = new User();
            mockUser.setId(UUIDUtil.newUUID());
            mockUser.setName("Jhon Smith");
            mockUser.setEmail(email);
            mockUser.setPassword("myPassword123#");

            String token = jwtService.generateToken(mockUser);
            Assertions.assertNotNull(token);
            mockUser.setEmail("jhonsmith1@yourdomain.com");
            Assertions.assertFalse(jwtService.isTokenValid(token, mockUser));
        } catch (Exception ex) {
            Assertions.fail();
        }
    }

}
