package com.renshihan.javabase.thread.lock.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author renshihan@winchannel.net
 * @date 2018/4/10 12:12
 * 结合reentrantReadWriteLock
 * treeMap当数据操作较大或者read操作明显多于write操作时，由于readLock的不阻塞性质使得ReentrantReadWriteLock效率明显高于synchronized
 */
public class LockTreeMap implements Map{
    private final Map containerMap=new TreeMap();
    private final ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();
    private final Lock readLock=reentrantReadWriteLock.readLock();
    private final Lock writeLock=reentrantReadWriteLock.writeLock();
//    public T get(String key){
//
//    }
//    public void put(String key,T value){
//
//    }
//    public void clear(){
//
//    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Object get(Object key) {
        readLock.lock();
        try {
            return containerMap.get(key);
        }finally {
            readLock.unlock();
        }
    }

    @Override
    public Object put(Object key, Object value) {
        writeLock.lock();
        try {
            return containerMap.put(key,value);
        }finally {
            writeLock.unlock();
        }
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }
}
