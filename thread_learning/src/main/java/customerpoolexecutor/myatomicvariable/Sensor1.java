package customerpoolexecutor.myatomicvariable;

/**
 * TODO 创建定制原子类
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/26 23:45
 */
public class Sensor1 implements Runnable{
    private ParkingCounter counter;

    public Sensor1(ParkingCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.carIn();
        counter.carIn();
        counter.carIn();
        counter.carIn();
        counter.carOut();
        counter.carOut();
        counter.carOut();
        counter.carIn();
    }
}
