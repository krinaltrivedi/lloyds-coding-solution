package com.krinal.lloyds.interview_coding_solution.atm.api.response;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class AtmResponse {
    @NotNull
    @NotBlank
    private String identification;
    private List<AtmDetail> atms;

    public AtmResponse(String identification, List<AtmDetail> atms) {
        this.identification = identification;
        this.atms = atms;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public List<AtmDetail> getAtms() {
        return atms;
    }

    public void setAtms(List<AtmDetail> atms) {
        this.atms = atms;
    }
}
