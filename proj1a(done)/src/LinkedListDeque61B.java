import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    private Node sentinel;
    private int size;

    private class Node {
        T data;
        Node previous;
        Node next;

        public Node(T data, Node previous, Node next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }
    }

    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node temp = sentinel.next;
        sentinel.next = new Node(x, sentinel, sentinel.next);
        temp.previous = sentinel.next;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node temp = sentinel.previous;
        sentinel.previous = new Node(x, temp, sentinel);
        temp.next = sentinel.previous;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<T>();
        for (Node temp = sentinel.next; temp != sentinel; temp = temp.next) {
            returnList.add(temp.data);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) return true;
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T removeFirst() {
        if(size == 0) return null;
        Node temp = sentinel.next;
        sentinel.next = temp.next;
        temp.next.previous = sentinel;
        size -= 1;
        return temp.data;
    }

    @Override
    public T removeLast() {
        if(size == 0) return null;
        Node temp = sentinel.previous;
        sentinel.previous = temp.previous;
        temp.previous.next = sentinel;
        size -= 1;
        return temp.data;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) return null;
        Node temp = sentinel.next;
        for (int i = 0; i != index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    @Override
    public T getRecursive(int index) {
        if (index >= size || index < 0){
            return null;
        }else{
            return getRecursiveHelper(sentinel.next, index);
        }
    }
    private T getRecursiveHelper(Node current,int index){
        if(index == 0){
            return current.data;
        }else{
            return getRecursiveHelper(current.next, index - 1);
        }
    }
}
