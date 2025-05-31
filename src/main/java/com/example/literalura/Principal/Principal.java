package com.example.literalura.Principal;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.literalura.Models.Autor;
import com.example.literalura.Models.Livro;
import com.example.literalura.Models.Results;
import com.example.literalura.Repositories.LivroRepository;
import com.example.literalura.Services.ConverteDados;
import com.example.literalura.Services.FazRequisicao;

@Service
public class Principal {

    @Autowired
    private LivroRepository repositorio;

    Scanner leitura = new Scanner(System.in);
    @SuppressWarnings("FieldMayBeFinal")
    private String endereco="https://gutendex.com/books/?search=";
    
    public void menu(){
        var opcao = -1;
        System.out.println("-----------------------------------------------");
        System.out.println("Escolha o número de sua opção");
        System.out.println("1 - Buscar livros pelo título");
        System.out.println("2 - Listar livros registrados");
        System.out.println("3 - Listar autores registrados");
        System.out.println("4 - Listar autores vivos em um determinado ano");
        System.out.println("5 - Listar livros em um determinado idioma");
        System.out.println("0 - Sair");
        System.out.println("-----------------------------------------------");

        opcao = leitura.nextInt();
        leitura.nextLine();
        switch(opcao){
            case 1 -> {
                System.out.println("Informe o livro que deseja buscar");
                var livro = leitura.nextLine();
                FazRequisicao req = new FazRequisicao();
                var json = req.obterDados(endereco+livro);
                ConverteDados conversor = new ConverteDados();
                Results result = conversor.ObterDados(json, Results.class);
                List<Livro> livro1 = result.getResults();
                Livro livrao = livro1.get(0);
                livrao.getAuthors();
                repositorio.save(livrao);
                List<Autor> autor = livrao.getAuthors();
                System.out.println("Titulo: "+livrao.getTitle()+", Autores: "+autor);
            }
            default -> System.out.println("Valor inválido");
        }
    }


}
