package io.edwinjmunoz.crud.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.edwinjmunoz.crud.model.PhoneNumber;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("token")
    private String token;

    @JsonProperty("isActive")
    private boolean isActive;

    @JsonProperty("created")
    private Date createdAt;

    @JsonProperty("modified")
    private Date modifiedAt;

    @JsonProperty("lastLogin")
    private Date lastLoginAt;

    @JsonProperty("phones")
    List<PhoneNumber> phones;

}
