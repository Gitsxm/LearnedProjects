package customerpoolexecutor.myatomicvariable;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO 创建定制 原子类
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/26 23:37
 */
public class ParkingCounter extends AtomicInteger {
    private int maxNumber;

    public ParkingCounter(int maxNumber) {
        set(0);
        this.maxNumber = maxNumber;
    }

    public boolean carIn(){
        for(;;){
            int value = get();
            if (value == maxNumber){
                System.out.println("ParkingCounter: The parking is full");
                return false;
            }else {
                int newValue = value+1;
                boolean changed = compareAndSet(value,newValue);
                if (changed){
                    System.out.println("ParkingCounter: a car has entered..");
                    return true;
                }
            }
        }
    }

    public boolean carOut(){
        for (;;){
            int value = get();
            if (value ==0){
                System.out.println("ParkingCounter: The parking lot is empty..");
                return false;
            }else {
                int newValue = value-1;
                boolean changed = compareAndSet(value,newValue);
                if (changed){
                    System.out.println("ParkingCounter: a car has gone out...");
                    return true;
                }
            }
        }
    }
}
