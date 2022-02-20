package com.krinal.lloyds.interview_coding_solution.atm.client.response.validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.krinal.lloyds.interview_coding_solution.test_support.api_facade.OpenBankingAtmResponseFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class OpenBankingAtmResponseValidatorTest {
    private OpenBankingAtmResponseFacade openBankingAtmResponseFacade;
    private OpenBankingAtmResponseValidator openBankingAtmResponseValidator;
    @BeforeEach
    public void setupTests() {
        openBankingAtmResponseFacade = new OpenBankingAtmResponseFacade();
        openBankingAtmResponseValidator = new OpenBankingAtmResponseValidator();
    }

    @Test
    public void validateBean_doesNotThrowExceptionWhenAllFieldsAreCorrectlyPopulated() throws JsonProcessingException {
        //Given
        final var openBankingAtmResponse = openBankingAtmResponseFacade.readFileWithAllFields();

        //When
        final var validationResult = openBankingAtmResponseValidator.isBeanValid(openBankingAtmResponse);

        //Then
        assertThat(validationResult).isTrue();
    }

    @Test
    public void validateBean_throwsInvalidResponseException_Meta_IsNull() throws JsonProcessingException {
        //Given
        final var openBankingAtmResponse = openBankingAtmResponseFacade.readFileWithAllFields();
        openBankingAtmResponse.setMeta(null);

        //When, Then
        assertThatExceptionOfType(InvalidResponseException.class)
                .isThrownBy(() -> openBankingAtmResponseValidator.isBeanValid(openBankingAtmResponse));
    }

    @Test
    public void validateBean_throwsInvalidResponseExceptionWhenMetaAttribute_Agreement_IsNull() throws JsonProcessingException {
        //Given
        final var openBankingAtmResponse = openBankingAtmResponseFacade.readFileWithAllFields();
        openBankingAtmResponse.getMeta().setAgreement(null);

        //When, Then
        assertThatExceptionOfType(InvalidResponseException.class)
                .isThrownBy(() -> openBankingAtmResponseValidator.isBeanValid(openBankingAtmResponse));
    }

    @Test
    public void validateBean_throwsInvalidResponseExceptionWhenMetaAttribute_Agreement_IsBlank() throws JsonProcessingException {
        //Given
        final var openBankingAtmResponse = openBankingAtmResponseFacade.readFileWithAllFields();
        openBankingAtmResponse.getMeta().setAgreement("");

        //When, Then
        assertThatExceptionOfType(InvalidResponseException.class)
                .isThrownBy(() -> openBankingAtmResponseValidator.isBeanValid(openBankingAtmResponse));
    }

    @Test
    public void validateBean_throwsInvalidResponseExceptionWhenMetaAttribute_License_IsNull() throws JsonProcessingException {
        //Given
        final var openBankingAtmResponse = openBankingAtmResponseFacade.readFileWithAllFields();
        openBankingAtmResponse.getMeta().setLicense(null);

        //When, Then
        assertThatExceptionOfType(InvalidResponseException.class)
                .isThrownBy(() -> openBankingAtmResponseValidator.isBeanValid(openBankingAtmResponse));
    }

    @Test
    public void validateBean_throwsInvalidResponseExceptionWhenMetaAttribute_License_IsBlank() throws JsonProcessingException {
        //Given
        final var openBankingAtmResponse = openBankingAtmResponseFacade.readFileWithAllFields();
        openBankingAtmResponse.getMeta().setLicense("");

        //When, Then
        assertThatExceptionOfType(InvalidResponseException.class)
                .isThrownBy(() -> openBankingAtmResponseValidator.isBeanValid(openBankingAtmResponse));
    }

    @Test
    public void validateBean_throwsInvalidResponseExceptionWhenMetaAttribute_TermsOfUse_IsNull() throws JsonProcessingException {
        //Given
        final var openBankingAtmResponse = openBankingAtmResponseFacade.readFileWithAllFields();
        openBankingAtmResponse.getMeta().setAgreement(null);

        //When, Then
        assertThatExceptionOfType(InvalidResponseException.class)
                .isThrownBy(() -> openBankingAtmResponseValidator.isBeanValid(openBankingAtmResponse));
    }

    @Test
    public void validateBean_throwsInvalidResponseExceptionWhenMetaAttribute_TermsOfUse_IsBlank() throws JsonProcessingException {
        //Given
        final var openBankingAtmResponse = openBankingAtmResponseFacade.readFileWithAllFields();
        openBankingAtmResponse.getMeta().setTermsOfUse("");

        //When, Then
        assertThatExceptionOfType(InvalidResponseException.class)
                .isThrownBy(() -> openBankingAtmResponseValidator.isBeanValid(openBankingAtmResponse));
    }

    @Test
    public void validateBean_throwsInvalidResponseExceptionWhenMetaAttribute_TotalResults_IsNull() throws JsonProcessingException {
        //Given
        final var openBankingAtmResponse = openBankingAtmResponseFacade.readFileWithAllFields();
        openBankingAtmResponse.getMeta().setTotalResults(null);

        //When, Then
        assertThatExceptionOfType(InvalidResponseException.class)
                .isThrownBy(() -> openBankingAtmResponseValidator.isBeanValid(openBankingAtmResponse));
    }

    @Test
    public void validateBean_throwsInvalidResponseExceptionWhenMetaAttribute_LastUpdated_IsNull() throws JsonProcessingException {
        //Given
        final var openBankingAtmResponse = openBankingAtmResponseFacade.readFileWithAllFields();
        openBankingAtmResponse.getMeta().setLastUpdated(null);

        //When, Then
        assertThatExceptionOfType(InvalidResponseException.class)
                .isThrownBy(() -> openBankingAtmResponseValidator.isBeanValid(openBankingAtmResponse));
    }

    @Test
    public void validateBean_throwsInvalidResponseExceptionWhenDataListIsNull() throws JsonProcessingException {
        //Given
        final var openBankingAtmResponse = openBankingAtmResponseFacade.readFileWithAllFields();
        openBankingAtmResponse.setData(null);

        //When, Then
        assertThatExceptionOfType(InvalidResponseException.class)
                .isThrownBy(() -> openBankingAtmResponseValidator.isBeanValid(openBankingAtmResponse));
    }

    @Test
    public void validateBean_throwsInvalidResponseExceptionWhenBrandListIsNull() throws JsonProcessingException {
        //Given
        final var openBankingAtmResponse = openBankingAtmResponseFacade.readFileWithAllFields();
        final var data = openBankingAtmResponseFacade.getDataFromOpenBankingAtmResponse(openBankingAtmResponse);
        data.setBrand(null);

        //When, Then
        assertThatExceptionOfType(InvalidResponseException.class)
                .isThrownBy(() -> openBankingAtmResponseValidator.isBeanValid(openBankingAtmResponse));
    }

    @Test
    public void validateBean_throwsInvalidResponseExceptionWhenBrandListIsEmpty() throws JsonProcessingException {
        //Given
        final var openBankingAtmResponse = openBankingAtmResponseFacade.readFileWithAllFields();
        final var data = openBankingAtmResponseFacade.getDataFromOpenBankingAtmResponse(openBankingAtmResponse);
        data.setBrand(List.of());

        //When, Then
        assertThatExceptionOfType(InvalidResponseException.class)
                .isThrownBy(() -> openBankingAtmResponseValidator.isBeanValid(openBankingAtmResponse));
    }

    @Test
    public void validateBean_throwsInvalidResponseExceptionWhenBrandNameIsNull() throws JsonProcessingException {
        //Given
        final var openBankingAtmResponse = openBankingAtmResponseFacade.readFileWithAllFields();
        final var brandList = openBankingAtmResponseFacade.getBrandListFromOpenBankingAtmResponse(openBankingAtmResponse);
        brandList.get(0).setBrandName(null);

        //When, Then
        assertThatExceptionOfType(InvalidResponseException.class)
                .isThrownBy(() -> openBankingAtmResponseValidator.isBeanValid(openBankingAtmResponse));
    }

    @Test
    public void validateBean_throwsInvalidResponseExceptionWhenAtmListIsNull() throws JsonProcessingException {
        //Given
        final var openBankingAtmResponse = openBankingAtmResponseFacade.readFileWithAllFields();
        final var brandList = openBankingAtmResponseFacade.getBrandListFromOpenBankingAtmResponse(openBankingAtmResponse);
        brandList.get(0).setAtm(null);

        //When, Then
        assertThatExceptionOfType(InvalidResponseException.class)
                .isThrownBy(() -> openBankingAtmResponseValidator.isBeanValid(openBankingAtmResponse));
    }

    @Test
    public void validateBean_throwsInvalidResponseExceptionWhenAtmAttribute_Identification_IsNull() throws JsonProcessingException {
        //Given
        final var openBankingAtmResponse = openBankingAtmResponseFacade.readFileWithAllFields();
        final var atmList = openBankingAtmResponseFacade.getAtmListFromOpenBankingAtmResponse(openBankingAtmResponse);
        atmList.get(0).setIdentification(null);

        //When, Then
        assertThatExceptionOfType(InvalidResponseException.class)
                .isThrownBy(() -> openBankingAtmResponseValidator.isBeanValid(openBankingAtmResponse));
    }

    @Test
    public void validateBean_throwsInvalidResponseExceptionWhenAtmAttribute_Currencies_IsNull() throws JsonProcessingException {
        //Given
        final var openBankingAtmResponse = openBankingAtmResponseFacade.readFileWithAllFields();
        final var atmList = openBankingAtmResponseFacade.getAtmListFromOpenBankingAtmResponse(openBankingAtmResponse);
        atmList.get(0).setSupportedCurrencies(null);

        //When, Then
        assertThatExceptionOfType(InvalidResponseException.class)
                .isThrownBy(() -> openBankingAtmResponseValidator.isBeanValid(openBankingAtmResponse));
    }

    @Test
    public void validateBean_throwsInvalidResponseExceptionWhenAtmAttribute_Currencies_LessThanOneElement() throws JsonProcessingException {
        //Given
        final var openBankingAtmResponse = openBankingAtmResponseFacade.readFileWithAllFields();
        final var atmList = openBankingAtmResponseFacade.getAtmListFromOpenBankingAtmResponse(openBankingAtmResponse);
        atmList.get(0).setSupportedCurrencies(List.of());

        //When, Then
        assertThatExceptionOfType(InvalidResponseException.class)
                .isThrownBy(() -> openBankingAtmResponseValidator.isBeanValid(openBankingAtmResponse));
    }

    @Test
    public void validateBean_throwsInvalidResponseExceptionWhenAtmAttribute_Location_Null() throws JsonProcessingException {
        //Given
        final var openBankingAtmResponse = openBankingAtmResponseFacade.readFileWithAllFields();
        final var atmList = openBankingAtmResponseFacade.getAtmListFromOpenBankingAtmResponse(openBankingAtmResponse);
        atmList.get(0).setLocation(null);

        //When, Then
        assertThatExceptionOfType(InvalidResponseException.class)
                .isThrownBy(() -> openBankingAtmResponseValidator.isBeanValid(openBankingAtmResponse));
    }

    @Test
    public void validateBean_throwsInvalidResponseExceptionWhenAtmAttribute_Currency_NotInISOFormat() throws JsonProcessingException {
        //Given
        final var openBankingAtmResponse = openBankingAtmResponseFacade.readFileWithAllFields();
        final var atmList = openBankingAtmResponseFacade.getAtmListFromOpenBankingAtmResponse(openBankingAtmResponse);
        atmList.get(0).setSupportedCurrencies(List.of("GB"));

        //When, Then
        assertThatExceptionOfType(InvalidResponseException.class)
                .isThrownBy(() -> openBankingAtmResponseValidator.isBeanValid(openBankingAtmResponse));
    }

    @Test
    public void validateBean_throwsInvalidResponseExceptionWhenLocationAttribute_PostalAddress_Null() throws JsonProcessingException {
        //Given
        final var openBankingAtmResponse = openBankingAtmResponseFacade.readFileWithAllFields();
        final var location = openBankingAtmResponseFacade.getLocationFromOpenBankingAtmResponse(openBankingAtmResponse);
        location.setPostalAddress(null);

        //When, Then
        assertThatExceptionOfType(InvalidResponseException.class)
                .isThrownBy(() -> openBankingAtmResponseValidator.isBeanValid(openBankingAtmResponse));
    }
}
