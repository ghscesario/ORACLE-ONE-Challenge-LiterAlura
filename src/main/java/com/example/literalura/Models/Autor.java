package com.example.literalura.Models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.EqualsAndHashCode;


@Entity
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown=true)
public class Autor{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int birth_year;
    private int death_year;
    @ManyToMany(mappedBy = "authors")
    private List<Livro> books = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append("----------AUTOR----------").append("\n");
        sb.append("Nome: ").append(name).append("\n");
        sb.append("Ano de nascimento: ").append(birth_year).append("\n");
        sb.append("Ano de falescimento: ").append(death_year).append("\n");
        sb.append("-------------------------").append("\n");
        return sb.toString();
    }

    public String nameToString()
    {
        return name;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBirth_year() {
        return birth_year;
    }

    public int getDeath_year() {
        return death_year;
    }

    public List<Livro> getBooks() {
        return books;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirth_year(int birth_year) {
        this.birth_year = birth_year;
    }

    public void setDeath_year(int death_year) {
        this.death_year = death_year;
    }

    public void setBooks(List<Livro> books) {
        this.books = books;
    }

    

}
