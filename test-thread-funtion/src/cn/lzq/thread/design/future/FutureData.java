package cn.lzq.thread.design.future;

/**
 * Created by liuzhiqi on 16/5/20.
 */
public class FutureData implements Data {
    protected RealData realData = null;
    protected boolean isReady = false;

    public synchronized void setRealData(RealData realData){
        if (isReady) return;
        this.realData = realData;
        isReady = true;
        notifyAll();
    }



    @Override
    public synchronized String getResult() {
        while (!isReady){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.result;
    }
}
