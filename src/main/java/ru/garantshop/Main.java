package ru.garantshop;

import ru.garantshop.model.Category;
import ru.garantshop.model.Product;

public class Main {
    public static void main(String[] args) {
        Category injections = new Category(1L, "Колющие и инъекционные", "Шприцы, иглы, катетеры");
        Category gloves = new Category(2L, "Перчатки", "Смотровые и хирургические");

        injections.addProduct(new Product(1L, "Шприц 5 мл", "Одноразовый, 3-компонентный", 4.5, true));
        injections.addProduct(new Product(2L, "Игла 21G", "0.8x40 мм, зеленая", 0.8, true));

        gloves.addProduct(new Product(3L, "Перчатки нитриловые", "Размер М, неопудренные", 8.9, false));

        System.out.println("=== КАТАЛОГ ГАРАНТ ===");
        System.out.println(injections);
        System.out.println("Товары: ");
        for (Product p: injections.getProducts()) {
            System.out.println("   " + p);
        }

    }
}