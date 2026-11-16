package ru.garantshop.repository;

import ru.garantshop.model.Category;

import java.util.List;

public interface CatalogRepository {

    /**
     * Сохранить список категорий
     */
    void save(List<Category> categories);

    /**
     * Загрузить список категорий
     */
    List<Category> load();
}
