package com.krinal.lloyds.interview_coding_solution.atm.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.krinal.lloyds.interview_coding_solution.atm.client.response.model.OpenBankingAtmResponse;
import com.krinal.lloyds.interview_coding_solution.atm.exception.DownstreamApiException;
import com.krinal.lloyds.interview_coding_solution.configuration.properties.ApiDetailProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class AtmClientRestTemplate implements AtmClient {
    private final RestTemplate restTemplate;
    private final ApiDetailProperties allAtmsApiProperties;
    private final ObjectMapper objectMapper;

    public AtmClientRestTemplate(final RestTemplate restTemplate,
                                 final ApiDetailProperties allAtmsProperties,
                                 final ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.allAtmsApiProperties = allAtmsProperties;
        this.objectMapper = objectMapper;
    }

    public OpenBankingAtmResponse getAllAtms() {
        try {
            log.info("Sending request to downstream to retrieve all atms. URL:[{}]", allAtmsApiProperties.getUrl());
            final var getAllAtmsResponseEntity = restTemplate.getForEntity(allAtmsApiProperties.getUrl(), String.class);
            final var responseBody = getAllAtmsResponseEntity.getBody();
            log.info("Received response from [{}]", allAtmsApiProperties.getUrl());
            log.debug("Received response body: [{}]", responseBody);

            return objectMapper.readValue(responseBody, OpenBankingAtmResponse.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            final var errorMessage = String.format("Unable to get response from {%s}. " +
                            "Response HTTP status code: [%s]. Response body: [%s]",
                    allAtmsApiProperties.getUrl(),
                    e.getRawStatusCode(),
                    e.getLocalizedMessage());

            throw new DownstreamApiException(errorMessage, e);
        } catch (JsonProcessingException e) {
            final var errorMessage = String.format(
                    "Unable to map response from [%s] to OpenBankingAtmResponse.java", allAtmsApiProperties.getUrl());
            throw new DownstreamApiException(errorMessage, e);
        }
    }
}
