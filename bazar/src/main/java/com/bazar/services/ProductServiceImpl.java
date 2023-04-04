package com.bazar.services;

import com.bazar.model.Product;
import com.bazar.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> getProductbyId(Long id) {
        Optional<Product> productId = productRepository.findById(id);
        return productId;
    }

    @Override
    public List<Product> getAllProductByCategoryId(int id) {
        List<Product> allProduct = productRepository.findAllByCategory_Id((long) id);
        return allProduct;
    }


}
