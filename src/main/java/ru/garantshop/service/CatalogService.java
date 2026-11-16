package ru.garantshop.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.garantshop.model.Category;
import ru.garantshop.model.Product;
import ru.garantshop.repository.CatalogRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CatalogService {

    private static final String FILE_PATH = "catalog.json";

    // Используем ИНТЕРФЕЙС, а не конкретную реализацию
    private final CatalogRepository repository;

    public CatalogService(CatalogRepository repository) {
        this.repository = repository;
    }

    // Сохранить список категорий в файл
    public void saveCatalog(List<Category> categories) {
        repository.save(categories);
    }

    // Загрузить список категорий из файла
    public List<Category> loadCatalog() {
        return repository.load();
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

    // Получить все товары, которые есть в наличии (из всех категорий)
    public List<Product> getAllAvailableProducts(List<Category> categories) {
        return categories.stream()
                .flatMap(category -> category.getProducts().stream())
                .filter(Product::isInStock)
                .toList();
    }
}
