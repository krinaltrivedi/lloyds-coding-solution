package com.krinal.lloyds.interview_coding_solution.atm.api.response;

public class ErrorInfo {
    private String errorMessage;

    public ErrorInfo() {}

    public ErrorInfo(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
