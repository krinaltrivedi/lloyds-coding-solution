package com.krinal.lloyds.interview_coding_solution.atm.service;

import com.krinal.lloyds.interview_coding_solution.atm.client.AtmClient;
import com.krinal.lloyds.interview_coding_solution.atm.client.response.model.OpenBankingAtmResponse;
import com.krinal.lloyds.interview_coding_solution.atm.client.response.validator.ResponseValidator;
import com.krinal.lloyds.interview_coding_solution.atm.converter.ResponseConverter;
import com.krinal.lloyds.interview_coding_solution.atm.api.response.AtmDetail;
import com.krinal.lloyds.interview_coding_solution.atm.service.DefaultAtmService;
import com.krinal.lloyds.interview_coding_solution.configuration.properties.LloydsProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultAtmServiceTest {
    @Mock
    private AtmClient atmClient;

    @Mock
    private ResponseConverter<OpenBankingAtmResponse, List<AtmDetail>> thirdPartyResponseToAtmResponseConverter;

    @Mock
    private LloydsProperties lloydsProperties;

    @Mock
    private ResponseValidator<OpenBankingAtmResponse> openBankingAtmResponseResponseValidator;

    private DefaultAtmService defaultAtmService;

    @BeforeEach
    public void setupTests() {
        defaultAtmService = new DefaultAtmService(atmClient,
                thirdPartyResponseToAtmResponseConverter,
                lloydsProperties,
                openBankingAtmResponseResponseValidator);
    }

    @Test
    public void getAllAtms_shouldReturnAllAtmsFromDownstreamAndCreateAtmResponseObject() {
        //Given
        final var identification = "123";
        final var openBankingAtmResponse = new OpenBankingAtmResponse();
        final var atmDetailList = List.of(new AtmDetail());
        when(atmClient.getAllAtms()).thenReturn(openBankingAtmResponse);
        when(thirdPartyResponseToAtmResponseConverter.convert(openBankingAtmResponse)).thenReturn(atmDetailList);
        when(lloydsProperties.getIdentification()).thenReturn(identification);
        when(openBankingAtmResponseResponseValidator.isBeanValid(openBankingAtmResponse)).thenReturn(true);

        //When
        final var atmResponse = defaultAtmService.getAllAtms();

        //Then
        assertThat(atmResponse.getAtms()).isEqualTo(atmDetailList);
        assertThat(atmResponse.getIdentification()).isEqualTo(identification);
    }
}
