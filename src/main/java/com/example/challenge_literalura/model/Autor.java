package com.example.challenge_literalura.model;


import com.example.challenge_literalura.dto.DatosAutor;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "autores")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonAlias("name")
    @Column(name = "nombre")
    private String nombre;

    @JsonAlias("birth_year")
    @Column(name = "anio_nacimiento")
    private Integer anioNacimiento;

    @JsonAlias("death_year")
    @Column(name = "anio_fallecimiento")
    private Integer anioFallecimiento;

    @ManyToOne
    @JsonIgnore
    private Libro libro;

    public Autor() {
    }

    public Autor(DatosAutor d) {
        this.nombre = d.nombre();
        this.anioNacimiento = d.anioNacimiento();
        this.anioFallecimiento = d.anioFallecimiento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(Integer anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public Integer getAnioFallecimiento() {
        return anioFallecimiento;
    }

    public void setAnioFallecimiento(Integer anioFallecimiento) {
        this.anioFallecimiento = anioFallecimiento;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }


    @Override
    public String toString() {
        return "Autor: "+nombre +"\n"+
                "Fecha de Nacimiento: "+anioNacimiento+"\n"+
                "Fecha de fallecimiento: "+anioFallecimiento+"\n"+
                "Libros: "+getLibro().getTitulo();
    }
}


