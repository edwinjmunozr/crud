package io.edwinjmunoz.crud.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PhoneNumber {

    @JsonProperty("countryCode")
    @NotEmpty(message = "The countryCode is required.")
    @NotNull(message = "The countryCode is required.")
    private String countryCode;

    @JsonProperty("cityCode")
    @NotEmpty(message = "The cityCode is required.")
    @NotNull(message = "The cityCode is required.")
    private String cityCode;

    @JsonProperty("number")
    @NotEmpty(message = "The phone number is required.")
    @NotNull(message = "The phone number is required.")
    private String number;

}
