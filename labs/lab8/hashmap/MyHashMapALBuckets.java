package hashmap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Hash Table with Array List buckets
 * @author Neil Kulkarni
 */
public class MyHashMapALBuckets<K, V> extends MyHashMap<K, V> {

    /**
     * Constructor that creates a backing array with default
     * initial size and load factor
     */
    public MyHashMapALBuckets() {
        super.size = 0;
        super.bucketNum = 16;
        super.maxLoad = 0.75;
        super.buckets = (Collection<Node>[]) new ArrayList[bucketNum];
        for (int i = 0; i < bucketNum; i++){
            buckets[i] = createBucket();
        }
    }

    /**
     * Constructor that creates a backing array of initialSize
     * and default load factor
     *
     * @param initialSize initial size of backing array
     */
    public MyHashMapALBuckets(int initialSize) {
        super(initialSize);
    }

    /**
     * Constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMapALBuckets(int initialSize, double maxLoad) {
        super(initialSize, maxLoad);
    }

    @Override
    protected Collection<Node> createBucket() {
        return new ArrayList<>();
    }
}
