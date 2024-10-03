package org.adaschool.api.service.product;

import org.adaschool.api.repository.product.Product;
import org.adaschool.api.repository.product.ProductDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class ProductsServiceMap implements ProductsService {

    private final ConcurrentMap<String, Product> productStore = new ConcurrentHashMap<>();

    @Override
    public Product save(Product product) {
        // Save or update the product
        productStore.put(product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> findById(String id) {
        // Find a product by its ID
        return Optional.ofNullable(productStore.get(id));
    }

    @Override
    public List<Product> all() {
        // Return all products
        return new ArrayList<>(productStore.values());
    }

    @Override
    public void deleteById(String id) {
        // Delete a product by its ID
        productStore.remove(id);
    }

    @Override
    public Product update(Product product, String productId) {
        if (product == null || productId == null) {
            return null;
        }
        if (productStore.containsKey(productId)) {
            product.setId(productId);
            return save(product);
        }
        return null;
    }

}
