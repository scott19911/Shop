package com.epam.verizhenko_andrii.electronicsStore;
import com.epam.verizhenko_andrii.electronicsStore.collections.ProductList;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class ProductListTest {


    @Test
    void testIteratorRemove(){
        List<Product> actual = new ArrayList<>();
        Product product1 = new Product("Samsung",230, 10000);
        Product product2 = new Product("Lg",230, 15000);
        int expectedSize = 1;
        actual.add(product1);
        actual.add(product2);
        Iterator<Product> itr = actual.iterator();
        assertThrows(IllegalStateException.class, itr::remove);
        assertTrue(itr.hasNext());
        assertThrows(IllegalStateException.class, itr::remove);
        itr.next();
        itr.remove();
        assertEquals(expectedSize,actual.size());
        assertTrue(actual.contains(product2));
        assertFalse(actual.contains(product1));
    }
    @Test
    void testIteratorWithPredicateRemove(){
        ProductList<Product> actual = new ProductList<>();
        Product product1 = new Product("Samsung",230, 10000);
        Product product2 = new Product("Lg",230, 15000);
        int expectedSize = 1;
        actual.add(product1);
        actual.add(product2);
        Predicate<Product> predicate = product -> product != null && product.equals(product2);
        Iterator<Product> itr = actual.iterator(predicate);
        assertThrows(IllegalStateException.class, itr::remove);
        assertTrue(itr.hasNext());
        assertThrows(IllegalStateException.class, itr::remove);
        assertEquals(product2,itr.next());
        itr.remove();
        assertThrows(IllegalStateException.class, itr::remove);
        assertEquals(expectedSize,actual.size());
        assertTrue(actual.contains(product1));
        assertFalse(actual.contains(product2));
    }
    @Test
    void iteratorWithPredicate() {
        ProductList<Product> actual = new ProductList<>();
        int expectedSize = 1;
        Product product1 = new Product("Samsung",230, 10000);
        Product product2 = new Product("Lg",230, 15000);
        Predicate<Product> predicate = product -> product != null && product.equals(product2);
        int count = 0;
        actual.add(product1);
        actual.add(product2);
        Iterator<Product> itr = actual.iterator(predicate);
        while (itr.hasNext()){
            count++;
            assertEquals(product2, itr.next());
        }
        assertEquals(expectedSize, count);

    }


    @Test
    void add() {
        ProductList<Product> actual = new ProductList<>();

        int expectedSize = 2;
        Product product1 = new Product("Samsung",230, 10000);
        Product product2 = new Product("Lg",230, 15000);
        List<Product> exp = new ArrayList<>();
        actual.add(product1);
        actual.add(product2);
        exp.add(product1);
        exp.add(product2);
        assertEquals(expectedSize, actual.size());
        assertTrue(actual.containsAll(exp));


    }

    @Test
    void remove() {
        ProductList<Product> actual = new ProductList<>();
        int expectedSize = 2;
        Product product1 = new Product("Samsung",230, 10000);
        Product product2 = new Product("Lg",230, 15000);
        Product product3 = new Product("Lg",440, 2000);
        actual.add(product1);
        actual.add(product2);
        actual.add(product3);
        actual.remove(product1);
        assertEquals(expectedSize, actual.size());
        assertFalse(actual.contains(product1));
    }

    @Test
    void get() {
        ProductList<Product> actual = new ProductList<>();
        Product product1 = new Product("Samsung",230, 10000);
        Product product2 = new Product("Lg",230, 15000);
        actual.add(product1);
        actual.add(product2);

        assertEquals(product1,actual.get(0));
        assertEquals(product2,actual.get(1));
        assertNotEquals(actual.get(0), product2);
        assertThrows(IndexOutOfBoundsException.class, () -> actual.get(2));
    }

    @Test
    void testAddByIndex() {
        ProductList<Product> actual = new ProductList<>();
        int expSize = 3;
        Product product1 = new Product("Samsung",230, 10000);
        Product product2 = new Product("Lg",230, 15000);
        actual.add(product1);
        actual.add(product2);

        assertEquals(actual.get(1), product2);

        actual.add(1,product2);
        assertEquals(expSize, actual.size());
        assertEquals(product1,actual.get(0));
        assertEquals(product2,actual.get(1));

    }

    @Test
    void testRemoveByIndex() {
        ProductList<Product> actual = new ProductList<>();
        int expectedSize = 2;
        Product product1 = new Product("Samsung",230, 10000);
        Product product2 = new Product("Lg",230, 15000);
        Product product3 = new Product("Lg",440, 2000);
        actual.add(product1);
        actual.add(product2);
        actual.add(product3);
        actual.remove(1);
        assertEquals(expectedSize, actual.size());
        assertFalse(actual.contains(product2));
        assertEquals(product3,actual.get(1));
    }
}