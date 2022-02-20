package com.krinal.lloyds.interview_coding_solution.atm.client.response.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@lombok.Data
public class Data {
    @NotNull(message = "Brand must not be null")
    @Size(min = 1, message = "Brand list must have atleast one element")
    @Valid
    @JsonProperty("Brand")
    private List<Brand> brand;
}
