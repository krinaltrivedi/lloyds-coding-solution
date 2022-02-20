package com.krinal.lloyds.interview_coding_solution.atm.client.response.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@lombok.Data
public class OpenBankingAtmResponse {
    @NotNull
    @Valid
    private Meta meta;

    @NotNull
    @Valid
    private List<Data> data;
}
