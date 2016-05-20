package cn.lzq.thread.function;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liuzhiqi on 16/5/20.
 */
public class TestCondition {
    private int queueSize = 10;

    private PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);

    private ReentrantLock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();

    private Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        TestCondition test = new TestCondition();
        Consumer consumer = test.new Consumer();
        Producer producer = test.new Producer();

        consumer.start();
        producer.start();

    }

    class Consumer extends Thread{
        @Override
        public void run() {
            consume();
        }

        private void consume(){
            while (true){
                lock.lock();
                try {
                    if(queue.size() == 0){
                        try {
                            System.out.println("队列空，等待数据");
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.poll();
                    notFull.signal();
                    System.out.println("从队列取走一个元素，队列剩余"+queue.size()+"个元素");
                }finally {
                    lock.unlock();
                }
            }
        }
    }

    class Producer extends Thread{
        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true){
                lock.lock();
                try {
                    if (queue.size() == queueSize){
                        System.out.println("队列满，等待有空余空间");
                        try {
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.offer(1);
                    notEmpty.signal();
                    System.out.println("向队列取中插入一个元素，队列剩余空间："+(queueSize-queue.size()));
                }finally {
                    lock.unlock();
                }
            }

        }
    }





}
