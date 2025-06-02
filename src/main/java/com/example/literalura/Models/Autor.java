package com.example.literalura.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
public class Autor{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int birth_year;
    private int death_year;
    @ManyToMany
    @JoinTable(name = "livro_autor",
        joinColumns = @JoinColumn(name = "autor_id"),
        inverseJoinColumns = @JoinColumn(name = "livro_id"))
    private List<Livro> books;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        return sb.toString();
    }


}
