package com.epam.verizhenko_andrii.electronicsStore.collections;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class CastomList implements List<Object> {
    /**
     * This class is a wrapper for two sheets of modify and unmodified.
     * The unmodifiable part of the list always comes at the beginning
     * and does not support any operations that can change it.
     * like this:
     *
     * @see CastomList#add(Object)  - add new element to modify part
     * @see CastomList#set(int, Object) - set element by index
     * @see CastomList#remove(Object)  - remove object from modify part
     * @see CastomList#clear()  - clear modify part
     */
    private final List unmodList;
    private final List modList;

    public CastomList(List modList, List unmodList) {
        this.modList = modList;
        this.unmodList = unmodList;
    }

    @Override
    public int size() {
        return unmodList.size() + modList.size();
    }

    @Override
    public boolean isEmpty() {
        return unmodList.isEmpty() && modList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return unmodList.contains(o) || modList.contains(o);
    }

    /**
     * Iterate over the list and support remove in modify part
     *
     * @throws UnsupportedOperationException - when trying to remove unmodified part
     */
    @Override
    public Iterator<Object> iterator() {
        return new Iterator<Object>() {
            int index = -1;
            int chekDelete = -1;
            int curentSize = size();

            @Override
            public boolean hasNext() {
                if (curentSize != size()) {
                    throw new ConcurrentModificationException();
                }
                return index < modList.size() + unmodList.size() - 1;
            }

            @Override
            public Object next() {
                index++;
                chekDelete = 1;
                if (index < unmodList.size()) {
                    return unmodList.get(index);
                } else {
                    return modList.get(index - unmodList.size());
                }
            }

            @Override
            public void remove() {
                if (index < modList.size() - 1 && chekDelete > 0) {
                    chekDelete = -1;
                    curentSize--;
                    modList.remove(index);
                    index--;
                } else {
                    throw new UnsupportedOperationException();
                }
            }

        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        System.arraycopy(unmodList.toArray(), 0, array, 0, unmodList.size());
        System.arraycopy(modList.toArray(), 0, array, unmodList.size(), modList.size());
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        System.arraycopy(unmodList.toArray(), 0, a, 0, unmodList.size());
        System.arraycopy(modList.toArray(), 0, a, unmodList.size(), modList.size());
        return a;
    }

    /**
     * @param o element will be added to the end of the list
     * @return true if element was added
     */
    @Override
    public boolean add(Object o) {

        return modList.add(o);
    }

    @Override
    public boolean remove(Object o) {
        return modList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!modList.contains(o) && !unmodList.contains(o)) {
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean addAll(Collection<?> c) {
        return modList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<?> c) {
        checkPosition(index);
        return modList.addAll(index - unmodList.size(), c);

    }

    private void checkPosition(int index) {
        if (index < unmodList.size()) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return modList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return modList.retainAll(c);

    }

    @Override
    public void clear() {
        modList.clear();
    }

    @Override
    public Object get(int index) {
        if (index < unmodList.size()) {
            return unmodList.get(index);
        } else if (index < unmodList.size() + modList.size()) {
            return modList.get(index - unmodList.size());
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Setting elements only in modify part
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     */
    @Override
    public Object set(int index, Object element) {
        checkPosition(index);
        modList.set(index - unmodList.size(), element);
        return modList.get(index - unmodList.size());
    }

    @Override
    public void add(int index, Object element) {
        checkPosition(index);
        modList.add(index - unmodList.size(), element);
    }

    @Override
    public Object remove(int index) {
        checkPosition(index);
        return modList.remove(index - unmodList.size());
    }

    @Override
    public int indexOf(Object o) {
        if (unmodList.contains(o)) {
            return unmodList.indexOf(o);
        } else if (modList.contains(o)) {
            return modList.indexOf(o) + unmodList.size();
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (modList.contains(o)) {
            return modList.lastIndexOf(o) + unmodList.size();
        }
        return unmodList.lastIndexOf(o);
    }

    @Override
    public ListIterator<Object> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Object> listIterator(int index) {
        return null;
    }

    @Override
    public List<Object> subList(int fromIndex, int toIndex) {
        return null;
    }
}
