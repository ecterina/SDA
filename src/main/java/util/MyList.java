package util;

public interface MyList<E> {

    void add(E el);

    boolean remove(E el);

    boolean remove(int pos);

    int size();

    boolean isEmpty();

    boolean contains(E el);

    E get(int pos);

    E set(int pos, E el);

}
