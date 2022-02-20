package com.krinal.lloyds.interview_coding_solution.atm.api.response;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AtmResponse {
    @NotNull
    @NotBlank
    private String identification;
    private List<AtmDetail> atms;

    public AtmResponse(String identification, List<AtmDetail> atms) {
        this.identification = identification;
        this.atms = atms;
    }
}
