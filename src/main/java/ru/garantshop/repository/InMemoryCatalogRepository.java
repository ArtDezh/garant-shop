package ru.garantshop.repository;

import ru.garantshop.model.Category;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCatalogRepository implements CatalogRepository {

    // Данные хранятся в памяти
    private List<Category> storage = new ArrayList<>();


    @Override
    public void save(List<Category> categories) {
        this.storage = new ArrayList<>(categories);
        System.out.println("✅ Каталог сохранён в память. Категорий: " + storage.size());
    }

    @Override
    public List<Category> load() {
        System.out.println("✅ Каталог загружен из памяти. Категорий: " + storage.size());
        return new ArrayList<>(storage);  // возвращаем копию для безопасности
    }
}
