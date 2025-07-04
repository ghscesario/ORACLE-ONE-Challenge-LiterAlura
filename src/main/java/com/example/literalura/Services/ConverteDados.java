package com.example.literalura.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados{
    @SuppressWarnings("FieldMayBeFinal")
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T ObterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
