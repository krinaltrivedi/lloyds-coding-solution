package com.krinal.lloyds.interview_coding_solution.atm.client.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

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

    public List<String> getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(List<String> addressLine) {
        this.addressLine = addressLine;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public List<String> getCountrySubDivision() {
        return countrySubDivision;
    }

    public void setCountrySubDivision(List<String> countrySubDivision) {
        this.countrySubDivision = countrySubDivision;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
