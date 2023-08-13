package com.example.homework27.converter;

import com.example.homework27.entity.Product;
import com.example.homework27.model.ProductJson;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter implements Converter<Product, ProductJson>{

    @Override
    public Product convertToEntity(ProductJson model, Product entity) {
        entity.setName(model.getName());
        entity.setPrice(model.getPrice());
        return entity;
    }

    @Override
    public ProductJson convertToModel(Product entity, ProductJson model) {
        model.setName(entity.getName());
        model.setPrice(entity.getPrice());
        return model;
    }
}
