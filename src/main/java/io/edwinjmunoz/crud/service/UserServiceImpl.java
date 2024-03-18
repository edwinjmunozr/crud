package io.edwinjmunoz.crud.service;

import io.edwinjmunoz.crud.exceptions.ErrorMessage;
import io.edwinjmunoz.crud.exceptions.InvalidRequestException;
import io.edwinjmunoz.crud.model.PhoneNumber;
import io.edwinjmunoz.crud.model.Role;
import io.edwinjmunoz.crud.model.dto.UserDTO;
import io.edwinjmunoz.crud.model.entity.User;
import io.edwinjmunoz.crud.model.entity.UserPhone;
import io.edwinjmunoz.crud.model.request.CreateUserRequest;
import io.edwinjmunoz.crud.model.response.CreateUserResponse;
import io.edwinjmunoz.crud.repository.UserRepository;
import io.edwinjmunoz.crud.util.PatternValidator;
import io.edwinjmunoz.crud.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Value("${pattern.validation.email}")
    private String emailPattern;

    @Value("${pattern.validation.password}")
    private String passwordPattern;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public CreateUserResponse createUser(CreateUserRequest request) throws InvalidRequestException {

        try {
            checkCreateUserRequest(request);

            Optional<User> userSearch = userRepository.findByEmail(request.getEmail());
            if (userSearch.isPresent()) {
                throw new InvalidRequestException(ErrorMessage.EMAIL_ALREADY_EXIST);
            }

            User user = convertRequestToUser(request);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setToken(jwtService.generateToken(user));
            user.setRole(Role.USER);
            userRepository.save(user);

            return CreateUserResponse.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .isActive(user.isActive())
                    .token(user.getToken())
                    .created(user.getCreatedAt())
                    .modified(user.getModifiedAt())
                    .lastLogin(user.getLastLoginAt())
                    .build();

        } catch (InvalidRequestException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new InvalidRequestException(ex.getMessage());
        }
    }

    @Override
    public Optional<UserDTO> findUserById(String id) {

        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            return Optional.of(converUserToUserDto(result.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> findUserByEmail(String email) {
        Optional<User> result = userRepository.findByEmail(email);
        if (result.isPresent()) {
            return Optional.of(converUserToUserDto(result.get()));
        }
        return Optional.empty();
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> result = userRepository.findAll();
        return result.stream().map(this::converUserToUserDto).collect(Collectors.toList());
    }


    private void checkCreateUserRequest(CreateUserRequest request) throws InvalidRequestException {

        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new InvalidRequestException(ErrorMessage.NAME_ERROR);
        }
        if (!PatternValidator.validateEmail(emailPattern, request.getEmail())) {
            throw new InvalidRequestException(ErrorMessage.EMAIL_FORMAT_ERROR);
        }
        if (!PatternValidator.validatePassword(passwordPattern.trim(), request.getPassword())) {
            throw new InvalidRequestException(ErrorMessage.PASSWORD_FORMAT_ERROR);
        }
        if (request.getPhones() == null || request.getPhones().isEmpty()) {
            throw new InvalidRequestException(ErrorMessage.PHONE_ERROR);
        }
    }

    private UserDTO converUserToUserDto(User user) {
        UserDTO dto = new UserDTO();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setToken(user.getToken());
        dto.setActive(user.isActive());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setModifiedAt(user.getModifiedAt());
        dto.setLastLoginAt(user.getLastLoginAt());

        List<PhoneNumber> phones = user.getPhones().stream().map(a ->
                        new PhoneNumber(a.getPhoneNumber(), a.getCityCode(), a.getCountryCode()))
                .toList();
        dto.setPhones(phones);
        return dto;
    }

    private User convertRequestToUser(CreateUserRequest request) {

        String userId = UUIDUtil.newUUID();
        Date now = new Date();

        User user = new User();
        user.setId(userId);
        user.setName(request.getName());
        user.setEmail(request.getEmail().toLowerCase());
        user.setPassword(request.getPassword());
        user.setToken("token");
        user.setActive(true);
        user.setCreatedAt(now);
        user.setModifiedAt(now);
        user.setLastLoginAt(now);

        List<UserPhone> phones = request.getPhones().stream()
                .map(phoneNumber -> convertToUserPhone(phoneNumber, user))
                .toList();
        user.setPhones(phones);
        return user;
    }

    private UserPhone convertToUserPhone(PhoneNumber phoneNumber, User user) {
        UserPhone userPhone = new UserPhone();
        userPhone.setId(UUIDUtil.newUUID());
        userPhone.setUserId(user);
        userPhone.setPhoneNumber(phoneNumber.getNumber());
        userPhone.setCityCode(phoneNumber.getCitycode());
        userPhone.setCountryCode(phoneNumber.getCountrycode());
        return userPhone;
    }

}
