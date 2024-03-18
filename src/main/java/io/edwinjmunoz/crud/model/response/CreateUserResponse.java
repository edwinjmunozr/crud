package io.edwinjmunoz.crud.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.edwinjmunoz.crud.model.request.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("phones")
    private List<PhoneNumber> phones;

    @JsonProperty("created")
    private Date created;

    @JsonProperty("modified")
    private Date modified;

    @JsonProperty("lastLogin")
    private Date lastLogin;

    @JsonProperty("token")
    private String token;

    @JsonProperty("isActive")
    private Boolean isActive;

}
