package ru.garantshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Category {
    private Long id;
    private String name;
    private String description;

    // Инициализируем сразу при объявлении
    private List<Product> products = new ArrayList<>();

    // @JsonIgnore — это поле НЕ будет сохраняться в JSON
    // Это внутренняя структура для быстрого поиска
    @JsonIgnore
    private Map<Long, Product> productMap = new HashMap<>();

    // ✅ Конструктор по умолчанию — ОБЯЗАТЕЛЬНО для Jackson
    public Category() {
    }

    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public void addProduct(Product product) {
        products.add(product);
        productMap.put(product.getId(), product);
    }

    public Product getProductById(Long id) {
        // Если productMap пуст (например, после загрузки из JSON),
        // ищем в списке products
        if (productMap.isEmpty() && !products.isEmpty()) {
            rebuildProductMap();
        }
        return productMap.get(id);
    }

    private void rebuildProductMap() {
        productMap.clear();
        for (Product product : products) {
            productMap.put(product.getId(), product);
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    // ✅ Сеттер для Jackson
    public void setProducts(List<Product> products) {
        this.products = products != null ? new ArrayList<>(products) : new ArrayList<>();
    }

    public int getProductCount() {
        return products.size();
    }

    public boolean hasProduct(Long id) {
        if (productMap.isEmpty() && !products.isEmpty()) {
            rebuildProductMap();
        }
        return productMap.containsKey(id);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Найти все товары, которые есть в наличии
    @JsonIgnore
    public List<Product> getAvailableProducts() {
        return products.stream()
                .filter(Product::isInStock)
                .toList();
    }

    // Найти все товары, которых нет в наличии
    @JsonIgnore
    public List<Product> getOutOfStockProducts() {
        return products.stream()
                .filter(p -> !p.isInStock())
                .toList();
    }

    // Найти все товары дешевле указанной цены
    @JsonIgnore
    public List<Product> getProductsCheaperThan(double price) {
        return products.stream()
                .filter(p -> p.getPrice() < price)
                .toList();
    }

    // Найти все товары дороже указанной цены
    @JsonIgnore
    public List<Product> getProductsMoreExpensiveThan(double price) {
        return products.stream()
                .filter(p -> p.getPrice() > price)
                .toList();
    }

    // Отсортировать товары по цене (от дешёвого к дорогому)
    @JsonIgnore
    public List<Product> getProductsSortedByPrice() {
        return products.stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .toList();
    }

    // Получить только названия товаров
    @JsonIgnore
    public List<String> getProductNames() {
        return products.stream()
                .map(Product::getName)
                .toList();
    }

    // Посчитать количество товаров в наличии
    @JsonIgnore
    public long countAvailableProducts() {
        return products.stream()
                .filter(Product::isInStock)
                .count();
    }

    // Найти самый дешёвый товар
    @JsonIgnore
    public Product getCheapestProduct() {
        return products.stream()
                .min(Comparator.comparing(Product::getPrice))
                .orElse(null);
    }

    // Найти самый дорогой товар
    @JsonIgnore
    public Product getMostExpensiveProduct() {
        return products.stream()
                .max(Comparator.comparing(Product::getPrice))
                .orElse(null);
    }

    @Override
    public String toString() {
        return "Category{id=" + id + ", name='" + name + "', products=" + products.size() + "}";
    }
}