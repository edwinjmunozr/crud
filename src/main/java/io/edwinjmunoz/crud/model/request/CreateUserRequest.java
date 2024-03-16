package io.edwinjmunoz.crud.model.request;

import io.edwinjmunoz.crud.model.PhoneNumber;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserRequest {

    @NotNull
    @NotEmpty(message = "The full name is required.")
    private String name;

    @NotEmpty(message = "The email is required.")
    @NotNull
    @Email
    private String email;

    @NotEmpty(message = "The password is required.")
    @NotNull(message = "Password must be between 4 to 15 characters")
    private String password;

    @NotEmpty(message = "The phone is required.")
    @NotNull
    @Size(min = 1, message = "Minimum 1 phone")
    @Valid
    private List<PhoneNumber> phones;

}
