package io.edwinjmunoz.crud.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PhoneNumber {

    @JsonProperty("number")
    @NotEmpty(message = "The phone number is required.")
    @NotNull(message = "The phone number is required.")
    private String number;

    @JsonProperty("citycode")
    @NotEmpty(message = "The citycode is required.")
    @NotNull(message = "The citycode is required.")
    private String citycode;

    @JsonProperty("countrycode")
    @NotEmpty(message = "The countrycode is required.")
    @NotNull(message = "The countrycode is required.")
    private String countrycode;

}
