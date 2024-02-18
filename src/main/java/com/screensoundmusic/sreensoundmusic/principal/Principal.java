package com.screensoundmusic.sreensoundmusic.principal;

import com.screensoundmusic.sreensoundmusic.model.Artista;
import com.screensoundmusic.sreensoundmusic.model.Musica;
import com.screensoundmusic.sreensoundmusic.model.TipoArtista;
import com.screensoundmusic.sreensoundmusic.repository.ArtistaRepository;
import com.screensoundmusic.sreensoundmusic.service.ConsultaChatGpt;

import java.util.*;

public class Principal {

    private Scanner sc = new Scanner(System.in);
    private Artista artista = new Artista();
    private ArtistaRepository repository;

    public Principal(ArtistaRepository repository) {
        this.repository = repository;
    }

    public void exibeMenu() {

        var opcao = -1;

        while (opcao != 0) {
            var menu ="""
                    ***Screen Sound Music***
                                    
                    1 - Cadastrar artistas
                    2 - Cadastrar músicas
                    3 - Listar músicas
                    4 - Buscar músicas por artistas
                    5 - Pesquisar dados sobre um artista
                                    
                    0 - Sair""";

            System.out.println(menu);
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusica();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    musicasPorArtista();
                    break;
                case 5:
                    consultarArtista();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção Inválida!");
            }
        }
    }

    private void cadastrarArtista() {
        var cadastrarNovo = "S";

        while(cadastrarNovo.equalsIgnoreCase("S")) {
            System.out.println("Digite o nome do artista:");
            var nomeArtista = sc.nextLine();
            System.out.println("Que tipo de artista é? (Solo, Dupla ou Banda?)");
            var tipo = sc.nextLine();
            TipoArtista tipoArtista = TipoArtista.fromTipoArtista(tipo);
            Artista artista = new Artista(nomeArtista, tipoArtista);
            repository.save(artista);

            System.out.println("Cadastrar novo artista? (S/N)");
            cadastrarNovo = sc.nextLine();
        }
    }

    private void cadastrarMusica() {
        System.out.println("Cadastrar música de qual artista?");
        var nomeArtista = sc.nextLine();
        Optional<Artista> buscaArtista = repository.findByNomeContainingIgnoreCase(nomeArtista);

        if(buscaArtista.isPresent()) {
            artista = buscaArtista.get();
            System.out.println("Digite o nome da música:");
            var nomeMusica = sc.nextLine();
            Musica musica = new Musica(artista, nomeMusica);
            artista.getMusicas().add(musica);
            repository.save(artista);

        } else {
            System.out.println("Artista não encontrado!");
        }
    }

    private void listarMusicas() {
        List<Artista> artistas = repository.findAll();
        artistas.forEach(a -> a.getMusicas().forEach(System.out::println));
    }

    private void musicasPorArtista() {
        System.out.println("Digite o nome do artista:");
        var artista = sc.nextLine();
        List<Musica> musicas = repository.buscaMusicaPorArtista(artista);
        musicas.forEach(System.out::println);
    }


    private void consultarArtista() {
        System.out.println("Digite qual artista:");
        String artista = sc.nextLine();
        var consulta = ConsultaChatGpt.consultar(artista);
        System.out.println(consulta.trim());
    }
}
