package com.example.homework27.service;

import com.example.homework27.converter.ProductConverter;
import com.example.homework27.entity.Product;
import com.example.homework27.excertion.ServiceException;
import com.example.homework27.model.ProductJson;
import com.example.homework27.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
    }
    @Transactional
    public Product addProduct(ProductJson productJson){
        Product product = productConverter.convertToEntity(productJson,new Product());
        return productRepository.save(product);
    }
    public Product getProduct(long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new ServiceException("Product(id="+id+") does not exists");
        }
        return product.get();
    }
    public Product getProductReference(long id){
        return productRepository.getReferenceById(id);
    }
}
