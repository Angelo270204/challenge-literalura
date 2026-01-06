package com.example.challenge_literalura.service;

import com.example.challenge_literalura.model.Libro;
import com.example.challenge_literalura.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {
    private final LibroRepository repositorio;

    public LibroService(LibroRepository repositorio){
        this.repositorio=repositorio;
    }

    public List<Libro> obtenerTodosLosLibros(){
        return repositorio.findAll();
    }

    public Libro agregarLibro(Libro libro){
        return repositorio.save(libro);
    }

    public List<Libro> buscarPorTitulo(String titulo){
        return repositorio.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Libro> buscarLibrosPorIdioma(String idioma){
        return repositorio.findByLenguaje(idioma);
    }
}
