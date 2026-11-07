package ru.garantshop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.garantshop.model.Category;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CatalogService {

    private static final String FILE_PATH = "catalog.json";

    // ObjectMapper — главный инструмент для работы с JSON
    private final ObjectMapper objectMapper;

    public CatalogService() {
        this.objectMapper = new ObjectMapper();
        // Включаем красивое форматирование (чтобы файл был читабельным)
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    // Сохранить список категорий в файл
    public void saveCatalog(List<Category> categories) {
        try {
            objectMapper.writeValue(new File(FILE_PATH), categories);
            System.out.println("✅ Каталог сохранён в файл: " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("❌ Ошибка при сохранении каталога: " + e.getMessage());
        }
    }
}
