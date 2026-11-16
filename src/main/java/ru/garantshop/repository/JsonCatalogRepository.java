package ru.garantshop.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.garantshop.model.Category;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonCatalogRepository implements CatalogRepository {

    private static final String FILE_PATH = "catalog.json";
    private final ObjectMapper objectMapper;

    public JsonCatalogRepository() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }


    @Override
    public void save(List<Category> categories) {
        try {
            objectMapper.writeValue(new File(FILE_PATH), categories);
            System.out.println("✅ Каталог сохранён в файл: " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("❌ Ошибка при сохранении: " + e.getMessage());
        }
    }

    @Override
    public List<Category> load() {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            System.out.println("⚠️ Файл не найден. Создаём новый каталог.");
            return new ArrayList<>();
        }

        try {
            return objectMapper.readValue(file, new TypeReference<List<Category>>() {
            });
        } catch (IOException e) {
            System.err.println("❌ Ошибка при загрузке: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
