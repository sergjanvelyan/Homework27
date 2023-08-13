package com.example.homework27.controller;

import com.example.homework27.converter.ProductConverter;
import com.example.homework27.entity.Product;
import com.example.homework27.model.ProductJson;
import com.example.homework27.model.Status;
import com.example.homework27.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductRestController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    @Autowired
    public ProductRestController(ProductService productService, ProductConverter productConverter) {
        this.productService = productService;
        this.productConverter = productConverter;
    }

    @PostMapping(value = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Status addProduct(@RequestBody ProductJson productJson) {
        Product product = productService.addProduct(productJson);
        return new Status("Product(id=" + product.getId() + ") created");
    }
    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductJson getProduct(@PathVariable long id){
        Product product = productService.getProduct(id);
        return productConverter.convertToModel(product,new ProductJson());
    }
}
