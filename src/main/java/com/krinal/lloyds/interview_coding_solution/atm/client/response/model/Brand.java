package com.krinal.lloyds.interview_coding_solution.atm.client.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class Brand {
    @NotNull(message = "BrandName must not be null")
    @Size(min = 1, message = "BrandName must not be less than 1 character")
    @Size(max = 140, message = "BrandName must not exceed 140 characters")
    @JsonProperty("BrandName")
    private String brandName;

    @NotNull
    @Size(min = 1, message = "ATM list must have atleast one element")
    @Valid
    @JsonProperty("ATM")
    private List<Atm> atm;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<Atm> getAtm() {
        return atm;
    }

    public void setAtm(List<Atm> atm) {
        this.atm = atm;
    }
}
