package com.krinal.lloyds.interview_coding_solution.atm.client.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PostalAddress {
    @JsonProperty("AddressLine")
    private List<String> addressLine;

    @JsonProperty("StreetName")
    private String streetName;

    @JsonProperty("TownName")
    private String townName;

    @JsonProperty("CountrySubDivision")
    private List<String> countrySubDivision;

    @JsonProperty("Country")
    private String country;

    @JsonProperty("PostCode")
    private String postcode;
}
