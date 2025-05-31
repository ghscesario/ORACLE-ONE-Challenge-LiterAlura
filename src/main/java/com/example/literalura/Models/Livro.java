package com.example.literalura.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public record Livro(@JsonAlias("authors")List<Autor> autores, @JsonAlias("title")String titulo, @JsonAlias("languages")List<String> idiomas, @JsonAlias("download_count")Long numeroDownloads) {
}
