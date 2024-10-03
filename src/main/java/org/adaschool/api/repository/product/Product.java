package org.adaschool.api.repository.product;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private String id;  // Remover el final

    private String name;
    private String description;
    private String category;
    private List<String> tags;
    private double price;
    private String imageUrl;

    public Product() {
        this.id = "";
        this.name = "";
        this.description = "";
        this.category = "";
        this.tags = new ArrayList<>();
        this.price = 0.0;
        this.imageUrl = "";
    }

    public Product(String id, String name, String description, String category, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.tags = new ArrayList<>();
        this.imageUrl = "";
    }

    public Product(ProductDto productDto) {
        this.id = ""; // Puedes establecer un valor por defecto si es necesario
        this.name = productDto.getName();
        this.description = productDto.getDescription();
        this.category = productDto.getCategory();
        this.tags = productDto.getTags() != null ? productDto.getTags() : new ArrayList<>();
        this.price = productDto.getPrice();
        this.imageUrl = productDto.getImageUrl();
    }

    // Getters
    public String getId() {
        return id;
    }

    // Setter para id
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void update(ProductDto productDto) {
        this.name = productDto.getName();
        this.description = productDto.getDescription();
        this.category = productDto.getCategory();
        this.tags = productDto.getTags() != null ? productDto.getTags() : new ArrayList<>();
        this.price = productDto.getPrice();
        this.imageUrl = productDto.getImageUrl();
    }
}
