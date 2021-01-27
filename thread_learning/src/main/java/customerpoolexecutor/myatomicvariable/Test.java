package customerpoolexecutor.myatomicvariable;

/**
 * TODO 查看停车场状态。。。
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/26 23:47
 */
public class Test {
    public static void main(String[] args) {
        ParkingCounter counter = new ParkingCounter(5);
        Sensor1 sensor1 = new Sensor1(counter);
        Sensor2 sensor2 = new Sensor2(counter);
        Thread t1 = new Thread(sensor1);
        Thread t2 = new Thread(sensor2);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main : numer of the counter :"+counter.get());
        System.out.println("Main : end...");
    }
}
