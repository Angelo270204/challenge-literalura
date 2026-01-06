package com.example.challenge_literalura.model;

import com.example.challenge_literalura.dto.DatosLibro;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "lenguaje")
    private String lenguaje;

    @Column(name = "numero_descargas")
    private Integer numeroDescargas;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "libro", cascade = CascadeType.ALL)
    private List<Autor> autores;

    public Libro() {
    }

    public Libro(DatosLibro d) {
        this.titulo = d.titulo();
        this.lenguaje = d.lenguajes().get(0);
        this.numeroDescargas = d.numeroDescargas();
        this.autores = d.autores().stream()
                .map(datosAutor -> {
                    Autor autor = new Autor(datosAutor);
                    autor.setLibro(this);
                    return autor;
                })
                .toList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        autores.forEach(e -> e.setLibro(this));
        this.autores = autores;
    }

    @Override
    public String toString() {
        return """
                ========== LIBRO ============
                Titulo: %s
                Autor: %s
                Idioma: %s
                Numero de descargas: %d
                =============================
                """.formatted(titulo, autores.get(0).getNombre(), lenguaje, numeroDescargas);
    }
}
