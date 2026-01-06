package com.example.challenge_literalura.service;

public interface IConvierteDatos {
    <T> T convertir(String json, Class<T> clase);
}
