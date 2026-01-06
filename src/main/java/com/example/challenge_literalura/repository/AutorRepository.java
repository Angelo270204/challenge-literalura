package com.example.challenge_literalura.repository;

import com.example.challenge_literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {
    @Query("select a from Autor a where a.anioNacimiento<=:anio and a.anioFallecimiento>:anio")
    List<Autor> findAutoresVivosEnAnio(Integer anio);
}
