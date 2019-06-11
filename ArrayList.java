package Example4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E>{
    private static final int DEFAULT_capacity = 10;
    private int size;
    private E[] elem;
    public ArrayList(){
        doClear();
    }
    public void clear() {
        doClear();
    }
    private void doClear(){
        size=0;
        ensureCapacity(DEFAULT_capacity);
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size()==0;
    }
    public void tirmToSize() {
        ensureCapacity(size());
    }
    public E get(int i) {
        if(i < 0 || i >= size())
            throw new ArrayIndexOutOfBoundsException();
        return elem[i];
    }
    public E set(int i, E x) {
        if(i < 0 || i >= size())
            throw new ArrayIndexOutOfBoundsException();
        E old = elem[i];
        elem[i] = x;
        return old;
    }
    public void ensureCapacity(int newCapacity) {
        if(newCapacity < size())
            return;
        E[] old = elem;
        elem = (E[]) new Object[newCapacity];
        for(int i = 0; i < size(); i++)
        {
            elem[i] = old[i];
        }
    }
    public boolean add(E x){
        add(size(), x);
        return true;
    }
    public void add(int i, E x){
        if(i < 0 || i > size())
            throw new ArrayIndexOutOfBoundsException("插入下标不合理");
        if(elem.length == size())
            ensureCapacity(size() * 2 + 1);
        for(int j = size(); j > i; j--)
            elem[j] = elem[j-1];
        elem[i] = x;
        size++;
    }
    public E remove(int i){
        if(i < 0 || i > size())
            throw new ArrayIndexOutOfBoundsException();
        E x = elem[i];
        for(; i < size(); i++)
            elem[i] = elem[i+1];
        size--;
        return x;
    }
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }
    private class ArrayListIterator implements Iterator<E>{
        private int current = 0;
        public boolean hasNext(){
            return current < size();
        }
        public E next(){
            if(!hasNext())
                throw new NoSuchElementException();
            return elem[current++];
        }
        public void remove(){
            ArrayList.this.remove(--current);
        }
    }
}
