package ru.garantshop.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.garantshop.model.Category;
import ru.garantshop.model.Product;

import javax.xml.catalog.Catalog;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    // Загрузить список категорий из файла
    public List<Category> loadCatalog() {

        File file = new File(FILE_PATH);

        // Если файл не существует — возвращаем пустой список
        if (!file.exists()) {
            System.out.println("⚠️ Файл не найден. Создаём новый каталог.");
            return new ArrayList<>();
        }

        try {
            List<Category> categories = objectMapper.readValue(file,
                    new TypeReference<List<Category>>() {});
            System.out.println("✅ Каталог загружен из файла. Категорий: " + categories.size());
            return categories;
        } catch (IOException e) {
            System.err.println("❌ Ошибка при загрузке каталога: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Найти категорию по ID
    public Category findCategoryById(List<Category> categories, Long id) {
        return categories.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Найти все товары по названию (поиск)
    public List<Product> searchProductsByName(List<Category> categories, String query) {
        String lowQuery = query.toLowerCase();

        return categories.stream()
                .flatMap(category -> category.getProducts().stream())
                .filter(product -> product.getName().toLowerCase().contains(lowQuery))
                .toList();
    }

    // Получить все товары из всех категорий
    public List<Product> getAllProducts(List<Category> categories) {
        return categories.stream()
                .flatMap(category -> category.getProducts().stream())
                .toList();
    }
}
