package com.krinal.lloyds.interview_coding_solution.configuration.properties;

public class OpenBankingProperties {
    private ApiDetailProperties getAllAtms;

    public ApiDetailProperties getGetAllAtms() {
        return getAllAtms;
    }

    public void setGetAllAtms(ApiDetailProperties getAllAtms) {
        this.getAllAtms = getAllAtms;
    }
}

