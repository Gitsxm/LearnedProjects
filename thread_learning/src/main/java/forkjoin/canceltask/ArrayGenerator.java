package forkjoin.canceltask;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * TODO 取消任务-数组生成器
 *
 * @author MGG
 * @version 1.0
 * @date 2021/1/17 11:02
 */
public class ArrayGenerator {
    public int[] generator(int size){
        int arr[] = new int[size];
        Random random = new Random();
        IntStream.range(0,size).forEach(e->{
            arr[e] = random.nextInt(10);
        });
        return arr;
    }
}
