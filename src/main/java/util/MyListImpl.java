package util;

import java.security.InvalidParameterException;

/**
 * Class simulating indexed access list behaviour
 * the checked exception part is intentionally omitted(used runtime exceptions)
 * to be implemented in next iteration
 *
 * @param <E> the generic type
 */
public class MyListImpl<E> implements MyList<E> {

    private E[] collection;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public MyListImpl() {
        size = -1;
        capacity = 10;
        //can't initialize array of generic object, need unchecked cast
        collection = (E[]) new Object[capacity];
    }

    public void add(E el) {
        if (capacity == (size + 1)) {
            resize();
        } else {
            collection[++size] = el;
        }
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        E[] temp = (E[]) new Object[capacity * 2];
        System.arraycopy(collection, 0, temp, 0, size);
        collection = temp;
    }

    public boolean remove(E el) {
        int pos = containsPos(el);
        if (pos == -1) {
            return false;
        } else {
            System.arraycopy(collection, pos + 1, collection, pos, collection.length - 1 - pos);
        }
        return true;
    }

    private int containsPos(E el) {
        int pos = -1;
        for (int i = 0; i < collection.length; i++) {
            if (el.equals(collection[i])) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public boolean remove(int pos) {
        if (pos > size) {
            return false;
        } else {
            System.arraycopy(collection, pos + 1, collection, pos, collection.length - 1 - pos);
            return true;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == -1;
    }

    public boolean contains(E el) {
        return containsPos(el) != -1;
    }

    public E get(int pos) {
        if (pos > size) {
            throw new InvalidParameterException("Invalid position");
        } else {
            return collection[pos];
        }
    }

    public E set(int pos, E el) {
        if (pos > size) {
            throw new InvalidParameterException("Invalid position");
        } else {
            E temp = collection[pos];
            collection[pos] = el;
            return temp;
        }
    }
}
