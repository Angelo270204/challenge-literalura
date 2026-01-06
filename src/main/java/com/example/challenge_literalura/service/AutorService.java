package com.example.challenge_literalura.service;

import com.example.challenge_literalura.model.Autor;
import com.example.challenge_literalura.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {
    private final AutorRepository repositorio;

    public AutorService(AutorRepository repositorio){
        this.repositorio=repositorio;
    }

    public List<Autor> autoresVivosDeterminadoAnio(Integer anio){
        return repositorio.findAutoresVivosEnAnio(anio);
    }


    public List<String> obtenerAutoresConLibrosAgrupados() {
        List<Autor> todosLosAutores = repositorio.findAll();

        return todosLosAutores.stream()
                .collect(Collectors.groupingBy(Autor::getNombre))
                .entrySet()
                .stream()
                .map(entry -> {
                    String nombre = entry.getKey();
                    List<Autor> autoresConMismoNombre = entry.getValue();

                    Autor autor = autoresConMismoNombre.get(0);

                    List<String> libros = autoresConMismoNombre.stream()
                            .map(a -> a.getLibro().getTitulo())
                            .distinct()
                            .toList();

                    return String.format(
                            "Autor: %s\nFecha de nacimiento: %s\nFecha de fallecimiento: %s\nLibros: %s\n",
                            nombre,
                            autor.getAnioNacimiento(),
                            autor.getAnioFallecimiento(),
                            libros
                    );
                })
                .toList();
    }

}
