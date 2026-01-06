package com.example.challenge_literalura.service;

import tools.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos{
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T convertir(String json, Class<T> clase) {
        try{
            return mapper.readValue(json,clase);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
