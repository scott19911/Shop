package com.epam.verizhenko_andrii.electronicsStore.collections;

import com.epam.verizhenko_andrii.electronicsStore.products.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


import static org.junit.jupiter.api.Assertions.*;

class CopyOnWriteProductListTest {


    @Test
    void iterator() {
        CopyOnWriteProductList<Product> list = new CopyOnWriteProductList<>();
        Product product = new Product("Samsung",125,200);
        Product product1 = new Product("LG",125,200);
        list.add(product);
        list.add(product1);
        Iterator<Product> itr = list.iterator();
        list.add(0,product1);
        list.set(0,product1);
        list.remove(product1);
        assertTrue(itr.hasNext());
        assertEquals(product,itr.next());
        assertEquals(product1,itr.next());
        assertThrows(NoSuchElementException.class, itr::next);

        }
    @Test
    void addAllTest(){
        CopyOnWriteProductList<Product> list = new CopyOnWriteProductList<>();
        Product product = new Product("Samsung",125,200);
        Product product1 = new Product("LG",125,200);
        List<Product> coll= new ArrayList<>();
        int expSize = 4;
        coll.add(product);
        coll.add(product1);
        list.add(product);
        list.add(product1);
        list.addAll(coll);
        assertEquals(expSize, list.size());
        assertEquals(product,list.get(2));
     }
    @Test
    void addAllByIndexTest(){
        CopyOnWriteProductList<Product> list = new CopyOnWriteProductList<>();
        Product product = new Product("Samsung",125,200);
        Product product1 = new Product("LG",125,200);
        List<Product> coll= new ArrayList<>();
        int expSize = 4;
        coll.add(product);
        coll.add(product1);
        list.add(product);
        list.add(product1);
        list.addAll(1,coll);
        assertEquals(expSize, list.size());
        assertEquals(product,list.get(1));
        assertEquals(product,list.get(0));
        assertEquals(product1,list.get(2));
        assertEquals(product1,list.get(3));
    }
    @Test
    void shouldIterateOverSnapshotVersion(){
        CopyOnWriteProductList<String> list = new CopyOnWriteProductList<>();
        list.add("A");
        list.add("B");

        Iterator<String> iterator = list.iterator();
        list.add("c");
        list.add("d");

        assertEquals("A", iterator.next());
        assertEquals("B", iterator.next());
        assertFalse(iterator.hasNext());

        iterator = list.iterator();
        assertEquals("A", iterator.next());
        assertEquals("B", iterator.next());
        assertEquals("c", iterator.next());
        assertEquals("d", iterator.next());
        assertFalse(iterator.hasNext());
    }


}