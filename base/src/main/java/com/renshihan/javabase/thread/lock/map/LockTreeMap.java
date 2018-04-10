package com.renshihan.javabase.thread.lock.map;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author renshihan@winchannel.net
 * @date 2018/4/10 12:12
 * 结合reentrantReadWriteLock
 * treeMap当数据操作较大或者read操作明显多于write操作时，由于readLock的不阻塞性质使得ReentrantReadWriteLock效率明显高于synchronized
 */
public class LockTreeMap<String,T> {
    private final Map<String,T> containerMap=new TreeMap();
    private final ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();
    private final Lock readLock=reentrantReadWriteLock.readLock();
    private final Lock writeLock=reentrantReadWriteLock.writeLock();
    public T get(String key){
        readLock.lock();
        try {
            return containerMap.get(key);
        }finally {
            readLock.unlock();
        }
    }
    public void put(String key,T value){
        writeLock.lock();
        try {
            containerMap.put(key,value);
        }finally {
            writeLock.unlock();
        }
    }

}
