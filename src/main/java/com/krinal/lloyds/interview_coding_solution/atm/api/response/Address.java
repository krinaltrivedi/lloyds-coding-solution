package com.krinal.lloyds.interview_coding_solution.atm.api.response;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Address {
    @NotNull
    @NotBlank
    private String addressLine1;
    private String addressLine2;
    private String town;
    private String city;
    private String county;
    @NotNull
    @NotBlank
    private String postcode;
    @NotNull
    @NotBlank
    private String country;
}
