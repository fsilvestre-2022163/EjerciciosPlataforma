package org.adaschool.api.controller.product;

import org.adaschool.api.exception.ProductNotFoundException;
import org.adaschool.api.repository.product.Product;
import org.adaschool.api.repository.product.ProductDto;
import org.adaschool.api.service.product.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/products/")
public class ProductsController {

    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        Product product = new Product(productDto);
        Product createdProduct = productsService.save(product);
        URI createdProductUri = URI.create("/v1/products/" + createdProduct.getId());
        return ResponseEntity.created(createdProductUri).body(createdProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productsService.all();
        return ResponseEntity.ok(products);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") String id) {
        return productsService.findById(id)
                .map(product -> ResponseEntity.ok(product))
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody ProductDto productDto) {
        Product existingProduct = productsService.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        existingProduct.update(productDto); // Actualiza el producto con la información del DTO
        Product updatedProduct = productsService.update(existingProduct, id); // Actualiza el producto en el servicio
        return ResponseEntity.ok(updatedProduct);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
        productsService.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productsService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
