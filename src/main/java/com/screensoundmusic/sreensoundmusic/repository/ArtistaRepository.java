package com.screensoundmusic.sreensoundmusic.repository;

import com.screensoundmusic.sreensoundmusic.model.Artista;
import com.screensoundmusic.sreensoundmusic.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    Optional<Artista> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT m from Artista a JOIN a.musicas m WHERE a.nome ILIKE %:nome%")
    List<Musica> buscaMusicaPorArtista(String nome);
}
