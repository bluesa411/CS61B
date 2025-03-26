package hashmap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {



    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int size;
    private int capacity;
    private double loadFactor;
    private final int DEFAULT_CAPACITY = 16;
    private final double DEFAULT_loadFactor = 0.75;
    // You should probably define some more!


    /** Constructors */
    public MyHashMap() {
        size = 0;
        capacity = DEFAULT_CAPACITY;
        buckets = new Collection[capacity];
        loadFactor = DEFAULT_loadFactor;
    }

    public MyHashMap(int initialCapacity) {
        size = 0;
        capacity = initialCapacity;
        buckets = new Collection[capacity];
        loadFactor = DEFAULT_loadFactor;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        size = 0;
        capacity = initialCapacity;
        buckets = new Collection[capacity];
        this.loadFactor = loadFactor;
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        // TODO: Fill in this method.
        return new ArrayList<Node>();
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
    private void resizeBuckets() {
        capacity *= 2;
        Collection<Node>[] originBuckets = buckets;
        buckets = new Collection[capacity];
        size = 0;
        for(int i = 0; i < originBuckets.length; i++) {
            if(originBuckets[i] == null) {
                continue;
            }
            for(Node node : originBuckets[i]) {
                this.put(node.key, node.value);
            }
        }
    }

    @Override
    public void put(K key, V value) {
        int index = Math.floorMod((int)key.hashCode(), capacity);
        if(buckets[index] == null) {
            buckets[index] = createBucket();
        }
        for(Node node : buckets[index]){
            if(node.key.equals(key)){
                node.value = value;
                return;
            }
        }
        buckets[index].add(new Node(key, value));
        size++;
        if((float)size / capacity >= loadFactor) {
            resizeBuckets();
        }
    }

    @Override
    public V get(K key) {
        int index = Math.floorMod((int)key.hashCode(), capacity);
        if(buckets[index] == null) {
            return null;
        }
        for(Node node : buckets[index]){
            if(node.key.equals(key)){
                return node.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int index = Math.floorMod((int)key.hashCode(), capacity);
        if(buckets[index] == null) {
            return false;
        }
        for(Node node : buckets[index]){
            if(node.key.equals(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        for(int i = 0; i < capacity; i++){
            buckets[i] = null;
        }
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
