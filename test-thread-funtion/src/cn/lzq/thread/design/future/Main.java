package cn.lzq.thread.design.future;

/**
 * Created by liuzhiqi on 16/5/20.
 */
public class Main {

    public static void main(String[] args) {
        Client client = new Client();

        Data data = client.request("name");

        System.out.println("请求完毕");

        //一下代表处理其他的业务
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //使用真实的数据
        System.out.println("数据= "+data.getResult());

    }
}
