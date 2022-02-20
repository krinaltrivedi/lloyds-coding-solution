package com.krinal.lloyds.interview_coding_solution.test_support.api_facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.krinal.lloyds.interview_coding_solution.atm.client.response.model.OpenBankingAtmErrorResponse;
import com.krinal.lloyds.interview_coding_solution.test_support.ResourcesFileReader;

public class OpenBankingAtmErrorResponseFacade {
    private static final String ERROR_RESPONSE_FILEPATH = "/test_data/openapi_get_all_atms_error.json";

    private final ResourcesFileReader resourcesFileReader = new ResourcesFileReader();

    public OpenBankingAtmErrorResponse readFileWithAllErrorFields() throws JsonProcessingException {
        return resourcesFileReader.serialiseJsonFileFromResources(ERROR_RESPONSE_FILEPATH, OpenBankingAtmErrorResponse.class);
    }

    public String readFileWithAllErrorFieldsAsString() {
        return resourcesFileReader.readFileFromResources(ERROR_RESPONSE_FILEPATH);
    }
}
