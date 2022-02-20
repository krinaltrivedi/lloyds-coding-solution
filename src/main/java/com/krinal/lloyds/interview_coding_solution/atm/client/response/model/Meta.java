package com.krinal.lloyds.interview_coding_solution.atm.client.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Meta {
    @NotNull(message = "LastUpdated must not be null")
    @JsonProperty("LastUpdated")
    private Date lastUpdated;

    @NotNull(message = "TotalResults must not be null")
    @JsonProperty("TotalResults")
    private Integer totalResults;

    @NotNull(message = "Agreement must not be null")
    @NotBlank(message = "Agreement must have atleast one character")
    @JsonProperty("Agreement")
    private String agreement;

    @NotNull(message = "License must not be null")
    @NotBlank(message = "License must have atleast one character")
    @JsonProperty("License")
    private String license;

    @NotNull(message = "TermsOfUse must not be null")
    @NotBlank(message = "TermsOfUse must have atleast one character")
    @JsonProperty("TermsOfUse")
    private String termsOfUse;

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getTermsOfUse() {
        return termsOfUse;
    }

    public void setTermsOfUse(String termsOfUse) {
        this.termsOfUse = termsOfUse;
    }
}
