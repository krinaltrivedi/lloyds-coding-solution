package com.krinal.lloyds.interview_coding_solution.atm.exception;

public class DownstreamApiException extends RuntimeException {
    public DownstreamApiException(final String message, Exception e) {
        super(message, e);
    }
}
