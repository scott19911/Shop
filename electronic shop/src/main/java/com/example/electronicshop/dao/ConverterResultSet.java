package com.example.electronicshop.dao;

import com.example.electronicshop.products.CategoryDTO;
import com.example.electronicshop.products.Product;
import com.example.electronicshop.users.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.electronicshop.dao.MySqlUserDao.AVATAR_URL;
import static com.example.electronicshop.dao.MySqlUserDao.EMAIL;
import static com.example.electronicshop.dao.MySqlUserDao.FIRST_NAME;
import static com.example.electronicshop.dao.MySqlUserDao.LAST_NAME;
import static com.example.electronicshop.dao.MySqlUserDao.PASSWORD;
import static com.example.electronicshop.dao.MySqlUserDao.RECEIVE_MAILING;
import static com.example.electronicshop.dao.MySqlUserDao.SALT;
import static com.example.electronicshop.dao.MySqlUserDao.USER_ID;
import static com.example.electronicshop.dao.ProductRepositoryImpl.CATEGORY_ID;
import static com.example.electronicshop.dao.ProductRepositoryImpl.CATEGORY_NAME;
import static com.example.electronicshop.dao.ProductRepositoryImpl.PRODUCT_BRAND;
import static com.example.electronicshop.dao.ProductRepositoryImpl.PRODUCT_CATEGORY;
import static com.example.electronicshop.dao.ProductRepositoryImpl.PRODUCT_DESCRIPTION;
import static com.example.electronicshop.dao.ProductRepositoryImpl.PRODUCT_ID;
import static com.example.electronicshop.dao.ProductRepositoryImpl.PRODUCT_IMAGE;
import static com.example.electronicshop.dao.ProductRepositoryImpl.PRODUCT_NAME;
import static com.example.electronicshop.dao.ProductRepositoryImpl.PRODUCT_PRICE;

/**
 * The class allows you to retrieve the user from ResultSet
 */
public class ConverterResultSet {
    /**
     * allows you to retrieve the list of user from ResultSet
     *
     * @param resultSet - entered data
     * @return - list of user
     */
    public List<User> getListUser(ResultSet resultSet) {
        List<User> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(USER_ID));
                user.setMailing(resultSet.getBoolean(RECEIVE_MAILING));
                user.setEmail(resultSet.getString(EMAIL));
                user.setLastName(resultSet.getString(LAST_NAME));
                user.setFirstName(resultSet.getString(FIRST_NAME));
                user.setAvatarUdl(resultSet.getString(AVATAR_URL));
                list.add(user);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Sorry, cannot load list of users");
        }
        return list;
    }

    /**
     * allows you to retrieve the user from ResultSet
     *
     * @param resultSet - entered data
     * @return - user
     */
    public User getUser(ResultSet resultSet) {
        User user = new User();
        try {
            if (resultSet.next()) {
                user.setPassword(resultSet.getString(PASSWORD));
                user.setSalt(resultSet.getString(SALT));
                user.setEmail(resultSet.getString(EMAIL));
                user.setLastName(resultSet.getString(LAST_NAME));
                user.setFirstName(resultSet.getString(FIRST_NAME));
                user.setId(resultSet.getInt(USER_ID));
                user.setMailing(resultSet.getBoolean(RECEIVE_MAILING));
                user.setAvatarUdl(resultSet.getString(AVATAR_URL));
                return user;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    public List<Product> getAllProduct(ResultSet resultSet) {
        List<Product> productList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt(PRODUCT_ID));
                product.setName(resultSet.getString(PRODUCT_NAME));
                product.setBrand(resultSet.getString(PRODUCT_BRAND));
                product.setCategory(resultSet.getInt(PRODUCT_CATEGORY));
                product.setPrice(resultSet.getInt(PRODUCT_PRICE));
                product.setDescription(resultSet.getString(PRODUCT_DESCRIPTION));
                product.setImgUrl(resultSet.getString(PRODUCT_IMAGE));
                productList.add(product);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return productList;
    }

    public List<String> getProductBrand(ResultSet resultSet) {
        List<String> brandList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                brandList.add(resultSet.getString(PRODUCT_BRAND));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return brandList;
    }

    public List<CategoryDTO> getCategory(ResultSet resultSet) {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setCategoryId(resultSet.getInt(CATEGORY_ID));
                categoryDTO.setCategoryName(resultSet.getString(CATEGORY_NAME));
                categoryDTOList.add(categoryDTO);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return categoryDTOList;
    }
}
