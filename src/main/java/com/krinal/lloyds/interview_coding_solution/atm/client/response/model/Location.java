package com.krinal.lloyds.interview_coding_solution.atm.client.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class Location {
    @NotNull(message = "PostalAddress must not be null")
    @Valid
    @JsonProperty("PostalAddress")
    private PostalAddress postalAddress;
}
