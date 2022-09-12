package com.epam.verizhenko_andrii.electronicsStore.collections;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The class is a wrapper for ArrayList. Which stores only one instance of each object.
 */
public class CustomSet<T> extends ArrayList<T> {

    /**
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @throws IllegalArgumentException - when contains the element to be added
     */
    public T set(int index, T element) {
        if (contains(element)) {
            throw new IllegalArgumentException();
        }
        return super.set(index, element);
    }

    /**
     * add new object to collections
     * @param t element whose presence in this collection is to be ensured
     * @return - true when successes add
     */
    public boolean add(T t) {
        if (contains(t)) {
            return false;
        }
        return super.add(t);
    }

    public void add(int index, T element) {
        if (contains(element)) {
            throw new IllegalArgumentException();
        }
        super.add(index, element);
    }


    public boolean addAll(Collection<? extends T> c) {
        if (super.containsAll(c)) {
            return false;
        }
        return !super.removeAll(c) && super.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        if (super.containsAll(c)) {
            return false;
        }
        return !super.removeAll(c) && super.addAll(index, c);
    }
}
