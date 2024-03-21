package io.edwinjmunoz.crud;

import io.edwinjmunoz.crud.model.entity.User;
import io.edwinjmunoz.crud.model.entity.UserPhone;
import io.edwinjmunoz.crud.util.UUIDUtil;

import java.util.Arrays;
import java.util.Date;

public final class CrudUtilTest {

    private CrudUtilTest() {
    }

    public static User mockUser() {
        String id = UUIDUtil.newUUID();
        Date now = new Date();
        User userMock = new User();
        userMock.setId(id);
        userMock.setName("Pedro Javier");
        userMock.setEmail("admin@youdomain.com");
        userMock.setPassword("Colombia123#");
        userMock.setCreatedAt(now);
        userMock.setModifiedAt(now);
        userMock.setLastLoginAt(now);
        userMock.setActive(true);
        userMock.setToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB5b3Vkb21haW4uY29tIiwiaWF0IjoxNzEwOTQzOTk2LCJleHAiOjE3MTA5NDU0MzZ9.azRrkDEPM7t-9uhkBLmLT4BGHFdYfI-Hs9JOoZGVeAQ");
        userMock.setPhones(Arrays.asList(mockUserPhone(userMock), mockUserPhone(userMock)));
        return userMock;
    }


    public static UserPhone mockUserPhone(User user) {
        UserPhone userPhone = new UserPhone();
        userPhone.setId(UUIDUtil.newUUID());
        userPhone.setUserId(user);
        userPhone.setPhoneNumber("123012548");
        userPhone.setCityCode("76001");
        userPhone.setCountryCode("57");
        return userPhone;
    }

}
