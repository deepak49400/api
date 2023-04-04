package com.bazar.services;

import com.bazar.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
  public List<Product> getAllProduct();
  public void addProduct(Product product);

  public void deleteProductById(Long id);

  public Optional<Product> getProductbyId(Long id);
  public List<Product> getAllProductByCategoryId(int id);

}
