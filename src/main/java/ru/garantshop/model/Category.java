package ru.garantshop.model;

import java.util.*;

public class Category {
    private Long id;
    private String name;
    private String description;

    // Инициализируем сразу при объявлении
    private List<Product> products = new ArrayList<>();
    private Map<Long, Product> productMap = new HashMap<>();

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
        return productMap.get(id);
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getProductCount() {
        return products.size();
    }

    public boolean hasProduct(Long id) {
        return productMap.containsKey(id);
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }

    // Найти все товары, которые есть в наличии
    public List<Product> getAvailableProducts() {
        return products.stream()
                .filter(Product::isInStock)
                .toList();
    }

    // Найти все товары, которых нет в наличии
    public List<Product> getOutOfStockProducts() {
        return products.stream()
                .filter(p -> !p.isInStock())
                .toList();
    }

    // Найти все товары дешевле указанной цены
    public List<Product> getProductsCheaperThan(double price) {
        return products.stream()
                .filter(p -> p.getPrice() < price)
                .toList();
    }

    // Найти все товары дороже указанной цены
    public List<Product> getProductsMoreExpensiveThan(double price) {
        return products.stream()
                .filter(p -> p.getPrice() > price)
                .toList();
    }

    // Отсортировать товары по цене (от дешёвого к дорогому)
    public List<Product> getProductsSortedByPrice() {
        return products.stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .toList();
    }

    // Получить только названия товаров
    public List<String> getProductNames() {
        return products.stream()
                .map(Product::getName)
                .toList();
    }

    // Посчитать количество товаров в наличии
    public long countAvailableProducts() {
        return products.stream()
                .filter(Product::isInStock)
                .count();
    }

    // Найти самый дешёвый товар
    public Product getCheapestProduct() {
        return products.stream()
                .min(Comparator.comparing(Product::getPrice))
                .orElse(null);
    }

    // Найти самый дорогой товар
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