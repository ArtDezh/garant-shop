package ru.garantshop.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.garantshop.model.Category;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JasonCatalogRepository implements CatalogRepository {

    private static final String FILE_PATH = "catalog.json";
    private final ObjectMapper objectMapper;

    public JasonCatalogRepository() {
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
        return List.of();
    }
}
