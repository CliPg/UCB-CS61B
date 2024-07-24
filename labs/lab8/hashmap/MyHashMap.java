package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
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
    protected Collection<Node>[] buckets;
    protected int size;
    protected int bucketNum;
    protected double maxLoad;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        size = 0;
        bucketNum = 16;
        maxLoad = 0.75;
        buckets = (Collection<Node>[]) new LinkedList[bucketNum];
        for (int i = 0; i < bucketNum; i++){
            buckets[i] = createBucket();
        }
    }

    public MyHashMap(int initialSize) {
        size = 0;
        bucketNum = initialSize;
        maxLoad = 0.75;
        buckets = (Collection<Node>[]) new LinkedList[bucketNum];
        for (int i = 0; i < bucketNum; i++){
            buckets[i] = createBucket();
        }
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        size = 0;
        bucketNum = initialSize;
        this.maxLoad = maxLoad;
        buckets = (Collection<Node>[]) new LinkedList[bucketNum];
        for (int i = 0; i < bucketNum; i++){
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
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
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return null;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    private int hash(K key) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
        return h & (bucketNum-1);
    }

    @Override
    public void clear() {
        size = 0;
        buckets = (Collection<Node>[]) new LinkedList[bucketNum];
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null){
            return false;
        }
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        if(key == null || size == 0){
            return null;
        }
        int h = hash(key);
        for(Node node : buckets[h]){
            if(node.key.equals(key)){
                return node.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if(key == null || value == null){
            return ;
        }
        if ((double) size/bucketNum > maxLoad){
            resize();
        }
        int h = hash(key);
        if (!containsKey(key)){
            buckets[h].add(new Node(key,value));
            size++;
        }else {
            for (Node node : buckets[h]){
                if (node.key.equals(key)){
                    node.value = value;
                }
            }
        }

    }

    public void resize(){
        MyHashMap temp = new MyHashMap(bucketNum*2,maxLoad);
        for (int i = 0; i < bucketNum; i++){
            for (Node node : buckets[i]){
                temp.put(node.key,node.value);
            }
        }
        this.buckets = temp.buckets;
        this.bucketNum = bucketNum*2;
        this.maxLoad = temp.maxLoad;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (int i = 0; i < bucketNum; i++){
            for (Node node : buckets[i]){
                keys.add(node.key);
            }
        }
        return keys;
    }

    @Override
    public V remove(K key) {
        if (key == null){
            return null;
        }
        int h = hash(key);
        V val = null;
        for (Node node : buckets[h]){
            if (node.key.equals(key)){
                val = node.value;
                buckets[h].remove(node);
            }
        }
        return val;
    }

    @Override
    public V remove(K key, V value) {
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
