package br.edu.fatecpg.spring.Jackson.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ConverteDados {
    private ObjectMapper mapper = new ObjectMapper();

    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter JSON", e);
        }
    }
}