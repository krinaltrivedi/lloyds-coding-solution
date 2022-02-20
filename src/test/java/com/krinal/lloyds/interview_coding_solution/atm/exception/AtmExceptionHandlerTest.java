package com.krinal.lloyds.interview_coding_solution.atm.exception;

import com.krinal.lloyds.interview_coding_solution.atm.client.response.validator.InvalidResponseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class AtmExceptionHandlerTest {
    private AtmExceptionHandler atmExceptionHandler;

    @BeforeEach
    public void setupTests() {
        atmExceptionHandler = new AtmExceptionHandler();
    }

    @Test
    public void handleDownstreamApiException_shouldThrow500InternalServerError() {
        //Given
        final var originalException = new Exception();
        final var errorMessage = "Some error message";

        //When
        final var errorResponseEntity =
                atmExceptionHandler.handleDownstreamApiException(new DownstreamApiException(errorMessage, originalException));
        final var errorInfoBody = errorResponseEntity.getBody();

        //Then
        assertThat(errorResponseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(errorInfoBody).isNotNull();
        assertThat(errorInfoBody.getErrorMessage()).isEqualTo(errorMessage);
    }

    @Test
    public void handleInvalidResponseException_shouldThrow500InternalServerError() {
        //Given
        final var errorMessage = "Some error message";

        //When
        final var errorResponseEntity =
                atmExceptionHandler.handleInvalidResponseException(new InvalidResponseException(errorMessage));
        final var errorInfoBody = errorResponseEntity.getBody();

        //Then
        assertThat(errorResponseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(errorInfoBody).isNotNull();
        assertThat(errorInfoBody.getErrorMessage()).isEqualTo(errorMessage);
    }
}
