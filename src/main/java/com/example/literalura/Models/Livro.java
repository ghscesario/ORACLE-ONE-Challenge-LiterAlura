package com.example.literalura.Models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
public class Livro{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idbanco;
    @JsonAlias("id")
    private Long externalId;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
        name = "livro_autor",
        joinColumns = @JoinColumn(name = "livro_id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> authors = new ArrayList<>();
    private String title;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "languages")
    private List<String> languages;
    
    private Long download_count;


    public List<Autor> getAuthors() {
        return authors;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public Long getDownload_count() {
        return download_count;
    }

    public void setAuthors(List<Autor> authors) {
        this.authors = authors;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public void setDownload_count(Long download_count) {
        this.download_count = download_count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("----------LIVRO----------").append("\n");
        sb.append("Título: ").append(title).append("\n");
        sb.append("Autor: ");
        if (authors != null && !authors.isEmpty()) {
            String nomesAutores = authors.stream()
                .map(Autor::getName)
                .reduce((a, b) -> a + ", " + b)
                .orElse("Desconhecido");
            sb.append(nomesAutores);
        } else {
            sb.append("Desconhecido");
        }
        sb.append("\n");
        sb.append("Idioma: ").append(languages).append("\n");
        sb.append("Numero de Downloads: ").append(download_count).append("\n");
        sb.append("-------------------------").append("\n");
        return sb.toString();
    }

    public Long getIdbanco() {
        return idbanco;
    }

    public Long getExternalId() {
        return externalId;
    }

    public void setIdbanco(Long idbanco) {
        this.idbanco = idbanco;
    }
}
