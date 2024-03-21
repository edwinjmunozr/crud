package io.edwinjmunoz.crud.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.edwinjmunoz.crud.exceptions.InvalidRequestException;
import io.edwinjmunoz.crud.model.dto.UserDTO;
import io.edwinjmunoz.crud.model.entity.User;
import io.edwinjmunoz.crud.model.request.CreateUserRequest;
import io.edwinjmunoz.crud.model.response.CreateUserResponse;
import io.edwinjmunoz.crud.repository.UserRepository;
import io.edwinjmunoz.crud.util.JsonReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static io.edwinjmunoz.crud.CrudUtilTest.mockUser;


@SpringBootTest
public class UserServiceTest {

    Logger log = LoggerFactory.getLogger(UserServiceTest.class);

    @MockBean
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserService userService;

    private JsonReader jsonReader = new JsonReader();


    @Test
    void testCreateUserSuccess() {
        try {
            String jsonData = jsonReader.readJsonFile("/users/create_user_request.json");
            log.info("jsonData(create_user_request) = {}", jsonData);

            CreateUserRequest request = objectMapper.readValue(jsonData, CreateUserRequest.class);

            Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
            Mockito.when(userRepository.save(Mockito.any())).thenReturn(new User());

            CreateUserResponse createUserResponse = userService.createUser(request);

            Assertions.assertNotNull(createUserResponse);
            Assertions.assertNotNull(createUserResponse.getId());
            Assertions.assertNotNull(createUserResponse.getEmail());
            Assertions.assertEquals(createUserResponse.getEmail().toLowerCase(), request.getEmail().toLowerCase());
            Assertions.assertNotNull(createUserResponse.getToken());
            Assertions.assertTrue(createUserResponse.getIsActive());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            Assertions.fail();
        }
    }

    @Test
    public void testOnInvalidEmailThenShouldThrowsInvalidRequestException() {
        try {
            CreateUserRequest request = new CreateUserRequest();
            request.setName("Juan Camilo");
            request.setEmail("eab@com");
            request.setPassword("Camila121#");
            userService.createUser(request);
            Assertions.fail();
        } catch (InvalidRequestException ex) {
        }
    }

    @Test
    public void testOnInvalidPasswordThenShouldThrowsInvalidRequestException() {
        try {
            CreateUserRequest request = new CreateUserRequest();
            request.setName("Juan Camilo");
            request.setEmail("miemailname@yourdomain.com");
            request.setPassword("Juan21#");
            userService.createUser(request);
            Assertions.fail();
        } catch (InvalidRequestException ex) {
        }
    }

    @Test
    public void testFindUserByIdSuccess() {
        try {

            User userMock = mockUser();
            String id = userMock.getId();
            Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(userMock));

            Optional<UserDTO> searchDto = userService.findUserById(id);
            Assertions.assertNotNull(searchDto);
            Assertions.assertTrue(searchDto.isPresent());
            UserDTO dto = searchDto.get();
            Assertions.assertNotNull(dto.getId());
            Assertions.assertEquals(dto.getId(), id);
            Assertions.assertNotNull(dto.getName());
            Assertions.assertNotNull(dto.getEmail());
            Assertions.assertNotNull(dto.getCreatedAt());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            Assertions.fail();
        }
    }

    @Test
    public void testFindUserByIdReturnEmpty() {
        try {
            Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
            Optional<UserDTO> searchDto = userService.findUserById("abc");
            Assertions.assertNotNull(searchDto);
            Assertions.assertTrue(searchDto.isEmpty());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            Assertions.fail();
        }
    }

    @Test
    public void testGetAllUsers() {
        try {

            Mockito.when(userRepository.findAll()).thenReturn(List.of(mockUser()));
            List<UserDTO> lstDto = userService.getAllUsers();
            Assertions.assertNotNull(lstDto);
            Assertions.assertFalse(lstDto.isEmpty());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            Assertions.fail();
        }
    }

}
