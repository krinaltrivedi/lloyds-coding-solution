package com.krinal.lloyds.interview_coding_solution.atm.api.response;

import lombok.Data;

@Data
public class ErrorInfo {
    private String errorMessage;

    public ErrorInfo() {}

    public ErrorInfo(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
