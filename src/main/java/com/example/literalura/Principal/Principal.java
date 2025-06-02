package com.example.literalura.Principal;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.literalura.Models.Autor;
import com.example.literalura.Models.Livro;
import com.example.literalura.Models.Results;
import com.example.literalura.Repositories.AutorRepository;
import com.example.literalura.Repositories.LivroRepository;
import com.example.literalura.Services.ConverteDados;
import com.example.literalura.Services.FazRequisicao;

@Service
public class Principal {

    @Autowired
    private LivroRepository repositorioLivro;
    @Autowired
    private AutorRepository repositorioAutor;

    Scanner leitura = new Scanner(System.in);
    FazRequisicao req = new FazRequisicao();
    ConverteDados conversor = new ConverteDados();

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
                var livro = leitura.nextLine().replaceAll(" ", "+");
                var json = req.obterDados(endereco+livro);
                Results result = conversor.ObterDados(json, Results.class);
                List<Livro> livro1 = result.getResults();
                Livro livrao = livro1.get(0);
                repositorioLivro.save(livrao);
                System.out.println(livrao);
            }
            case 2 ->{
                List<Livro> livros = repositorioLivro.findAll();
                livros.forEach(System.out::println);
            }
            case 3 ->{
                List<Autor> autor = repositorioAutor.findAll();
                autor.forEach(System.out::println);
            }
            case 4 ->{

            }
            case 5 ->{
                
            }
            default -> System.out.println("Valor inválido");
        }
    }


}
