package cn.lzq.thread.function;

/**
 * Created by liuzhiqi on 16/5/20.
 */
public class TestThreadLocal {
    private ThreadLocal<Long> longLocal = new ThreadLocal<Long>();

    private ThreadLocal<String> stringLocal = new ThreadLocal<String>();

    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public Long getLong(){
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final TestThreadLocal test = new TestThreadLocal();

        test.set();

        System.out.println(test.getLong());
        System.out.println(test.getString());

        Thread thread = new Thread(){
            @Override
            public void run() {
                test.set();
                System.out.println(test.getLong());
                System.out.println(test.getString());
            }
        };

        thread.start();
        thread.join();

        System.out.println(test.getLong());
        System.out.println(test.getString());

    }
}
