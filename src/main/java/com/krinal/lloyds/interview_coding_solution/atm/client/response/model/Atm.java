package com.krinal.lloyds.interview_coding_solution.atm.client.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

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

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public List<String> getSupportedCurrencies() {
        return supportedCurrencies;
    }

    public void setSupportedCurrencies(List<String> supportedCurrencies) {
        this.supportedCurrencies = supportedCurrencies;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
