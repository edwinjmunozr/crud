package io.edwinjmunoz.crud.repository;

import io.edwinjmunoz.crud.model.entity.User;
import io.edwinjmunoz.crud.model.entity.UserPhone;
import io.edwinjmunoz.crud.util.UUIDUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserPhoneRepository userPhoneRepository;

    private User user;

    @BeforeEach
    public void setupTestData() {
        user = mockUser();
    }


    @Test
    void testSaveAndFind() {

        Assertions.assertNotNull(user);
        User newUser = userRepository.save(user);
        Assertions.assertNotNull(newUser);

        Optional<User> searchUser = userRepository.findById(newUser.getId());
        Assertions.assertNotNull(searchUser);
        Assertions.assertTrue(searchUser.isPresent());
        Assertions.assertEquals(user.getId(), newUser.getId());
        Assertions.assertEquals(user.getId(), searchUser.get().getId());

        Optional<User> user2 = userRepository.findByEmail(user.getEmail());
        Assertions.assertNotNull(user2);
        Assertions.assertTrue(user2.isPresent());
        Assertions.assertEquals(user2.get().getEmail(), user.getEmail());
    }

    public User mockUser() {
        String id = UUIDUtil.newUUID();
        Date now = new Date();
        User userMock = new User();
        userMock.setId(id);
        userMock.setName("Pedro Javier");
        userMock.setEmail("pedrojavier@tudomain.com");
        userMock.setPassword("Colombia123#");
        userMock.setCreatedAt(now);
        userMock.setModifiedAt(now);
        userMock.setLastLoginAt(now);
        userMock.setActive(true);
        userMock.setToken("eyJhbGciOiJIU_ykX0Ut2YCTGs61U");
        userMock.setPhones(Arrays.asList(mockUserPhone(userMock), mockUserPhone(userMock)));
        return userMock;
    }

    public UserPhone mockUserPhone(User user) {
        UserPhone userPhone = new UserPhone();
        userPhone.setId(UUIDUtil.newUUID());
        userPhone.setUserId(user);
        userPhone.setPhoneNumber("123012548");
        userPhone.setCityCode("76001");
        userPhone.setCountryCode("57");
        return userPhone;
    }

}
