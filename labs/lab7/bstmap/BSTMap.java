package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B{

    private K key;
    private V value;
    private BSTMap left;
    private BSTMap right;
    private static int size = 0;

    public BSTMap(K key, V value){
        this.key = key;
        this.value = value;
    }

    public BSTMap(){
        this.key = null;
        this.value = null;
        this.left = null;
        this.right = null;
    }

    @Override
    public void clear() {
        size = 0;
        this.key = null;
        this.value = null;
        this.left = null;
        this.right = null;
    }

    public boolean containsHelper(BSTMap t, K key){
        if(t.key != null){
            if(key.compareTo((K)t.key) == 0){
                return true;
            }else{
                if(t.key.compareTo(key) < 0){
                    return containsHelper(t.left,key);
                }else if(t.key.compareTo(key) > 0){
                    return containsHelper(t.right,key);
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return containsHelper(this,(K)key);
    }

    public Object getHelper(BSTMap t, K key) {
        if(t.key != null){
            if(t.key.compareTo(key) == 0){
                return t.value;
            }else{
                if(t.key.compareTo(key) < 0){
                    return getHelper(t.left,key);
                }else if(t.key.compareTo(key) > 0){
                    return getHelper(t.right,key);
                }
            }
        }
        return null;
    }

    @Override
    public Object get(Object key) {
        return getHelper(this,(K)key);
    }

    @Override
    public int size() {
        return size;
    }


    public void putHelper(BSTMap t, K key, V value) {
        if(t.key == null){
            size++;
            t.key = key;
            t.value = value;
        }else{
            if(t.key.compareTo(key) < 0){
                if(t.left == null){
                    t.left = new BSTMap(key,value);
                    size++;
                }else {
                    putHelper(t.left,key,value);
                }
            }else if(t.key.compareTo(key) > 0){
                if(t.right == null){
                    t.right = new BSTMap(key,value);
                    size++;
                }else {
                    putHelper(t.right,key,value);
                }
            }
        }
    }

    @Override
    public void put(Object key, Object value) {
        putHelper(this,(K)key,(V)value);
    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public Object remove(Object key, Object value) {
        return null;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
