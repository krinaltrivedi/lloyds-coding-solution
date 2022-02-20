package com.krinal.lloyds.interview_coding_solution.atm.client.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class Data {
    @NotNull(message = "Brand must not be null")
    @Size(min = 1, message = "Brand list must have atleast one element")
    @Valid
    @JsonProperty("Brand")
    private List<Brand> brand;

    public List<Brand> getBrand() {
        return brand;
    }

    public void setBrand(List<Brand> brand) {
        this.brand = brand;
    }
}
