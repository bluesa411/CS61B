import org.checkerframework.checker.units.qual.K;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private BSTNode root;
    int size;

    private class BSTNode {
        private final K key;
        private V value;
        private BSTNode left, right;
        public BSTNode(K key, V value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public BSTMap() {
        root = null;
        size = 0;
    }

    /*find the node which contains the key
    return the node which contains the key
    return null if there is no correct node
     */
    private BSTNode findBSTNode(K key) {
        BSTNode current = root;
        while(current != null) {
            if (current.key.compareTo(key) == 0) {
                return current;
            }else if(current.key.compareTo(key) > 0) {
                current = current.left;
            }else{
                current = current.right;
            }
        }
        return null;
    }

    private void putHelper(BSTNode root, K key, V value) {
        if(root.key.compareTo(key) >= 0) {
            if(root.left == null){
                root.left = new BSTNode(key, value, null, null);
            }
            else{
                putHelper(root.left, key, value);
            }
        }else{
            if(root.right == null){
                root.right = new BSTNode(key, value, null, null);
            }else{
                putHelper(root.right, key, value);
            }
        }
    }

    @Override
    public void put(K key, V value) {
        //create the root
        if(size == 0){
            root = new BSTNode(key, value, null, null);
            ++size;
            return;
        }
        //already have the key
        if(containsKey(key)){
            //must have a node
            BSTNode node = findBSTNode(key);
            node.value = value;
            return;
        }
        //find the right location
        putHelper(root, key, value);
        ++size;
        return;
    }

    @Override
    public V get(K key) {
        BSTNode node = findBSTNode(key);
        if(node == null) return null;
        return node.value;
    }

    @Override
    public boolean containsKey(K key) {
        return findBSTNode(key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    private void printHelper(BSTNode root){
        if(root != null){
            printHelper(root.left);
            System.out.print(root.key + " ");
            printHelper(root.right);
        }
    }

    public void printInOrder() {
        printHelper(root);
    }

    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<K> {
        private BSTNode current;

        public BSTIterator() {
            current = root;
        }

        public boolean hasNext() {}

        public K next() {}

    }
}
