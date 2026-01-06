package com.example.challenge_literalura;

import com.example.challenge_literalura.dto.Results;
import com.example.challenge_literalura.model.Libro;
import com.example.challenge_literalura.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ChallengeLiteraluraApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ChallengeLiteraluraApplication.class, args);
    }

    private final LibroService libroService;
    private final AutorService autorService;

    public ChallengeLiteraluraApplication(LibroService libroService, AutorService autorService) {
        this.libroService = libroService;
        this.autorService = autorService;
    }

    private final Scanner scan = new Scanner(System.in);
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();

    @Override
    public void run(String... args) throws Exception {
        int opcion = -1;

        while (opcion != 0) {
            Menu.exhibirMenu();
            try {
                opcion = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Por favor ingrese un numero valido\n");
                scan.nextLine();
                opcion = -1;
            }
            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosBuscados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosDeterminadoAnio();
                    break;
                case 5:
                    cantidadDeLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida, vuelva a intentarlo");
                    break;
            }
        }
    }

    public void buscarLibroPorTitulo() {
        System.out.println("Ingrese el libro que desea buscar: ");
        String titulo = scan.nextLine();
        var libroPorTitulo = libroService.buscarPorTitulo(titulo);
        if (!libroPorTitulo.isEmpty()) {
            System.out.println("El libro ya esta registrado en la base de datos");
            return;
        }

        final String URL_BASE = "https://gutendex.com/books";
        String json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + titulo.replace(" ", "+"));
        var results = conversor.convertir(json, Results.class);
        var libroEncontrado = results.libros().stream()
                .filter(l -> l.titulo().toLowerCase().contains(titulo.toLowerCase()))
                .map(Libro::new)
                .findFirst();

        if (libroEncontrado.isPresent()) {
            System.out.println("Libro agregado a la bd");
            System.out.println(libroEncontrado.get());
            libroService.agregarLibro(libroEncontrado.get());
        } else {
            System.out.println("Libro no encontrado");
        }

    }

    public void listarLibrosBuscados() {
        List<Libro> librosBuscados = libroService.obtenerTodosLosLibros();
        if (librosBuscados.isEmpty()) {
            System.out.println("No hay libros registrados aun");
            return;
        }
        librosBuscados.forEach(System.out::println);
    }

    public void listarAutoresRegistrados() {
        List<String> autoresAgrupados = autorService.obtenerAutoresConLibrosAgrupados();
        if (autoresAgrupados.isEmpty()) {
            System.out.println("No hay autores registrados aun");
            return;
        }

        autoresAgrupados.forEach(System.out::println);
    }

    public void listarAutoresVivosDeterminadoAnio() {
        List<Libro> librosBuscados = libroService.obtenerTodosLosLibros();
        if (librosBuscados.isEmpty()) {
            System.out.println("Aun no hay autores registrados por mostrar");
            return;
        }
        System.out.println("Ingrese el anio en el que desea buscar: ");
        try {
            int anio = scan.nextInt();
            scan.nextLine();
            var librosAniosAnteriores = autorService.autoresVivosDeterminadoAnio(anio);
            if (librosAniosAnteriores.isEmpty()) {
                System.out.println("El arreglo esta vacio");
            } else {
                librosAniosAnteriores.forEach(System.out::println);
            }
        } catch (InputMismatchException e) {
            System.out.println("Por favor ingrese un numero valido");
        }
    }

    public void cantidadDeLibrosPorIdioma() {
        System.out.println("Ingrese el idioma para buscar los libros");
        System.out.println("es - Espaniol");
        System.out.println("en - ingles");
        String idioma = scan.nextLine();
        System.out.println("Resultados de la busqueda:");
        if (idioma.equals("es") || idioma.equals("en")) {
            List<Libro> librosEncontrados = libroService.buscarLibrosPorIdioma(idioma);
            if (!librosEncontrados.isEmpty()) {
                librosEncontrados.forEach(System.out::println);
            } else {
                System.out.println("No hay libros por mostrar");
            }
        } else {
            System.out.println("Opcion invalida");
        }
    }
}
