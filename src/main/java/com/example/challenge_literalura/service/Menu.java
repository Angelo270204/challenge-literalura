package com.example.challenge_literalura.service;

public class Menu {
     public static void exhibirMenu(){
        String interfaz = """
                Bienvenido a la app de Literalura!!!
                Por favor selecciona una de las siguientes opciones:
                1 - Buscar libro por titulo
                2 - Listar libros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos en un determinado anio
                5 - Listar libros por idioma
                0 - Salir
                """;

        System.out.println(interfaz);
    }
}
