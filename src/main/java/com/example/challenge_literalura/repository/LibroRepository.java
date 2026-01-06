package com.example.challenge_literalura.repository;

import com.example.challenge_literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {
    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    List<Libro> findByLenguaje(String lenguaje);
}
