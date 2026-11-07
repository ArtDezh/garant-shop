package ru.garantshop;

import ru.garantshop.model.Category;
import ru.garantshop.model.Product;
import ru.garantshop.service.CatalogService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        CatalogService service = new CatalogService();

        System.out.println("=== КАТАЛОГ ГАРАНТ ===");

        // Пытаемся загрузить каталог из файла
        List<Category> categories = service.loadCatalog();

        // Если файл пустой или не существует — создаём данные
        if (categories.isEmpty()) {
            System.out.println("Создаём начальные данные...\n");
            categories = createInitialData();

            // Сохраняем в файл
            service.saveCatalog(categories);
        }

        // === РАБОТАЕМ С ДАННЫМИ ===

        System.out.println("\n--- Все категории ---");
        for (Category cat : categories) {
            System.out.println(cat);
        }

        // Поиск товаров по названию
        System.out.println("\n--- Поиск: товары со словом 'Шприц' ---");
        List<Product> found = service.searchProductsByName(categories, "Шприц");
        found.forEach(p -> System.out.println("  " + p));

        // Все товары в наличии
        System.out.println("\n--- Все товары в наличии ---");
        List<Product> available = service.getAllAvailableProducts(categories);
        available.forEach(p -> System.out.println("  " + p));

        System.out.println("\n✅ Работа завершена!");
    }

    // Создаём начальные данные для каталога
    private static List<Category> createInitialData() {
        List<Category> categories = new ArrayList<>();

        // Категория 1: Колющие и инъекционные
        Category injections = new Category(1L, "Колющие и инъекционные", "Шприцы, иглы, катетеры");
        injections.addProduct(new Product(1L, "Шприц 5 мл", "Одноразовый, 3-компонентный", 4.5, true));
        injections.addProduct(new Product(2L, "Шприц 10 мл", "Одноразовый, 3-компонентный", 5.2, true));
        injections.addProduct(new Product(3L, "Игла 21G", "0.8х40 мм, зеленая", 0.8, true));
        injections.addProduct(new Product(4L, "Игла 23G", "0.6х30 мм, синяя", 0.7, false));
        injections.addProduct(new Product(5L, "Катетер внутривенный", "18G, розовый", 12.5, true));

        // Категория 2: Перчатки
        Category gloves = new Category(2L, "Перчатки", "Смотровые и хирургические");
        gloves.addProduct(new Product(6L, "Перчатки нитриловые M", "Неопудренные, 100 шт", 890.0, true));
        gloves.addProduct(new Product(7L, "Перчатки латексные L", "Опудренные, 100 шт", 650.0, true));
        gloves.addProduct(new Product(8L, "Перчатки хирургические", "Стерильные, пара", 45.0, false));

        // Категория 3: Пластыри
        Category plasters = new Category(3L, "Пластыри и повязки", "Бактерицидные, катетерные");
        plasters.addProduct(new Product(9L, "Пластырь бактерицидный", "Набор 20 шт", 85.0, true));
        plasters.addProduct(new Product(10L, "Пластырь рулонный", "5м x 2.5см", 120.0, true));

        categories.add(injections);
        categories.add(gloves);
        categories.add(plasters);

        return categories;
    }
}