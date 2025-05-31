package com.example.literalura.Models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public record Autor(Long id, @JsonAlias("name")String nome, @JsonAlias("birth_year")int anoNascimento, @JsonAlias("death_year")int anoFalescimento) {
    
}
