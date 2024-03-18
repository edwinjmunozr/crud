package io.edwinjmunoz.crud.controller;


import io.edwinjmunoz.crud.exceptions.ErrorMessage;
import io.edwinjmunoz.crud.exceptions.InvalidRequestException;
import io.edwinjmunoz.crud.model.request.LoginRequest;
import io.edwinjmunoz.crud.model.response.LoginResponse;
import io.edwinjmunoz.crud.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "auth", description = "Security management APIs")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationService authenticationService;

    @Operation(summary = "Login user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user logged", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Users not found", content = @Content)})
    @PostMapping(value = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody(required = true) LoginRequest loginRequest) {

        if (loginRequest == null || loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
            throw new InvalidRequestException(ErrorMessage.INVALID_USERNAME_PASSWORD);
        }

        String email = loginRequest.getEmail().trim().toLowerCase();
        if (!isValidEmail(email)) {
            throw new InvalidRequestException(ErrorMessage.EMAIL_FORMAT_ERROR);
        }

        LoginResponse loginResponse = authenticationService.signin(loginRequest);
        if (loginResponse == null) {
            throw new BadCredentialsException("");
        }
        return ResponseEntity.ok(loginResponse);
    }

    private boolean isValidEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

}
