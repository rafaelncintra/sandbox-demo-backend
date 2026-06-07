package com.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private static final List<Product> PRODUCTS = List.of(
        new Product(1L, "Laptop Pro 15",       "Electronics", 2499.99, 12),
        new Product(2L, "Wireless Mouse",       "Electronics",   49.99, 87),
        new Product(3L, "Standing Desk",        "Furniture",    799.00,  5),
        new Product(4L, "Ergonomic Chair",      "Furniture",    599.00, 18),
        new Product(5L, "USB-C Hub",            "Electronics",   89.99, 43),
        new Product(6L, "Bookshelf 5-tier",     "Furniture",    149.00,  9),
        new Product(7L, "Mechanical Keyboard",  "Electronics",  129.99, 31),
        new Product(8L, "Monitor 27\"",         "Electronics",  449.00,  7)
    );

    @GetMapping
    public List<Product> list(@RequestParam Optional<String> category) {
        return category
            .map(c -> PRODUCTS.stream().filter(p -> p.category().equalsIgnoreCase(c)).toList())
            .orElse(PRODUCTS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return PRODUCTS.stream()
            .filter(p -> p.id().equals(id))
            .findFirst()
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
