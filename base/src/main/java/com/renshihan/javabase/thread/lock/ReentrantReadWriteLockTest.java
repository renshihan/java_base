package com.renshihan.javabase.thread.lock;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author renshihan@winchannel.net
 * @date 2018/4/10 11:33
 * 针对reentrantReadWriteLock 重复性读写锁的应用例子
 * 定义：
 * 所谓读写锁，是对访问资源共享锁和排斥锁，
 * 一般的重入性语义为 如果对资源加了写锁，其他线程无法再获得写锁与读锁，
 * 但是持有写锁的线程，可以对资源加读锁（锁降级）；如果一个线程对资源加了读锁，其他线程可以继续加读锁。
 */
public class ReentrantReadWriteLockTest {
    private final ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();
    private final Lock readLock=reentrantReadWriteLock.readLock();
    private final Lock writeLock=reentrantReadWriteLock.writeLock();
    @Test
    public void treeMapTest(){

    }
}
