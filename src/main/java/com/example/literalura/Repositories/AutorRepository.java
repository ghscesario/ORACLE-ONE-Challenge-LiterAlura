package com.example.literalura.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.literalura.Models.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{

    Optional<Autor> findByName(String name);
}
