package main;

import java.util.*;

public class MyArrayList<E> implements CustomArrayListInterface<E> {

    private int size;
    private Object[] values;

    public MyArrayList() {
        values = new Object[0];
    }

    public MyArrayList(int initCapacity) {
        if (initCapacity > 0) {
            values = new Object[initCapacity];
        } else if (initCapacity == 0) {
            values = new Object[0];
        } else {
            throw new IllegalArgumentException("Wrong size: " + initCapacity);
        }
    }

    public MyArrayList(Collection<? extends E> newCollection) {
        Object[] elements = newCollection.toArray();
        if ((size = elements.length) != 0) {
            if (newCollection.getClass() == ArrayList.class) {
                values = elements;
            } else {
                values = Arrays.copyOf(elements, size, Object[].class);
            }
        } else {
            values = new Object[0];
        }
    }

    @Override
    public boolean add(E e) {
        ensureCapacity();
        values[size++] = e;
        return true;
    }

    @Override
    public void add(int index, E element) {
        rangeCheck(index);
        ensureCapacity();
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = element;
        size++;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        for (E element : collection) {
            add(element);
        }
        return true;
    }

    @Override
    public void clear() {
        values = new Object[0];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        rangeCheck(index);
        return (E) values[index];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) {
        rangeCheck(index);
        E oldValue = (E) values[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(values, index + 1, values, index, numMoved);
        }
        values[--size] = null;
        return oldValue;
    }

    @Override
    public boolean remove(Object o) {
        for (int index = 0; index < size; index++) {
            if (o.equals(values[index])) {
                remove(index);
                return true;
            }
        }
        return false;
    }

    @Override
    public void sort(Comparator<? super E> c) {
        quickSort(0, size - 1, c);
    }

    private void quickSort(int low, int high, Comparator<? super E> c) {
        if (low < high) {
            int pi = partition(low, high, c);
            quickSort(low, pi - 1, c);
            quickSort(pi + 1, high, c);
        }
    }

    @SuppressWarnings("unchecked")
    private int partition(int low, int high, Comparator<? super E> c) {
        E pivot = (E) values[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (c.compare((E) values[j], pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    @SuppressWarnings("unchecked")
    private void swap(int i, int j) {
        E temp = (E) values[i];
        values[i] = values[j];
        values[j] = temp;
    }

    private void ensureCapacity() {
        if (size == values.length) {
            int newSize = (int) (values.length * 1.5 + 1);
            values = Arrays.copyOf(values, newSize);
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        boolean first = true;
        for (int i = 0; i < size; i++) {
            if (values[i] != null){
                if (!first) {
                    sb.append(", ");
                }
                sb.append(values[i].toString());
                first = false;
            }
        }
        sb.append(']');
        return sb.toString();
    }

    private void rangeCheck(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index, size));
    }

    private static String outOfBoundsMsg(int index, int size) {
        return "Index: "+index+", Size: "+ size;
    }

}
