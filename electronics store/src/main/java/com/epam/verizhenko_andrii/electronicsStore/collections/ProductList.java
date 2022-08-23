package com.epam.verizhenko_andrii.electronicsStore.collections;


import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;


public class ProductList<T> implements List<T>, Serializable {
    private static final int START_CAPACITY = 50;
    private T[] products = (T[]) new Object[START_CAPACITY];
    private int size = 0;

    public T[] getProducts() {
        return products;
    }

    public void setProducts(T[] products) {
        this.products = products;
        size = products.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }


    public Iterator<T> iterator(Predicate<T> predicate) {
        return new Iterate<>(predicate);
    }

    @Override
    public Iterator<T> iterator() {
        Predicate<T> predicate = t -> true;
        return new Iterate<>(predicate);
    }

    @Override
    public T[] toArray() {
        return Arrays.copyOf(products, size);
    }

    @Override
    public boolean add(T o) {
        int curentSize = size();
        addCapacity(1);
        products[size] = o;
        size++;
        return curentSize == size - 1;
    }

    private void addCapacity(int numberInput) {
        if (size + numberInput >= products.length) {
            int newSize;
            if (products.length * 2 + 1 > 0) {
                newSize = products.length * 2 + 1;
            } else if (products.length + numberInput > 0) {
                newSize = products.length + numberInput;
            } else {
                throw new IllegalArgumentException("The value is larger than Integer.MAX_VALUE! ");
            }
            T[] tempArr = (T[]) new Object[newSize];
            System.arraycopy(products, 0, tempArr, 0, products.length);
            products = tempArr;
        }
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index < 0) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public boolean addAll(Collection c) {
        T[] product = (T[]) c.toArray(new Object[c.size()]);

        int newSize = size + product.length;
        addCapacity(product.length);
        System.arraycopy(product, 0, products, size, product.length);
        size += product.length;

        return newSize == size();
    }

    @Override
    public boolean addAll(int index, Collection c) {
        T[] col = (T[]) c.toArray();
        T[] temp = (T[]) new Object[products.length + col.length];
        System.arraycopy(products, 0, temp, 0, index);
        System.arraycopy(col, 0, temp, index, col.length);
        System.arraycopy(products, index, temp, col.length + index, products.length - index);
        size += col.length;
        products = temp;
        return true;
    }

    @Override
    public void clear() {
        products = (T[]) new Object[10];
        size = 0;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return products[index];
    }

    @Override
    public T set(int index, Object element) {
        return products[index] = (T) element;
    }

    @Override
    public void add(int index, T element) {
        indexOutBounds(index);
        if (size + 1 >= products.length) {
            addCapacity(1);
        }
        System.arraycopy(products, 0, products, 0, index);
        System.arraycopy(products, index, products, index + 1, products.length - index - 1);
        products[index] = element;
        size++;
    }

    public void indexOutBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public T remove(int index) {
        T removeProduct = products[index];
        indexOutBounds(index);
        int tail = size - index - 1;
        System.arraycopy(products, index + 1, products, index, tail);
        size--;
        return removeProduct;
    }

    @Override
    public int indexOf(Object o) {
        if (Arrays.asList(products).indexOf(o) < size()) {
            return Arrays.asList(products).indexOf(o);
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        return Arrays.asList(Arrays.copyOf(products, size)).lastIndexOf(o);
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        T[] col = (T[]) c.toArray(new Object[c.size()]);
        int newSize = 0;
        T[] temp = (T[]) new Object[products.length];
        for (int i = 0; i < col.length; i++) {
            if (contains(col[i])) {
                temp[i] = col[i];
                newSize++;
            }

        }
        if (newSize != size) {
            products = temp;
            size = newSize;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        T[] col = (T[]) c.toArray(new Object[c.size()]);
        int newSize = size;
        for (int j = 0; j < col.length; j++) {
            if (contains(col[j])) {
                remove(col[j]);
            }
        }
        return newSize != size;
    }

    @Override
    public boolean containsAll(Collection c) {
        for (Object product : c) {
            if (!contains(product)) {
                return false;
            }
        }
        return true;

    }

    @Override
    public T[] toArray(Object[] a) {
        return Arrays.copyOf(products, size);
    }

    class Iterate<T> implements Iterator<T> {
        Predicate<T> predicate;
        private int curentPosition = -1;
        private int curentDelet = -1;
        private int curentSize = size();

        public Iterate(Predicate predicate) {
            this.predicate = predicate;
        }

        @Override
        public boolean hasNext() {
            if (curentSize != size()) {
                throw new ConcurrentModificationException();
            }
            return nextIndex(curentPosition) < size;
        }

        private int nextIndex(int curent) {
            while (!predicate.test((T) products[curent + 1]) && curent < size - 1) {
                curent++;
            }
            return curent + 1;
        }

        @Override
        public T next() {
            if (nextIndex(curentPosition) > size - 1) {
                throw new NoSuchElementException();
            }
            curentPosition = nextIndex(curentPosition);
            curentDelet = curentPosition;
            return (T) products[curentPosition];
        }

        @Override
        public void remove() {
            if (curentDelet < 0) {
                throw new IllegalStateException();
            } else {
                ProductList.this.remove(curentDelet);
                curentPosition--;
                curentSize--;
                curentDelet = -1;
            }
        }

    }
}
