package com.epam.verizhenko_andrii.electronicsStore.collections;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class CopyOnWriteProductList<T> extends ProductList<T> {
    ReentrantLock lock = new ReentrantLock();

    public CopyOnWriteProductList() {
        setProducts((T[]) new Object[0]);
    }

    @Override
    public boolean add(T o) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            T[] elements = getProducts();
            int len = size();
            T[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = o;
            setProducts(newElements);
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean remove(Object o) {
    if (indexOf(o) < 0) {
        return false;
    }
     remove(indexOf(o));
     return  true;
    }
    @Override
    public boolean addAll(Collection c) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            T[] elements = getProducts();
            int len = size();
            T[] newElements = (T[]) new Object[len + c.size()];
            T[] col = (T[]) c.toArray();
            System.arraycopy(elements, 0, newElements, 0, size());
            System.arraycopy(col, 0, newElements, size(), col.length);
            setProducts(newElements);
            return size() == newElements.length;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean addAll(int index, Collection c) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        indexOutBounds(index);
        try {
            T[] elements = getProducts();
            int len = size();
            T[] newElements = (T[]) new Object[len + c.size()];
            T[] col = (T[]) c.toArray();
            System.arraycopy(elements, 0, newElements, 0, index);
            System.arraycopy(col, 0, newElements, index, col.length);
            System.arraycopy(elements,  index, newElements, col.length + index, elements.length - index);
            setProducts(newElements);
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            super.clear();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T set(int index, Object element) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            T[] elements = getProducts();
            T oldValue = get(index);
            int len = size();
            T[] newElements = Arrays.copyOf(elements, len);
            newElements[index] = (T) element;
            setProducts(newElements);
            return oldValue;
        } finally {
            lock.unlock();
        }

    }

    @Override
    public void add(int index, T element) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        if (size() > 0){
            indexOutBounds(index);
        }
        try {
            T[] elements = getProducts();
            int len = size();
            T[] newElements = Arrays.copyOf(elements, len + 1);
            System.arraycopy(elements, 0, newElements, 0, index);
            System.arraycopy(elements,  index, newElements,  index + 1, elements.length - index);
            newElements[index] = element;
            setProducts(newElements);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T remove(int index) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        indexOutBounds(index);
        try {
            T[] elements = getProducts();
            T[] newElements = (T[]) new Object[size() - 1];
            T removeProduct = elements [index];
            int tail = size() - index - 1;
            System.arraycopy(elements,  index + 1, newElements,  index, tail);
            setProducts(newElements);
            return removeProduct;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new supIterator();
    }

    private class  supIterator implements Iterator<T> {
        private final Object[] snapshot;
        /** Index of element to be returned by subsequent call to next.  */
        private int cursor = 0;

        public supIterator() {
            this.snapshot = getProducts();
        }

        public boolean hasNext() {
            return cursor < snapshot.length;
        }

        public T next() {
            if (! hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) snapshot[cursor++];
        }

        /**
         * Not supported. Always throws UnsupportedOperationException.
         * @throws UnsupportedOperationException always;
         *         is not supported by this iterator.
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }

}
}
