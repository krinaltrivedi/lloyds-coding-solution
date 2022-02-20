package com.krinal.lloyds.interview_coding_solution.configuration.properties;

public class LloydsProperties {
    private ApisProperties apisProperties;
    private String identification;

    public ApisProperties getApis() {
        return apisProperties;
    }

    public void setApis(ApisProperties apisProperties) {
        this.apisProperties = apisProperties;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }
}
