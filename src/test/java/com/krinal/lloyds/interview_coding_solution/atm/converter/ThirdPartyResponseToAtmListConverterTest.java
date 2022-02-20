package com.krinal.lloyds.interview_coding_solution.atm.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.krinal.lloyds.interview_coding_solution.atm.api.response.AtmDetail;
import com.krinal.lloyds.interview_coding_solution.atm.client.response.model.Atm;
import com.krinal.lloyds.interview_coding_solution.atm.client.response.model.Brand;
import com.krinal.lloyds.interview_coding_solution.test_support.api_facade.OpenBankingAtmResponseFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ThirdPartyResponseToAtmListConverterTest {
    private static final String EMPTY_STRING = "";
    private final OpenBankingAtmResponseFacade openBankingAtmResponseFacade = new OpenBankingAtmResponseFacade();

    private ThirdPartyResponseToAtmListConverter thirdPartyResponseToAtmListConverter;

    @BeforeEach
    public  void setupTests() {
        thirdPartyResponseToAtmListConverter = new ThirdPartyResponseToAtmListConverter();
    }

    @Test
    public void convert_shouldConvertAllAtmsResponseToAtmListWithAllFieldsPopulated() throws JsonProcessingException {
        //Given
        final var openBankingAllAtmsResponseValid = openBankingAtmResponseFacade.readFileWithAllFields();
        final var brandListFromApi = openBankingAtmResponseFacade.getBrandListFromOpenBankingAtmResponse(openBankingAllAtmsResponseValid);

        //When
        final var atmDetailList = thirdPartyResponseToAtmListConverter.convert(openBankingAllAtmsResponseValid);

        //Then
        assertResponseConversion(brandListFromApi, atmDetailList);
    }

    @Test
    public void convert_shouldConvertAllAtmsResponseToAtmListWithAllFieldsNull() throws JsonProcessingException {
        //Given
        final var openBankingAllAtmsResponseNullFields = openBankingAtmResponseFacade.readFileWithNullFields();
        final var brandListFromApi = openBankingAtmResponseFacade.getBrandListFromOpenBankingAtmResponse(openBankingAllAtmsResponseNullFields);

        //When
        final var atmDetailList = thirdPartyResponseToAtmListConverter.convert(openBankingAllAtmsResponseNullFields);

        //Then
        assertResponseConversion(brandListFromApi, atmDetailList);
    }

    @Test
    public void convert_shouldConvertAllAtmsResponseToAtmListWithSupportedCurrenciesEmpty() throws JsonProcessingException {
        //Given
        final var openBankingAllAtmsResponseNullFields = openBankingAtmResponseFacade.readFileWithCustomisedCurrencies(List.of());
        final var brandListFromApi = openBankingAtmResponseFacade.getBrandListFromOpenBankingAtmResponse(openBankingAllAtmsResponseNullFields);

        //When
        final var atmDetailList = thirdPartyResponseToAtmListConverter.convert(openBankingAllAtmsResponseNullFields);

        //Then
        assertResponseConversion(brandListFromApi, atmDetailList);
    }

    @Test
    public void convert_shouldConvertAllAtmsResponseToAtmListWithCountrySubdivisionEmpty() throws JsonProcessingException {
        //Given
        final var openBankingAllAtmsResponseNullFields = openBankingAtmResponseFacade.readFileWithCustomisedCountrySubdivision(List.of());
        final var brandListFromApi = openBankingAtmResponseFacade.getBrandListFromOpenBankingAtmResponse(openBankingAllAtmsResponseNullFields);

        //When
        final var atmDetailList = thirdPartyResponseToAtmListConverter.convert(openBankingAllAtmsResponseNullFields);

        //Then
        assertResponseConversion(brandListFromApi, atmDetailList);
    }

    @Test
    public void convert_shouldConvertAllAtmsResponseToAtmListWithCountrySubdivisionHavingMultipleValues() throws JsonProcessingException {
        //Given
        final var openBankingAllAtmsResponseNullFields = openBankingAtmResponseFacade.readFileWithCustomisedCountrySubdivision(
                List.of("London", "Greater London")
        );

        final var brandListFromApi = openBankingAtmResponseFacade.getBrandListFromOpenBankingAtmResponse(openBankingAllAtmsResponseNullFields);

        //When
        final var atmDetailList = thirdPartyResponseToAtmListConverter.convert(openBankingAllAtmsResponseNullFields);

        //Then
        assertResponseConversion(brandListFromApi, atmDetailList);
    }

    private void assertResponseConversion(final List<Brand> brandListFromApi, final List<AtmDetail> atmDetailList) {
        int atmDetailListNextIndex = 0;
        for (final Brand brand : brandListFromApi) {
            final var atmListFromApi = brand.getAtm();
            for (final Atm atmFromApi : atmListFromApi) {
                final var convertedAtmDetail = atmDetailList.get(atmDetailListNextIndex);

                assertThat(convertedAtmDetail.getBrandName()).isEqualTo(brand.getBrandName());
                assertThat(convertedAtmDetail.getIdentification()).isEqualTo(atmFromApi.getIdentification());
                assertThat(convertedAtmDetail.getSupportedCurrencies()).isEqualTo(atmFromApi.getSupportedCurrencies());
                assertThat(convertedAtmDetail.getAddress().getAddressLine1())
                        .isEqualTo(atmFromApi.getLocation().getPostalAddress().getStreetName());
                assertThat(convertedAtmDetail.getAddress().getTown())
                        .isEqualTo(atmFromApi.getLocation().getPostalAddress().getTownName());
                assertCountrySubdivisionConversion(atmFromApi, convertedAtmDetail);

                assertThat(convertedAtmDetail.getAddress().getPostcode())
                        .isEqualTo(atmFromApi.getLocation().getPostalAddress().getPostcode());

                atmDetailListNextIndex += 1;
            }
        }
    }

    private void assertCountrySubdivisionConversion(Atm atmFromApi, AtmDetail convertedAtmDetail) {
        final var countrySubdivision = atmFromApi.getLocation().getPostalAddress().getCountrySubDivision();
        final var county = convertedAtmDetail.getAddress().getCounty();
        if (isListNull(countrySubdivision)) {
            assertThat(convertedAtmDetail.getAddress().getCounty()).isNull();
        } else if (isListEmpty(countrySubdivision)) {
            assertThat(county).isEqualTo(EMPTY_STRING);
        } else {
            assertThat(county).isEqualTo(String.join(", ", countrySubdivision));
        }
    }

    private <T> boolean isListNull(List<T> list) {
        return list == null;
    }

    private <T> boolean isListEmpty(List<T> list) {
        return list.isEmpty();
    }

}
