package io.edwinjmunoz.crud.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.edwinjmunoz.crud.model.PhoneNumber;
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
    //@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",flags = Pattern.Flag.CASE_INSENSITIVE)
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
