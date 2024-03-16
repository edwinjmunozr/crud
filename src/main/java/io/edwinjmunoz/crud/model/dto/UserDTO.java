package io.edwinjmunoz.crud.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private String id;
    private String name;
    private String email;
    private String password;
    private Date createdAt;
    private Date modificatedAt;
    private Date lastLoginAt;
    private boolean isActive;

}
