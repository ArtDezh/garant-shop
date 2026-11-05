package ru.garantshop.model;

public class Product {
    private Long id;
    private String name;
    private String description;
    private double price;
    private boolean inStock;

    public Product(Long id, String name, String description, double price, boolean inStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.inStock = inStock;
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

    public double getPrice() {
        return price;
    }

    public boolean isInStock() {
        return inStock;
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + ", inStock=" + inStock + "}";
    }
}
