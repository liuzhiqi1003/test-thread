package cn.lzq.thread.function;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liuzhiqi on 16/5/20.
 */
public class TestAtomic {
    private static final int MAX_THREADS = 3;
    private static final int TASK_COUNT = 3;
    private static final int TARGET_COUNT = 1000000;
    private AtomicInteger acount = new AtomicInteger(0);
    private int count = 0;

    protected synchronized int inc(){
        return ++count;
    }

    protected synchronized int get(){
        return count;
    }

    public class SyncThread implements Runnable{
        protected String name;
        protected long starttime;
        TestAtomic out;

        public SyncThread(TestAtomic o,long starttime){
            this.out = o;
            this.starttime = starttime;
        }

        @Override
        public void run() {
            int v = out.inc();
            while (v<TARGET_COUNT){
                v = out.inc();
            }
            long endtime = System.currentTimeMillis();
            System.out.println("SyncThread spend:"+(endtime-starttime)+"ms v="+v);
        }
    }

    public class AtomicThread implements Runnable{
        protected String name;
        protected long starttime;

        public AtomicThread(long starttime){
            this.starttime = starttime;
        }

        @Override
        public void run() {
            int v = acount.incrementAndGet();
            while (v<TARGET_COUNT){
                v = acount.incrementAndGet();
            }
            long endtime = System.currentTimeMillis();
            System.out.println("AtomicThread spend:"+(endtime-starttime)+"ms v="+v);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(MAX_THREADS);
        long starttime = System.currentTimeMillis();
        TestAtomic test = new TestAtomic();
        AtomicThread atomicThread = test.new AtomicThread(starttime);
        SyncThread syncThread = test.new SyncThread(test,starttime);

        //测试atomic的效率
//        for (int i = 0; i < test.TASK_COUNT; i++) {
//            service.submit(atomicThread);
//        }
//      测试synch的效率，明显低于atomic效率
        for (int i = 0; i < test.TASK_COUNT; i++) {
            service.submit(syncThread);
        }
        Thread.sleep(10000);
    }


}
