package com.krinal.lloyds.interview_coding_solution.atm.service;

import com.krinal.lloyds.interview_coding_solution.atm.api.response.AtmDetail;
import com.krinal.lloyds.interview_coding_solution.atm.api.response.AtmResponse;
import com.krinal.lloyds.interview_coding_solution.atm.client.AtmClient;
import com.krinal.lloyds.interview_coding_solution.atm.client.response.model.OpenBankingAtmResponse;
import com.krinal.lloyds.interview_coding_solution.atm.client.response.validator.ResponseValidator;
import com.krinal.lloyds.interview_coding_solution.atm.converter.ResponseConverter;
import com.krinal.lloyds.interview_coding_solution.configuration.properties.LloydsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DefaultAtmService implements AtmService {
    private final AtmClient atmClient;
    private final ResponseConverter<OpenBankingAtmResponse, List<AtmDetail>> thirdPartyResponseToAtmResponseConverter;
    private final LloydsProperties lloydsProperties;
    private final ResponseValidator<OpenBankingAtmResponse> responseValidator;

    public DefaultAtmService(final AtmClient atmClient,
                             final ResponseConverter<OpenBankingAtmResponse, List<AtmDetail>> thirdPartyResponseToAtmResponseConverter,
                             final LloydsProperties lloydsProperties,
                             final ResponseValidator<OpenBankingAtmResponse> responseValidator) {
        this.atmClient = atmClient;
        this.thirdPartyResponseToAtmResponseConverter = thirdPartyResponseToAtmResponseConverter;
        this.lloydsProperties = lloydsProperties;
        this.responseValidator = responseValidator;
    }

    @Override
    public AtmResponse getAllAtms() {
        final var openBankingAtmResponse = atmClient.getAllAtms();
        responseValidator.isBeanValid(openBankingAtmResponse);
        log.info("Successfully validated get all ATMs response from downstream");
        final var atmList = thirdPartyResponseToAtmResponseConverter.convert(openBankingAtmResponse);

        return new AtmResponse(lloydsProperties.getIdentification(), atmList);
    }
}
