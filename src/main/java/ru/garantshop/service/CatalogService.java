package ru.garantshop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CatalogService {

    private static final String FILE_PATH = "catalog.json";

    // ObjectMapper — главный инструмент для работы с JSON
    private final ObjectMapper objectMapper;

    public CatalogService() {
        this.objectMapper = new ObjectMapper();
        // Включаем красивое форматирование (чтобы файл был читабельным)
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    
}
