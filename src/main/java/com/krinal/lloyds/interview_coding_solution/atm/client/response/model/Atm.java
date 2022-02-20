package com.krinal.lloyds.interview_coding_solution.atm.client.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Atm {
    @NotNull(message = "Identification must not be null")
    @Size(min = 1, message = "Identification must have atleast 1 character")
    @Size(max = 35, message = "Identification must not exceed 35 characters")
    @JsonProperty("Identification")
    private String identification;

    @NotNull(message = "SupportedCurrencies must not be null")
    @Size(min = 1, message = "SupportedCurrencies must have atleast 1 element")
    @JsonProperty("SupportedCurrencies")
    private List<@Pattern(regexp = "[A-Z]{3}", message = "Currency should be in ISO 4217 format") String> supportedCurrencies;

    @NotNull(message = "Location must not be null")
    @Valid
    @JsonProperty("Location")
    private Location location;
}
