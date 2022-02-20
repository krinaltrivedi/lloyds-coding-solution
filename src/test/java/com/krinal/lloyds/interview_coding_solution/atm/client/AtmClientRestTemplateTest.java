package com.krinal.lloyds.interview_coding_solution.atm.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.krinal.lloyds.interview_coding_solution.atm.client.response.model.OpenBankingAtmResponse;
import com.krinal.lloyds.interview_coding_solution.atm.exception.DownstreamApiException;
import com.krinal.lloyds.interview_coding_solution.configuration.properties.ApiDetailProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AtmClientRestTemplateTest {
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ApiDetailProperties allAtmsApiProperties;

    @Mock
    private ObjectMapper objectMapper;

    private AtmClientRestTemplate atmClientRestTemplate;

    @BeforeEach
    public void setupTests() {
        when(allAtmsApiProperties.getUrl()).thenReturn("http://some-fake-atm-url.com");
        atmClientRestTemplate = new AtmClientRestTemplate(restTemplate, allAtmsApiProperties, objectMapper);
    }

    @Test
    public void getAllAtms_shouldReturnResponseBodyIfHttpStatusIsOK() throws JsonProcessingException {
        //Given
        final var expectedResponse = "Hello";
        final var getAllAtmsResponseEntity = new ResponseEntity<>(expectedResponse, HttpStatus.OK);
        when(restTemplate.getForEntity(allAtmsApiProperties.getUrl(), String.class))
                .thenReturn(getAllAtmsResponseEntity);
        when(objectMapper.readValue(getAllAtmsResponseEntity.getBody(), OpenBankingAtmResponse.class))
                .thenReturn(new OpenBankingAtmResponse());

        //When
        final var actualOpenBankingAtmResponse = atmClientRestTemplate.getAllAtms();

        //Then
        assertThat(actualOpenBankingAtmResponse).isNotNull();
    }

    @ParameterizedTest
    @ArgumentsSource(HttpStatusArgumentsProvider.class)
    public void getAllAtms_shouldReturnResponseBodyIfHttpStatusIsClientOrServerError(HttpStatus httpStatus) {
        //Given
        final var clientErrorMessage = "Some error message";
        final var clientErrorException = new HttpClientErrorException(httpStatus, clientErrorMessage);
        when(restTemplate.getForEntity(allAtmsApiProperties.getUrl(), String.class))
                .thenThrow(clientErrorException);

        //When, Then
        assertThatExceptionOfType(DownstreamApiException.class)
                .isThrownBy(() -> atmClientRestTemplate.getAllAtms())
                .withMessageContaining(clientErrorMessage);
    }

    private static class HttpStatusArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(HttpStatus.BAD_REQUEST),
                    Arguments.of(HttpStatus.REQUEST_TIMEOUT),
                    Arguments.of(HttpStatus.TOO_MANY_REQUESTS),
                    Arguments.of(HttpStatus.INTERNAL_SERVER_ERROR),
                    Arguments.of(HttpStatus.SERVICE_UNAVAILABLE)
            );
        }
    }
}
