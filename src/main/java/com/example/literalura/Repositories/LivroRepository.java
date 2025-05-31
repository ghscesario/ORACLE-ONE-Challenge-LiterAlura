package com.example.literalura.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.literalura.Models.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{
    
}
