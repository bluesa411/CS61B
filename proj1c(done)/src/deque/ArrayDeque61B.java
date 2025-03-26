package deque;

import com.github.javaparser.quality.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] array;
    private int nextfront;
    private int nextlast;
    private int capacity;
    private int size;

    public int retnextfront(){
        return nextfront;
    }

    public int retcapacity(){
        return capacity;
    }

    public ArrayDeque61B() {
        array = (T[]) new Object[8];
        nextfront = 3;
        nextlast = 4;
        capacity = 8;
        size = 0;
    }

    public Iterator<T> iterator(){
        return new ArrayDeque61BIterator();
    }

    private class ArrayDeque61BIterator implements Iterator<T> {

        private int wizPos;

        public ArrayDeque61BIterator(){
            wizPos = Math.floorMod(++nextfront, capacity);
        }

        public boolean hasNext(){
            return !(wizPos == nextlast);
        }

        public T next(){
            T item = array[wizPos];
            wizPos = Math.floorMod(++wizPos, capacity);
            return item;
        }
    }
    @Override
    public boolean equals(Object o){
        if(o instanceof ArrayDeque61B){
            ArrayDeque61B<T> other = (ArrayDeque61B<T>) o;
            if(this.size != other.size){
                return false;
            }
            for(int i = this.nextfront; i!= this.nextlast;
                i = Math.floorMod(++i, capacity)){
                if(this.get(i) != other.get(i)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(T item : this){
            sb.append(item.toString());
            sb.append(", ");
        }
        return sb.toString();
    }

    @Override
    public void addFirst(T x) {
        if(size + 1 == capacity){
            this.resize(capacity * 2);
        }
        array[nextfront--] = x;
        nextfront = Math.floorMod(nextfront, capacity);
        size++;
    }

    @Override
    public void addLast(T x) {
        if(size + 1 == capacity){
            this.resize(capacity * 2);
        }
        array[nextlast++] = x;
        nextlast = Math.floorMod(nextlast, capacity);
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<T>();
        for(int i = 0; i < capacity; i++){
            returnList.add(array[i]);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0) return true;
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T removeFirst() {
        if((double)size / capacity < 0.25){
            this.resize(capacity / 2);
        }
        if(size == 0) return null;
        nextfront++;
        nextfront = Math.floorMod(nextfront, capacity);
        T returnValue = array[nextfront];
        array[nextfront] = null;
        size--;
        return returnValue;
    }

    @Override
    public T removeLast() {
        if((double)size / capacity < 0.25){
            this.resize(capacity / 2);
        }
        if(size == 0) return null;
        nextlast = Math.floorMod(++nextlast, capacity);
        T returnValue = array[nextlast];
        array[nextlast] = null;
        size--;
        return returnValue;
    }

    public void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];
        int j = 0;
        int originalsize = size;
        for(int i = 0; i < originalsize; i++){
            newArray[j++] = this.removeFirst();
        }
        this.array = newArray;
        nextfront = newCapacity - 1;
        nextlast = j;
        capacity = newCapacity;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= capacity) return null;
        return this.array[index];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
