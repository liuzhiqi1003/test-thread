package cn.lzq.thread.design.future.jdk;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by liuzhiqi on 16/5/20.
 */
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<String>(new RealData("a"));
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.submit(futureTask);
        System.out.println("请求完毕");
        //处理其他的业务
        Thread.sleep(2000);

        System.out.println("数据 = " +futureTask.get());
    }
}
