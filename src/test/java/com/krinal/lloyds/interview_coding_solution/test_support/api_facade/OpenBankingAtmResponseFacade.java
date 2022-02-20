package com.krinal.lloyds.interview_coding_solution.test_support.api_facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.krinal.lloyds.interview_coding_solution.atm.client.response.model.*;
import com.krinal.lloyds.interview_coding_solution.test_support.JsonObjectToStringMapper;
import com.krinal.lloyds.interview_coding_solution.test_support.ResourcesFileReader;

import java.util.List;

public class OpenBankingAtmResponseFacade {
    private static final String ALL_FIELDS_RESPONSE_FILEPATH = "/test_data/openapi_get_all_atms_valid.json";
    private static final String NULL_FIELDS_RESPONSE_FILEPATH = "/test_data/openapi_get_all_atms_null_fields.json";

    private final ResourcesFileReader resourcesFileReader = new ResourcesFileReader();
    private final JsonObjectToStringMapper jsonObjectToStringMapper = new JsonObjectToStringMapper();

    public OpenBankingAtmResponse readFileWithAllFields() throws JsonProcessingException {
        return resourcesFileReader.serialiseJsonFileFromResources(ALL_FIELDS_RESPONSE_FILEPATH, OpenBankingAtmResponse.class);
    }

    public String readFileWithAllFieldsAsString() {
        return resourcesFileReader.readFileFromResources(ALL_FIELDS_RESPONSE_FILEPATH);
    }

    public OpenBankingAtmResponse readFileWithNullFields() throws JsonProcessingException {
        return resourcesFileReader.serialiseJsonFileFromResources(NULL_FIELDS_RESPONSE_FILEPATH, OpenBankingAtmResponse.class);
    }

    public OpenBankingAtmResponse readFileWithCustomisedCurrencies(final List<String> currenciesList) throws JsonProcessingException {
        final var nullFieldsResponse = readFileWithNullFields();
        final var brandListFromApi = nullFieldsResponse.getData().get(0).getBrand();
        brandListFromApi.get(0).getAtm().get(0).setSupportedCurrencies(currenciesList);

        return nullFieldsResponse;
    }

    public OpenBankingAtmResponse readFileWithCustomisedCountrySubdivision(final List<String> countrySubdivision) throws JsonProcessingException {
        final var nullFieldsResponse = readFileWithNullFields();
        final var brandListFromApi = nullFieldsResponse.getData().get(0).getBrand();
        brandListFromApi.get(0).getAtm().get(0).getLocation().getPostalAddress().setCountrySubDivision(countrySubdivision);

        return nullFieldsResponse;
    }

    public OpenBankingAtmResponse readFileWithMissingMetaField() throws JsonProcessingException {
        final var allFieldsResponse = readFileWithAllFields();
        allFieldsResponse.setMeta(null);

        return allFieldsResponse;
    }

    public String readFileWithMissingMetaFieldAsString() throws JsonProcessingException {
        final var allFieldsResponse = readFileWithMissingMetaField();
        return jsonObjectToStringMapper.jsonObjectToString(allFieldsResponse);
    }

    public List<Brand> getBrandListFromOpenBankingAtmResponse(OpenBankingAtmResponse openBankingAtmResponse) {
        return openBankingAtmResponse.getData().get(0).getBrand();
    }

    public Data getDataFromOpenBankingAtmResponse(OpenBankingAtmResponse openBankingAtmResponse) {
        return openBankingAtmResponse.getData().get(0);
    }

    public List<Atm> getAtmListFromOpenBankingAtmResponse(OpenBankingAtmResponse openBankingAtmResponse) {
        return openBankingAtmResponse.getData().get(0).getBrand().get(0).getAtm();
    }

    public Location getLocationFromOpenBankingAtmResponse(OpenBankingAtmResponse openBankingAtmResponse) {
        return openBankingAtmResponse.getData().get(0).getBrand().get(0).getAtm().get(0).getLocation();
    }

    public PostalAddress getPostalAddressLocationFromOpenBankingAtmResponse(OpenBankingAtmResponse openBankingAtmResponse) {
        return openBankingAtmResponse.getData().get(0).getBrand().get(0).getAtm().get(0).getLocation().getPostalAddress();
    }
}
