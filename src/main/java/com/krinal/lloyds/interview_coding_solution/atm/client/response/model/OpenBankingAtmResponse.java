package com.krinal.lloyds.interview_coding_solution.atm.client.response.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class OpenBankingAtmResponse {
    @NotNull
    @Valid
    private Meta meta;

    @NotNull
    @Valid
    private List<Data> data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
