package com.krinal.lloyds.interview_coding_solution.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.krinal.lloyds.interview_coding_solution.atm.api.response.AtmResponse;
import com.krinal.lloyds.interview_coding_solution.atm.api.response.ErrorInfo;
import com.krinal.lloyds.interview_coding_solution.configuration.properties.ApiDetailProperties;
import com.krinal.lloyds.interview_coding_solution.configuration.properties.LloydsProperties;
import com.krinal.lloyds.interview_coding_solution.test_support.WireMockInitializer;
import com.krinal.lloyds.interview_coding_solution.test_support.api_facade.OpenBankingAtmErrorResponseFacade;
import com.krinal.lloyds.interview_coding_solution.test_support.api_facade.OpenBankingAtmResponseFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.Objects;
import java.util.stream.Stream;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = { WireMockInitializer.class })
@ActiveProfiles("test")
public class AllAtmsIntegrationTest {
    @Autowired
    private LloydsProperties lloydsProperties;
    @Autowired
    private ApiDetailProperties allAtmsApiProperties;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private WireMockServer wireMockServer;

    @LocalServerPort
    private int port;

    private OpenBankingAtmResponseFacade openBankingAtmResponseFacade;
    private OpenBankingAtmErrorResponseFacade openBankingAtmErrorResponseFacade;

    @BeforeEach
    public void setupTests() {
        openBankingAtmResponseFacade = new OpenBankingAtmResponseFacade();
        openBankingAtmErrorResponseFacade = new OpenBankingAtmErrorResponseFacade();
    }
    @Test
    public void getALlAtms_shouldReturnAtmsInLloydsBankingGroup_200_OK() {
        //Given
        final var alLAtmsUrl = getAllAtmsUrl();
        final var response = openBankingAtmResponseFacade.readFileWithAllFieldsAsString();
        stubForAllLloydsAtms(HttpStatus.OK.value(), response);

        //When
        final var atmResponseResponseEntity = testRestTemplate.getForEntity(alLAtmsUrl, AtmResponse.class);
        final var atmResponseBody = Objects.requireNonNull(atmResponseResponseEntity.getBody());

        //Then
        assertThat(atmResponseResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(atmResponseBody.getIdentification()).isEqualTo(lloydsProperties.getIdentification());
        assertThat(atmResponseBody.getAtms().size()).isGreaterThan(0);
    }

    @Test
    public void getALlAtms_shouldReturnErrorWhenResourceIsInvalid_404_Not_Found() {
        //Given
        final var alLAtmsUrl = getAllAtmsUrl().concat("aaa");

        //When
        final var atmResponseResponseEntity = testRestTemplate.getForEntity(alLAtmsUrl, AtmResponse.class);

        //Then
        assertThat(atmResponseResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @ParameterizedTest
    @ArgumentsSource(value = HttpStatusCodeArgumentsProvider.class)
    public void getALlAtms_shouldReturnErrorWhenAtmApiReturns400BadRequest_500_Internal_Server_Error(int httpStatusCode) {
        //Given
        final var alLAtmsUrl = getAllAtmsUrl();
        final var errorString = openBankingAtmErrorResponseFacade.readFileWithAllErrorFieldsAsString();
        stubForAllLloydsAtms(httpStatusCode, errorString);

        //When
        final var atmResponseResponseEntity = testRestTemplate.getForEntity(alLAtmsUrl, ErrorInfo.class);
        final var atmResponseBody = Objects.requireNonNull(atmResponseResponseEntity.getBody());

        //Then
        assertThat(atmResponseResponseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(atmResponseBody.getErrorMessage()).isNotNull();
    }

    @Test
    public void getALlAtms_shouldReturnErrorWhenResponseValidationFails_500_Internal_Server_Error() throws JsonProcessingException {
        //Given
        final var alLAtmsUrl = getAllAtmsUrl();
        final var openBankingAtmResponse = openBankingAtmResponseFacade.readFileWithMissingMetaFieldAsString();
        stubForAllLloydsAtms(HttpStatus.OK.value(), openBankingAtmResponse);

        //When
        final var atmResponseResponseEntity = testRestTemplate.getForEntity(alLAtmsUrl, ErrorInfo.class);
        final var atmResponseBody = Objects.requireNonNull(atmResponseResponseEntity.getBody());

        //Then
        assertThat(atmResponseResponseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(atmResponseBody.getErrorMessage()).isNotNull();
    }

    private String getAllAtmsUrl() {
        return String.format("http://localhost:%s/v1.0.0/atms", port);
    }

    private void stubForAllLloydsAtms(int httpStatusCode, String responseBody) {
        wireMockServer.stubFor(get(urlEqualTo("/all-atms"))
                .willReturn(aResponse()
                        .withHeader("Accept", "application/json")
                        .withHeader("Content-Type", "application/json")
                        .withStatus(httpStatusCode)
                        .withBody(responseBody)));
    }

    private static class HttpStatusCodeArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(HttpStatus.BAD_REQUEST.value()),
                    Arguments.of(HttpStatus.REQUEST_TIMEOUT.value()),
                    Arguments.of(HttpStatus.TOO_MANY_REQUESTS.value()),
                    Arguments.of(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    Arguments.of(HttpStatus.SERVICE_UNAVAILABLE.value())
            );
        }
    }
}
