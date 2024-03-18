package io.edwinjmunoz.crud.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest implements Serializable {

    @NotNull
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String password;

}
