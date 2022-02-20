package com.krinal.lloyds.interview_coding_solution.atm.client.response.validator;

public class InvalidResponseException extends RuntimeException {
    public InvalidResponseException(String message) {
        super(message);
    }
}
