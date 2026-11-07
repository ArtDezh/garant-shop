package ru.garantshop;

import ru.garantshop.model.Category;
import ru.garantshop.model.Product;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== КАТАЛОГ ГАРАНТ ===");

        // Создаём категорию
        Category injections = new Category(1L, "Колющие и инъекционные", "Шприцы, иглы, катетеры");

        // Добавляем товары
        injections.addProduct(new Product(1L, "Шприц 5 мл", "Одноразовый, 3-компонентный", 4.5, true));
        injections.addProduct(new Product(2L, "Шприц 10 мл", "Одноразовый, 3-компонентный", 5.2, true));
        injections.addProduct(new Product(3L, "Игла 21G", "0.8х40 мм, зеленая", 0.8, true));
        injections.addProduct(new Product(4L, "Игла 23G", "0.6х30 мм, синяя", 0.7, false));
        injections.addProduct(new Product(5L, "Катетер внутривенный", "18G, розовый", 12.5, true));
        injections.addProduct(new Product(6L, "Катетер внутривенный", "20G, желтый", 15.0, false));

        // === ТЕСТЫ НОВЫХ МЕТОДОВ ===
        System.out.println("--- Все товары ---");
        injections.getProducts().forEach(System.out::println);

        System.out.println("\n--- Товары в наличии ---");
        injections.getAvailableProducts().forEach(System.out::println);

        System.out.println("\n--- Товаров нет в наличии ---");
        injections.getOutOfStockProducts().forEach(System.out::println);

        System.out.println("\n--- Товары дешевле 5 рублей ---");
        injections.getProductsCheaperThan(5.0).forEach(System.out::println);

        System.out.println("\n--- Товары дороже 10 рублей ---");
        injections.getProductsMoreExpensiveThan(10.0).forEach(System.out::println);

        System.out.println("\n--- Товары отсортированные по цене ---");
        injections.getProductsSortedByPrice().forEach(System.out::println);

        System.out.println("\n--- Только названия товаров ---");
        injections.getProductNames().forEach(name -> System.out.println("  " + name));

        System.out.println("\n--- Статистика ---");
        System.out.println("Всего товаров: " + injections.getProductCount());
        System.out.println("В наличии: " + injections.countAvailableProducts());
        System.out.println("Нет в наличии: " + (injections.getProductCount() - injections.countAvailableProducts()));

        System.out.println("\n--- Самый дорогой товар ---");
        Product mostExpensive = injections.getMostExpensiveProduct();
        if (mostExpensive != null) {
            System.out.println(mostExpensive.getName() + " - " + mostExpensive.getPrice() + " руб.");
        }
    }
}