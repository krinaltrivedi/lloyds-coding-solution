package com.krinal.lloyds.interview_coding_solution.test_support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonObjectToStringMapper {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T jsonStringToObject(String jsonString, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(jsonString, clazz);
    }

    public <T> String jsonObjectToString(T t) throws JsonProcessingException {
        return objectMapper.writeValueAsString(t);
    }
}
