package ru.garantshop.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.garantshop.model.Category;

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
        
    }

    @Override
    public List<Category> load() {
        return List.of();
    }
}
