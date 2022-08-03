package com.epam.verizhenko_andrii.electronicsStore.collections;
import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CastomListTest {
    List<Product> unmodList;
    List<Product> modList;
    CastomList castomList;
    @BeforeEach
    void init() {
        Product product = new Product();
        Product product1 = new Product();
        Product product2 = new Product();
        Product product3 = new Product();
        product.setBrand("Samsung");
        product1.setBrand("LG");
        product2.setBrand("Xiaomi");
        product3.setBrand("Hisense");
        modList = new ArrayList<>();
        unmodList = new ArrayList<>();

        modList.add(product);
        modList.add(product1);

        unmodList.add(product2);
        unmodList.add(product3);
        castomList = new CastomList(modList,unmodList);
    }
    @AfterEach
    public void teardown() {
        modList.clear();
        unmodList.clear();
        castomList = null;
    }
    @Test
    void size() {
        int expected = 4;
        assertEquals(expected, castomList.size());
    }

    @Test
    void contains() {
        Product product = new Product();
        product.setBrand("Samsung");
        Product product1 = new Product();
        product1.setBrand("Atlant");
        assertTrue(castomList.contains(product));
        assertFalse(castomList.contains(product1));

    }

    @Test
    void iteratorRemoveFromModList() {
        Iterator<Object> itr = castomList.iterator();
        int count = 0;
        int expectedSize = 3;
        Product removeObj = modList.get(0);
        itr.next();
        itr.remove();
        assertFalse(castomList.contains(removeObj));
        while (itr.hasNext()){
            count++;
            itr.next();
        }
        assertEquals(expectedSize, count);
    }
    @Test
    void iteratorUnsupportedRemoveFromUnmodList() {
        Iterator<Object> itr = castomList.iterator();
        itr.next();
        itr.next();
        itr.next();
        assertThrows(UnsupportedOperationException.class, itr::remove);
    }
    @Test
    void add() {
    castomList.add(new Product());
    int expected = 5;
    int expectIndex = 4;
    assertTrue(castomList.contains(new Product()));
    assertEquals(expected, castomList.size());
    assertEquals(expectIndex, castomList.indexOf(new Product()));
    }

    @Test
    void remove() {
        Product product = new Product();
        product.setBrand("Samsung");
        castomList.remove(product);
        int expected = 3;
        assertEquals(expected, castomList.size());

        assertTrue(castomList.containsAll(unmodList));
    }

    @Test
    void containsAll() {
        Product product = new Product();
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> expected = new ArrayList<>();
        product.setBrand("Samsung");
        product1.setBrand("LG");
        product2.setBrand("Hisense");
        expected.add(product);
        expected.add(product1);
        expected.add(product2);

        assertTrue(castomList.containsAll(expected));

        expected.add(new Product("Atlant",10,9));
        assertFalse(castomList.containsAll(expected));
    }

    @Test
    void addAll() {
        Product product = new Product();
        Product product1 = new Product();
        Product product2 = new Product();
        int expSize = 7;
        List<Product> expected = new ArrayList<>();
        product.setBrand("Samsung");
        product1.setBrand("LG");
        product2.setBrand("Hisense");
        expected.add(product);
        expected.add(product1);
        expected.add(product2);
        castomList.addAll(expected);

        assertTrue(castomList.containsAll(expected));
        assertEquals(expSize, castomList.size());
    }

    @Test
    void testAddAll() {
        Product product = new Product();
        Product product1 = new Product();
        Product product2 = new Product();
        int expSize = 7;
        List<Product> expected = new ArrayList<>();
        product.setBrand("Atlant");
        product1.setBrand("Nokia");
        product2.setBrand("Indesit");

        expected.add(product);
        expected.add(product1);
        expected.add(product2);

        castomList.addAll(3,expected);

        assertTrue(castomList.containsAll(expected));
        assertEquals(expSize, castomList.size());
        assertEquals(product, castomList.get(3));
        assertThrows(UnsupportedOperationException.class, ()-> castomList.addAll(1,expected));
    }

    @Test
    void removeAll() {
        int expSize = 2;
        assertTrue(castomList.removeAll(modList));
        assertEquals(expSize, castomList.size());
        assertFalse(castomList.removeAll(unmodList));
    }

    @Test
    void clear() {
        int expSize = 2;
        List<Product> deleteCol = new ArrayList<>(modList);
        castomList.clear();
        assertEquals(expSize, castomList.size());
        assertTrue(castomList.containsAll(unmodList));
        assertFalse(castomList.containsAll(deleteCol));
    }

    @Test
    void get() {
        Product expectFirst = modList.get(0);
        Product expectLast = unmodList.get(1);
        assertEquals(expectFirst, castomList.get(2));
        assertEquals(expectLast, castomList.get(1));

    }

    @Test
    void set() {
        Product expect = unmodList.get(1);
        assertEquals(expect, castomList.set(3,expect));
        assertThrows(UnsupportedOperationException.class, ()-> castomList.set(1,expect));
    }

    @Test
    void testAdd() {
        int expectedSize = 5;
        int expectIndex = 4;
        int expectThrows = 1;
        castomList.add(expectIndex,new Product());
        assertTrue(castomList.contains(new Product()));
        assertEquals(expectedSize, castomList.size());
        assertEquals(expectIndex, castomList.indexOf(new Product()));
        assertThrows(UnsupportedOperationException.class, ()-> castomList.add(expectThrows,new Product()));
    }

    @Test
    void testRemove() {
        Product product = modList.get(0);
        castomList.remove(2);
        int expected = 3;
        assertEquals(expected, castomList.size());
        assertFalse(castomList.contains(product));
        assertThrows(UnsupportedOperationException.class, ()-> castomList.remove(1));
    }

    @Test
    void indexOf() {
        int expected = 0;
        int expNoSuchElement = -1;
        assertEquals(expected, castomList.indexOf(unmodList.get(0)));
        assertEquals(expNoSuchElement, castomList.indexOf(new Product()));

    }

    @Test
    void lastIndexOf() {
        modList.add(unmodList.get(0));
        int expectedLast = 4;
        int expectedFirst = 0;
        int expNoSuchElement = -1;
        assertEquals(expectedLast, castomList.lastIndexOf(unmodList.get(0)));
        assertEquals(expectedFirst, castomList.indexOf(unmodList.get(0)));
        assertEquals(expNoSuchElement, castomList.indexOf(new Product()));
    }
}