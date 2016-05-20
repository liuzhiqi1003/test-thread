package cn.lzq.thread.design.future;

/**
 * Created by liuzhiqi on 16/5/20.
 * 获取futureData,开启构造realData的线程，并在接受请求后，很快的返回futureData
 */
public class Client {

    public Data request(final String queryStr){
        //先创建个futureData返回，类似网购商家生成个订单
        final FutureData futureData = new FutureData();

        //然后开启个线程进行装配realdata，类似网购商家发货+快递运送
        new Thread(){
            @Override
            public void run() {
                RealData realData = new RealData(queryStr);
                futureData.setRealData(realData);
            }
        }.start();

        return futureData;
    }
}
