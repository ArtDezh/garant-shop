package ru.garantshop.repository;

import ru.garantshop.model.Category;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCatalogRepository implements CatalogRepository {
    private List<Category> storage = new ArrayList<>();


    @Override
    public void save(List<Category> categories) {
        
    }

    @Override
    public List<Category> load() {
        return List.of();
    }
}
