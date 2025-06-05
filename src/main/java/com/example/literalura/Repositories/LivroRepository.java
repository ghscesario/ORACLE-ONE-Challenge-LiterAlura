package com.example.literalura.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.literalura.Models.Livro;


@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{
    Livro findByTitle(String title);

    @Query("SELECT l FROM Livro l WHERE :idioma MEMBER OF l.languages")
    List<Livro> buscarPorIdioma(@Param("idioma") String idioma);
}

