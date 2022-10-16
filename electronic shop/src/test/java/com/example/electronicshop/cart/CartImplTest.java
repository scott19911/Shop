package com.example.electronicshop.cart;

import com.example.electronicshop.dao.CartRepositoryImpl;
import com.example.electronicshop.dao.TransactionManager;
import com.example.electronicshop.products.CategoryDTO;
import com.example.electronicshop.products.Product;
import com.example.electronicshop.products.ProductFilter;
import com.example.electronicshop.utils.ConnectionPool;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.electronicshop.cart.CartImpl.ID;
import static com.example.electronicshop.cart.CartImpl.PRICE;
import static com.example.electronicshop.cart.CartImpl.QUANTITY;
import static com.example.electronicshop.dao.ProductRepositoryImpl.ORDER_DESC;
import static com.example.electronicshop.servlets.CartServlets.ADD_TO_CART;
import static com.example.electronicshop.servlets.CartServlets.COMMAND_DELETE_PRODUCT;
import static com.example.electronicshop.utils.ConnectionPool.CONNECTION_THREAD_LOCAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CartImplTest {
    public HttpServletRequest request = mock(HttpServletRequest.class);
    public ConnectionPool connectionPool;
    public Product product1;
    public Product product2;
    public Product product3;
    public Product product4;
    public Product product5;
    public Product product6;
    public CategoryDTO categoryDTO1;
    public CategoryDTO categoryDTO2;
    public CategoryDTO categoryDTO3;
    public CategoryDTO categoryDTO4;
    public List<CategoryDTO> categoryDTOList;
    public List<String> brandList = new ArrayList<>();
    public CartImpl cart = new CartImpl(new CartRepositoryImpl(), new TransactionManager());

    @BeforeEach
    void init() throws SQLException {
        connectionPool = mock(ConnectionPool.class);
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test", "root", "1991");
        CONNECTION_THREAD_LOCAL.set(connection);
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS `products`;");
        stmt.executeUpdate("DROP TABLE IF EXISTS `category`;");
        stmt.executeUpdate("CREATE TABLE `category` (\n" +
                "  `idcategory` int NOT NULL AUTO_INCREMENT,\n" +
                "  `categoryType` varchar(45) NOT NULL,\n" +
                "  PRIMARY KEY (`idcategory`)\n" +
                ") ");
        stmt.executeUpdate("INSERT INTO `category` VALUES (1,'flagship'),(2,'business'),(3,'budget')," +
                "(4,'ultra_budget');");

        stmt.executeUpdate("CREATE TABLE `products` (\n" +
                "  `idproducts` int NOT NULL AUTO_INCREMENT,\n" +
                "  `name` varchar(240) NOT NULL,\n" +
                "  `brand` varchar(240) NOT NULL,\n" +
                "  `price` int NOT NULL,\n" +
                "  `category` int NOT NULL,\n" +
                "  `imgUrl` varchar(250) NOT NULL,\n" +
                "  `description` varchar(250) NOT NULL,\n" +
                "  `quantity` int NOT NULL DEFAULT '0',\n" +
                "  PRIMARY KEY (`idproducts`),\n" +
                "  KEY `category_idx` (`category`),\n" +
                "  CONSTRAINT `category` FOREIGN KEY (`category`) REFERENCES `category` (`idcategory`)\n" +
                ")");
        stmt.executeUpdate("INSERT INTO `products` VALUES (1,'iPhone 13 Pro Max 1TB Graphite (MLLK3HU/A)','APPLE',69999,1," +
                "'uploadDir/img/iphone_13_pro_max.jpg','Characteristics',0)," +
                "(2,'iPhone 13 Pro Max 512GB Sierra Blue (MLLJ3HU/A)','APPLE',61999,1," +
                "'uploadDir/img/img_0_60_8353_2_1.jpg','Characteristics',0)," +
                "(3,'Galaxy Fold 3 12/512 Gb Dual Sim Phantom Silver (SM-F926BZSGSEK)'," +
                "'SAMSUNG',59999,2,'uploadDir/img/img_0_60_8162_7_1.jpg','Characteristics',0)," +
                "(4,'Redmi Note 10 Pro 6/128 Onyx gray (M2101K6G)','XIAOMI',11999,3," +
                "'uploadDir/img/img_0_60_7928_0.jpg','Characteristics',0)," +
                "(5,'Galaxy S22 8/128 Gb Dual Sim Phantom Green (SM-S901BZGDSEK)'," +
                "'SAMSUNG',33199,4,'uploadDir/img/img_0_60_8574_0_1_637798486657836804.jpg','Characteristics',0)," +
                "(6,'ZenFone 8 16/256GB Dual Sim Black (ZS590KS-2A011EU)','ASUS',31444,2,'uploadDir/img/img_0_60_8053_0.jpg','Characteristics',0)");

        product1 = inputProduct("iPhone 13 Pro Max 1TB Graphite (MLLK3HU/A)", "APPLE",
                "uploadDir/img/iphone_13_pro_max.jpg", "Characteristics", 1, 1, 69999);
        product2 = inputProduct("iPhone 13 Pro Max 512GB Sierra Blue (MLLJ3HU/A)", "APPLE",
                "uploadDir/img/img_0_60_8353_2_1.jpg", "Characteristics", 2, 1, 61999);
        product3 = inputProduct("Galaxy Fold 3 12/512 Gb Dual Sim Phantom Silver (SM-F926BZSGSEK)", "SAMSUNG",
                "uploadDir/img/img_0_60_8162_7_1.jpg", "Characteristics", 3, 2, 59999);
        product5 = inputProduct("Galaxy S22 8/128 Gb Dual Sim Phantom Green (SM-S901BZGDSEK)", "SAMSUNG",
                "uploadDir/img/img_0_60_8574_0_1_637798486657836804.jpg", "Characteristics", 5, 4, 33199);
        product6 = inputProduct("ZenFone 8 16/256GB Dual Sim Black (ZS590KS-2A011EU)", "ASUS",
                "uploadDir/img/img_0_60_8053_0.jpg", "Characteristics", 6, 2, 31444);
        product4 = inputProduct("Redmi Note 10 Pro 6/128 Onyx gray (M2101K6G)", "XIAOMI",
                "uploadDir/img/img_0_60_7928_0.jpg", "Characteristics", 4, 3, 11999);
        categoryDTO1 = new CategoryDTO();
        categoryDTO1.setCategoryName("flagship");
        categoryDTO1.setCategoryId(1);
        categoryDTO2 = new CategoryDTO();
        categoryDTO2.setCategoryName("business");
        categoryDTO2.setCategoryId(2);
        categoryDTO3 = new CategoryDTO();
        categoryDTO3.setCategoryName("budget");
        categoryDTO3.setCategoryId(3);
        categoryDTO4 = new CategoryDTO();
        categoryDTO4.setCategoryName("ultra_budget");
        categoryDTO4.setCategoryId(4);
        ProductFilter filter = new ProductFilter();
        filter.setOrder(ORDER_DESC);
        brandList.add("APPLE");
        brandList.add("SAMSUNG");
        brandList.add("XIAOMI");
        brandList.add("ASUS");
        categoryDTOList = new ArrayList<>();
        categoryDTOList.add(categoryDTO1);
        categoryDTOList.add(categoryDTO2);
        categoryDTOList.add(categoryDTO3);
        categoryDTOList.add(categoryDTO4);

    }

    private Product inputProduct(String name, String brand, String img, String description, int id, int category, int price) {
        Product product = new Product();
        product.setName(name);
        product.setBrand(brand);
        product.setImgUrl(img);
        product.setDescription(description);
        product.setProductId(id);
        product.setCategory(category);
        product.setPrice(price);
        return product;
    }

    @Test
    void shouldReturnCartSize2_whenAddTwoProducts() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1991");
        Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1991");
        CONNECTION_THREAD_LOCAL.set(connection);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute(any())).thenReturn(null);
        when(request.getParameter(ADD_TO_CART)).thenReturn("1").thenReturn("2");
        Map<Product,Integer> expected = new HashMap<>();
        int expectedSize = 2;
        expected.put(product1,1);
        expected.put(product2,1);
        cart.addProduct(request);
        CONNECTION_THREAD_LOCAL.set(connection1);
        cart.addProduct(request);
        assertEquals(expected, cart.getCart());
        assertEquals(expectedSize,cart.totalQuantity());
    }

    @Test
    void shouldReturnCartSize1_whenDeleteProduct() throws SQLException {
        shouldReturnCartSize2_whenAddTwoProducts();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1991");
        CONNECTION_THREAD_LOCAL.set(connection);
        when(request.getParameter(COMMAND_DELETE_PRODUCT)).thenReturn("2");
        when(request.getParameter(PRICE)).thenReturn(String.valueOf(product2.getPrice()));
        Map<Product,Integer> expected = new HashMap<>();
        int expectedSize = 1;
        expected.put(product1,1);
        cart.deleteProduct(request);
        assertEquals(expected, cart.getCart());
        assertEquals(expectedSize,cart.totalQuantity());
    }

    @Test
    void shouldReturnEmptyCart_whenClearCart() throws SQLException {
        shouldReturnCartSize2_whenAddTwoProducts();
        cart.clearCart(request);
        assertTrue(cart.getCart().isEmpty());
    }
    @Test
    void shouldReturnCartWithQuantity5_whenUpdateCart() throws SQLException {
        shouldReturnCartSize1_whenDeleteProduct();
        Map<String, String[]> parameterMap = new HashMap<>();
        parameterMap.put(ID,new String[]{"1"});
        parameterMap.put(PRICE,new String[]{"69999.0"});
        parameterMap.put(QUANTITY,new String[]{"5"});
        when(request.getParameterMap()).thenReturn(parameterMap);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1991");
        CONNECTION_THREAD_LOCAL.set(connection);
        cart.updateCart(request);
        int expected = 5;
        assertEquals(expected,cart.totalQuantity());
    }
}