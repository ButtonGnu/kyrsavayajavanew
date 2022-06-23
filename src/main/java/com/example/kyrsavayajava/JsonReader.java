package com.example.kyrsavayajava;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class JsonReader {

    public Damage[] readJson(String fileName) {
        try (InputStream resourceAsStream = JsonReader.class.getResourceAsStream("/" + fileName)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(resourceAsStream, Damage[].class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
