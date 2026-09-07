package br.edu.fatecpg.spring.Jackson.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Endereco(
        String cep,
        @JsonAlias("logradouro") String rua,
        String bairro,
        @JsonAlias("localidade") String cidade,
        String uf
) {}