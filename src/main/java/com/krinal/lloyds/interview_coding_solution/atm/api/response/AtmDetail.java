package com.krinal.lloyds.interview_coding_solution.atm.api.response;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class AtmDetail {
    @NotNull
    @NotBlank
    private String brandName;

    @NotNull
    @NotBlank
    private String identification;

    @NotNull
    @Size(min = 1)
    private List<String> supportedCurrencies;

    @NotNull
    @Valid
    private Address address;
}
