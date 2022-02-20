package com.krinal.lloyds.interview_coding_solution.test_support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.krinal.lloyds.interview_coding_solution.atm.converter.ThirdPartyResponseToAtmListConverterTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class ResourcesFileReader {
    private final JsonObjectToStringMapper jsonObjectToStringMapper = new JsonObjectToStringMapper();
     public String readFileFromResources(final String filepath) {
        final var fileInputStream = ThirdPartyResponseToAtmListConverterTest.class.getResourceAsStream(filepath);
        final var fileInputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
        return new BufferedReader(fileInputStreamReader)
                .lines()
                .collect(Collectors.joining("\n"));
    }

     public <T> T serialiseJsonFileFromResources(final String filepath, Class<T> clazz) throws JsonProcessingException {
        final var jsonString = readFileFromResources(filepath);
        return jsonObjectToStringMapper.jsonStringToObject(jsonString, clazz);
    }
}
