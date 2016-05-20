package cn.lzq.thread.design.future;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

/**
 * Created by liuzhiqi on 16/5/20.
 */
public class RealData implements Data {
    protected final String result;

    public RealData(String para) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            //用sleep代表一个比较费时的操作
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.result = sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}
