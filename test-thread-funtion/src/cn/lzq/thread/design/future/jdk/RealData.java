package cn.lzq.thread.design.future.jdk;

import java.util.concurrent.Callable;

/**
 * Created by liuzhiqi on 16/5/20.
 */
public class RealData implements Callable<String> {
    private String para;

    public RealData(String para) {
        this.para = para;
    }

    @Override
    public String call() throws Exception {
        //真实的业务逻辑，类似商家发+快递运送
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            Thread.sleep(100);
        }
        return sb.toString();
    }
}
