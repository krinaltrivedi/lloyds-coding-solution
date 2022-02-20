package com.krinal.lloyds.interview_coding_solution.atm.exception;

import com.krinal.lloyds.interview_coding_solution.atm.api.response.ErrorInfo;
import com.krinal.lloyds.interview_coding_solution.atm.client.response.validator.InvalidResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AtmExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { DownstreamApiException.class })
    public ResponseEntity<ErrorInfo> handleDownstreamApiException(DownstreamApiException e) {
        return createErrorInfoResponseEntity(e.getMessage());
    }

    @ExceptionHandler(value = { InvalidResponseException.class })
    public ResponseEntity<ErrorInfo> handleInvalidResponseException(InvalidResponseException e) {
        return createErrorInfoResponseEntity(e.getMessage());
    }

    private ResponseEntity<ErrorInfo> createErrorInfoResponseEntity(final String message) {
        final var errorInfo = new ErrorInfo(message);
        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
