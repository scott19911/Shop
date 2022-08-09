package com.epam.verizhenko_andrii.electronicsStore.service;
import com.epam.verizhenko_andrii.electronicsStore.DAO.ProductsDao;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;

import java.util.Map;

public class ProductsService {
    ProductsDao productsDao;

    public ProductsService(ProductsDao productsDao) {
        this.productsDao = productsDao;
    }
    public Map<Product, Integer> getProducts() {
        return productsDao.getProducts();
    }
    public void setProducts(Map<Product, Integer> productsMap) {
        productsDao.setProducts(productsMap);
    }
    public void updateProducts(Product product, int quantity){
        productsDao.updateProducts(product,quantity);
    }
    public Product getProduct(String brand){return productsDao.getProduct(brand);}
    public int getQuantity(Product product) {
        return productsDao.getQuantity(product);
    }
    public ProductsDao getProductsDao(){
        return productsDao;
    }

}
