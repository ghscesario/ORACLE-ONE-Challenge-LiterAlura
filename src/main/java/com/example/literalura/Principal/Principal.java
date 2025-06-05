package com.example.literalura.Principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        while(opcao != 0){
            System.out.println("-----------------------------------------------");
            System.out.println("Escolha o número de sua opção");
            System.out.println("1 - Buscar livros pelo título");
            System.out.println("2 - Listar livros registrados");
            System.out.println("3 - Listar autores registrados");
            System.out.println("4 - Listar autores vivos em um determinado ano");
            System.out.println("5 - Listar livros em um determinado idioma");
            System.out.println("0 - Sair");
            System.out.println("-----------------------------------------------");

            //Fazendo a conversão do valor informado para que se caso for uma String o erro ser tratado e retornar ao menu
            String escolha = leitura.nextLine();
            try{
                int number = Integer.parseInt(escolha);
                opcao = number;
            }
            catch (NumberFormatException ex){
                System.out.print("");
                //É definido o valor -1 para que os cases sejam lidos normalmente, pois o scanner termina o catch com o valor anterior armazenado
                opcao = -1;
            }

            switch(opcao){ 
                case 0 ->{
                    break;
                }          
                case 1 -> {
                    System.out.println("Informe o livro que deseja buscar");
                    var livroBuscado = leitura.nextLine().replaceAll(" ", "+");

                    var json = req.obterDados(endereco + livroBuscado);
                    Results result = conversor.ObterDados(json, Results.class);
                    List<Livro> resultados = result.getResults();

                    if (resultados.isEmpty()) {
                        System.out.println("-----------------------------------------------\n");
                        System.out.println("Nenhum livro encontrado.\n");
                        System.out.println("-----------------------------------------------\n");
                        break;
                    }

                    Livro livrao = resultados.get(0);

                    // Pega o autor do livro buscado (vindo da API)
                    Autor autorDoLivro = livrao.getAuthors().get(0);

                    // Busca no banco por um autor com o mesmo nome
                    Optional<Autor> autorExistenteOpt = repositorioAutor.findByName(autorDoLivro.getName());

                    if (autorExistenteOpt.isPresent()) {
                        // Usa o autor que já está no banco (gerenciado pelo JPA)
                        livrao.setAuthors(List.of(autorExistenteOpt.get()));
                    } else {
                        // Salva o novo autor (agora ele será gerenciado pelo JPA)
                        Autor autorSalvo = repositorioAutor.save(autorDoLivro);
                        livrao.setAuthors(List.of(autorSalvo));
                    }

                    // Verifica se o livro já existe
                    Livro livroExistente = repositorioLivro.findByTitle(livrao.getTitle());

                    if (livroExistente != null) {
                        System.out.println(livroExistente);
                    } else {
                        repositorioLivro.save(livrao);
                        System.out.println(livrao);
                    }
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
                    System.out.println("Digite o ano em que deseja buscar autores vivos!");
                    Double ano = leitura.nextDouble();
                    List<Autor> autoresAnoBuscado = new ArrayList<>();
                    List<Autor> todos = repositorioAutor.findAll();
                    for(int i = 0; i < todos.size(); i++){
                        if(todos.get(i).getDeath_year() >= ano && todos.get(i).getDeath_year() > 1 && todos.get(i).getBirth_year() <= ano){
                            autoresAnoBuscado.add(todos.get(i));
                        }
                    }
                    if(autoresAnoBuscado.isEmpty()){
                        System.out.println("-----------------------------------------------\n");
                        System.out.println("Nenhum autor vivo no ano buscado!\n");
                        System.out.println("-----------------------------------------------\n");
                    }
                    else{
                        System.out.println("-----------------------------------------------");
                        autoresAnoBuscado.forEach(System.out::println);
                        System.out.println("-----------------------------------------------\n");
                    }

                }
                case 5 -> {
                    System.out.println("Digite o idioma (ex: 'en', 'pt', 'es'):");
                    String idioma = leitura.nextLine().trim().toLowerCase();
                    List<Livro> livrosIdiomaBuscado = new ArrayList<>();
                    List<Livro> todos = repositorioLivro.findAll();
                    for(int i = 0; i < todos.size(); i++){
                        if(todos.get(i).getLanguages().get(0).contains(idioma)){
                            livrosIdiomaBuscado.add(todos.get(i));
                        }
                    }
                    if(livrosIdiomaBuscado.isEmpty()){
                        System.out.println("-----------------------------------------------\n");
                        System.out.println("Nenhum livro encontrado com o idioma buscado!\n");
                        System.out.println("-----------------------------------------------\n");
                    }
                    else{
                        System.out.println("-----------------------------------------------");
                        livrosIdiomaBuscado.forEach(System.out::println);
                        System.out.println("-----------------------------------------------\n");
                    } 
                }
                default -> {
                    System.out.println("-----------------------------------------------\n");
                    System.out.println("Valor inválido\n");
                    System.out.println("-----------------------------------------------\n");
                }
            }
        }
    }
}
