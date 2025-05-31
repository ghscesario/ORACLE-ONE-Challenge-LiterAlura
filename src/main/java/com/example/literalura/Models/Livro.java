package com.example.literalura.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
public class Livro{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idbanco;
    @JsonAlias("id")
    private Long externalId;
    @ManyToMany(mappedBy = "books" , cascade = CascadeType.ALL)
    private List<Autor> authors;
    private String title;
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
        sb.append("Livro: ").append(title);
        sb.append(", Autores: ").append(authors);
        sb.append(", Idiomas: ").append(languages);
        sb.append(", Numero de Downloads: ").append(download_count);
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
