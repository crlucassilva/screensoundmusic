package com.screensoundmusic.sreensoundmusic.model;

public enum TipoArtista {

    SOLO("SOLO"),
    DUPLA("Dupla"),
    BANDA("Banda");

    private String tipoArtista;

    TipoArtista(String tipoArtista) {
        this.tipoArtista = tipoArtista;
    }

    public static TipoArtista fromTipoArtista(String text) {
        for (TipoArtista tipo : TipoArtista.values()) {
            if (tipo.tipoArtista.equalsIgnoreCase(text)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Nenhum tipo de artista encontrado para a string fornecida!");
    }
}
